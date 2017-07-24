package com.example.FurnitureShare.ui.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.FurnitureShare.R;
import com.example.FurnitureShare.app.AllUrl;
import com.example.FurnitureShare.app.FSApplication;
import com.example.FurnitureShare.base.BaseActivity;
import com.example.FurnitureShare.entry.AddressList;
import com.example.FurnitureShare.nohttp.HttpListener;
import com.example.FurnitureShare.utils.JsonUtil;
import com.example.FurnitureShare.utils.StaturBar;
import com.example.FurnitureShare.utils.ToastUtil;
import com.example.FurnitureShare.view.Swipe.SwipeAdapter;
import com.example.FurnitureShare.view.Swipe.SwipeListView;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 地址管理
 */
public class AddressManageActivity extends BaseActivity {

    private static final String TAG = "AddressManageActivity";

    @BindView(R.id.bt_back)
    Button btBack;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.listview)
    SwipeListView listview;
    @BindView(R.id.rl)
    LinearLayout rl;
    @BindView(R.id.bt_newaddress)
    Button btNewaddress;
    private String userid;

    private int delPos;
    private SwipeAdapter adapter;
    private ArrayList<AddressList.DataBean> data;
    private String delId;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            //从当前的data里删除 点击了红色删除的条目
            switch (msg.what) {
                case 1:
                    if (delPos != -1) {
                        data.remove(delPos);
                        adapter.notifyDataSetChanged();
                    }

                    break;
            }

            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_manage);
        ButterKnife.bind(this);

        // 添加到Activity集合
        FSApplication.instance.addActivity(this);

        // 系统 6.0 以上 状态栏白底黑字的实现方法
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        StaturBar.MIUISetStatusBarLightMode(getWindow(), true);
        StaturBar.FlymeSetStatusBarLightMode(getWindow(), true);

        userid = FSApplication.instance.sp.getString("userid", "");
    }

    @Override
    protected void onResume() {
        super.onResume();

        PostAddressManage();

        // 设置item点击事件
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            private AddressList.DataBean dataBean;

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                dataBean = data.get(position);

                Log.e(TAG, "onItemClick: "+position );
                Log.e(TAG, "onItemClick: "+dataBean );
                Intent intent1 = new Intent();
                intent1.putExtra("dataBean", dataBean);

                intent1.setClass(AddressManageActivity.this, AddressActivity.class);
                startActivity(intent1);

            }
        });
    }


    private void PostAddressManage() {

        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.ADRESSLIST, RequestMethod.POST);
        if (request != null) {
            request.add("uid", userid);

            // 添加到请求队列
            request(0, request, addressobjectListener, true, true);
        }

    }

    private HttpListener<JSONObject> addressobjectListener = new HttpListener<JSONObject>() {

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            try {

                JSONObject js = response.get();
                Log.e(TAG, "address: " + js);
                int code = js.getInt("code");
                AddressList addressList = JsonUtil.parseJsonToBean(js.toString(), AddressList.class);

                if (code == 200) {
                    if (addressList != null) {
                        data = (ArrayList) addressList.getData();


                        adapter = new SwipeAdapter(getBaseContext(), listview.getRightViewWidth(),
                                new SwipeAdapter.IOnItemRightClickListener() {

                                    private String status;

                                    @Override
                                    public void onRightClick(View v, int position) {
                                        AddressList.DataBean dataBean = data.get(position);

                                        delId = dataBean.getId();
                                        status = dataBean.getStatus();


                                        //获取 当前点击右侧删除按钮的条目的示例
                                        View childAt = listview.getChildAt(position - listview.getFirstVisiblePosition());
//隐藏当前的条目的红色删除
                                        listview.hiddenRight(childAt);
//获取点击条蜜的角标值
                                        delPos = position;
                                        //给点击的条目添加一个位置动画
                                        ObjectAnimator animator =
                                                ObjectAnimator.ofFloat(childAt, "translationX", 0, 200f, 0, 200f, 0);
                                        animator.setDuration(500);//0.5s
                                        animator.start();//开启动画

                                        if (status.equals("1")){
                                            //调用删除的接口
                                            deleClick(delId);
//延迟0.6s开启线程，从当前的data里删除数据 并且刷新页面
                                            new Thread(new Runnable() {
                                                public void run() {
                                                    try {
                                                        Thread.sleep(600);
                                                        Message message = new Message();
                                                        message.what = 1;
                                                        handler.sendMessage(message);
                                                    } catch (InterruptedException e) {
                                                        e.printStackTrace();
                                                    }

                                                }
                                            }).start();
                                            Log.e(TAG, "onRightClick: " + position);

                                        }else {
                                            ToastUtil.showToast(getBaseContext(),"请先设置别的默认地址");
                                        }

                                    }
                                }, data);
                        listview.setAdapter(adapter);

//                        if (data.isEmpty()) {
//                            flNomessage.setVisibility(View.VISIBLE);
//                        }
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {

            ToastUtil.showToast(getBaseContext(), "访问网络失败，请检查您的网络！");

        }
    };

    //删除
    private void deleClick(String delId){

        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.ADRESSEDIT, RequestMethod.POST);
        if (request != null){
            request.add("dosubmit",1);
            request.add("uid",userid);
            request.add("id",delId);
            request.add("delete",1);

            // 添加到请求队列
            request(0, request, addressshanobjectListener, true, true);
        }

    }

    private HttpListener<JSONObject> addressshanobjectListener = new HttpListener<JSONObject>() {

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            JSONObject js = response.get();
            Log.e(TAG, "addressshan: " + js);
        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {

            ToastUtil.showToast(getBaseContext(),"访问网络失败，请检查您的网络！");

        }
    };


    @OnClick({R.id.bt_back, R.id.bt_newaddress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_back:
                finish();
                break;
            case R.id.bt_newaddress:

                startActivity(new Intent(this, AddressActivity.class));

                break;
        }
    }
}
