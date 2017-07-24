package com.example.FurnitureShare.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.FurnitureShare.R;
import com.example.FurnitureShare.adapter.MenuDestailAdapter;
import com.example.FurnitureShare.app.AllUrl;
import com.example.FurnitureShare.app.FSApplication;
import com.example.FurnitureShare.base.BaseActivity;
import com.example.FurnitureShare.entry.ClassifyDestail;
import com.example.FurnitureShare.nohttp.HttpListener;
import com.example.FurnitureShare.utils.JsonUtil;
import com.example.FurnitureShare.utils.StaturBar;
import com.example.FurnitureShare.utils.ToastUtil;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.FurnitureShare.app.FSApplication.mContext;

/**
 * 精选分类- 详情
 */
public class MenuDestailActivity extends BaseActivity {

    private static final String TAG = "MenuDestailActivity";

    @BindView(R.id.bt_back)
    Button btBack;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
    @BindView(R.id.rv_menu_destail)
    RecyclerView rvMenuDestail;
    @BindView(R.id.sw_layout)
    SwipeRefreshLayout swLayout;

    private KProgressHUD hud;
    private String typename;
    private String catid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_destail);
        ButterKnife.bind(this);
        // 添加到Activity集合
        FSApplication.instance.addActivity(this);

        // 系统 6.0 以上 状态栏白底黑字的实现方法
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        StaturBar.MIUISetStatusBarLightMode(getWindow(), true);
        StaturBar.FlymeSetStatusBarLightMode(getWindow(), true);

        Intent intent = getIntent();
        catid = intent.getStringExtra("catid");
        Log.e(TAG, "onCreate: "+catid );
        typename = intent.getStringExtra("typename");
        tvTitle.setText(typename);

        setinit();
        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
        hud.show();

        getList();
    }

    /*设置刷新的效果*/

    private void setinit() {
//设置刷新的颜色
        swLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                //刷新的时候

                hud = KProgressHUD.create(MenuDestailActivity.this)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
                hud.show();

                getList();

                //停止刷新
                swLayout.setRefreshing(false);
            }
        });

    }

    private void getList() {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.CLASSIFYDESTAIL+catid);
        request(0, request, classdestailListener, true, true);

    }
    private HttpListener<JSONObject> classdestailListener = new HttpListener<JSONObject>() {

        private MenuDestailAdapter menuDestailAdapter;
        private ClassifyDestail classifyDestail;
        private List<ClassifyDestail.DataBean> data;

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            scheduleDismiss();
            try {
                JSONObject js = response.get();
                Log.e(TAG, "list: " + js);
                int code = js.getInt("code");
                if (code == 200) {

                    classifyDestail = JsonUtil.parseJsonToBean(js.toString(), ClassifyDestail.class);
                    if (classifyDestail != null){
                        data = classifyDestail.getData();

                        if (data.size() != 0){
                            menuDestailAdapter = new MenuDestailAdapter(data);
                            menuDestailAdapter.openLoadAnimation();

                            rvMenuDestail.setAdapter(menuDestailAdapter);
                            rvMenuDestail.setHasFixedSize(true);
                            GridLayoutManager mgr = new GridLayoutManager(getBaseContext(), 2) {
                                @Override
                                public boolean canScrollVertically() {
                                    return true;
                                }
                            };
                            rvMenuDestail.setLayoutManager(mgr);

                            menuDestailAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
                                @Override
                                public void onItemClick(View view, int i) {
                                    String id = data.get(i).getId();

                                    Intent intent = new Intent();
                                    intent.putExtra("id",id);
                                    intent.putExtra("title",typename);
                                    intent.setClass(mContext, DestailActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }
                            });

                        }
                    }


                }


            } catch (Exception e) {
                Log.e(TAG, "Exception: " + "123");
                e.printStackTrace();
            }

        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {
            scheduleDismiss();
            ToastUtil.showToast(getBaseContext(), "请求失败");
        }
    };

    private void scheduleDismiss() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hud.dismiss();
            }
        }, 2000);
    }


    @OnClick(R.id.bt_back)
    public void onViewClicked() {
        finish();
    }
}
