package com.example.FurnitureShare.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.FurnitureShare.R;
import com.example.FurnitureShare.adapter.ContractAdapter;
import com.example.FurnitureShare.app.AllUrl;
import com.example.FurnitureShare.app.FSApplication;
import com.example.FurnitureShare.base.BaseActivity;
import com.example.FurnitureShare.entry.Contract;
import com.example.FurnitureShare.entry.MyOrderAll;
import com.example.FurnitureShare.nohttp.HttpListener;
import com.example.FurnitureShare.utils.JsonUtil;
import com.example.FurnitureShare.utils.StaturBar;
import com.example.FurnitureShare.utils.ToastUtil;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.FurnitureShare.app.FSApplication.mContext;

/**
 * 我的合同
 */
public class MyContractActivity extends BaseActivity {

    private static final String TAG = "MyContractActivity";
    @BindView(R.id.bt_back)
    Button btBack;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
    @BindView(R.id.rv_contract)
    RecyclerView rvContract;
    @BindView(R.id.sw)
    SwipeRefreshLayout sw;

    private KProgressHUD hud;
    private String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_contract);
        ButterKnife.bind(this);

        // 添加到Activity集合
        FSApplication.instance.addActivity(this);

        // 系统 6.0 以上 状态栏白底黑字的实现方法
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        StaturBar.MIUISetStatusBarLightMode(getWindow(), true);
        StaturBar.FlymeSetStatusBarLightMode(getWindow(), true);

        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
        hud.show();
        userid = FSApplication.instance.sp.getString("userid", "");
        GetData();
        initView();

    }

    private void initView(){
        //设置刷新的颜色
        sw.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        sw.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                //刷新的时候
                hud = KProgressHUD.create(MyContractActivity.this)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
                hud.show();

                GetData();
                //停止刷新
                sw.setRefreshing(false);
            }
        });
    }

    private void GetData(){
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.MYCONTRACT, RequestMethod.POST);
        if (request != null){
            request.add("dosubmit",1);
            request.add("uid",userid);

            // 添加到请求队列
            request(0, request, contractobjectListener, true, true);
        }

    }

    private HttpListener<JSONObject> contractobjectListener = new HttpListener<JSONObject>() {


        private ContractAdapter contractAdapter;
        private List<Contract.DataBean> data;
        private Contract contract;

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {

            hud.dismiss();

            try {
                JSONObject js = response.get();
                Log.e(TAG, "onSucceed123: "+js );
                int code = js.getInt("code");
                String msg = js.getString("msg");
                if (code == 200){
                    contract = JsonUtil.parseJsonToBean(js.toString(), Contract.class);
                    if (contract != null){
                        data = contract.getData();
                        contractAdapter = new ContractAdapter(data);
                        contractAdapter.openLoadAnimation();

                        rvContract.setAdapter(contractAdapter);

                        rvContract.setHasFixedSize(true);
                        rvContract.setLayoutManager(new LinearLayoutManager(getBaseContext()));

                        contractAdapter.setmOnItemClickLitener(new ContractAdapter.OnItemClickListener() {
                            @Override
                            public void changeonItemClick(Button bt_change, int postion) {

                            }

                            @Override
                            public void refundonItemClick(Button bt_refund, int postion) {
//                                ToastUtil.showToast(getBaseContext(),postion+"");
                            }

                            @Override
                            public void payonItemClick(Button bt_pay, int postion) {

                            }

                            @Override
                            public void contractonItemClick(RelativeLayout rl_contract, int postion) {
                                String id = data.get(postion).getId();
                                Intent intent = new Intent();
                                intent.putExtra("id",id);
                                intent.setClass(getBaseContext(), ContractDetailActivity.class);
                                startActivity(intent);
                            }
                        });

                    }
                }else {
                    ToastUtil.showToast(getBaseContext(),msg);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {

            hud.dismiss();
            ToastUtil.showToast(getApplicationContext(),"访问网络失败，请检查您的网络！");

        }
    };


    @OnClick(R.id.bt_back)
    public void onViewClicked() {
        finish();
    }
}
