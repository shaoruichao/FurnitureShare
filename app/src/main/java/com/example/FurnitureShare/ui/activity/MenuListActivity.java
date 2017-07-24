package com.example.FurnitureShare.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
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
import com.example.FurnitureShare.adapter.HomeListAdapter;
import com.example.FurnitureShare.adapter.MenuListAdapter;
import com.example.FurnitureShare.adapter.MenuListItemAdapter;
import com.example.FurnitureShare.app.AllUrl;
import com.example.FurnitureShare.app.FSApplication;
import com.example.FurnitureShare.base.BaseActivity;
import com.example.FurnitureShare.entry.Classify;
import com.example.FurnitureShare.entry.ClassifyList;
import com.example.FurnitureShare.entry.HomeLIst;
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

/**
 * 精选分类-全部分类
 */
public class MenuListActivity extends BaseActivity {

    private static final String TAG = "MenuListActivity";

    @BindView(R.id.bt_back)
    Button btBack;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
    @BindView(R.id.rv_menu_list)
    RecyclerView rvMenuList;
    @BindView(R.id.sw_layout)
    SwipeRefreshLayout swLayout;

    private KProgressHUD hud;
    private String typename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);
        ButterKnife.bind(this);

        // 添加到Activity集合
        FSApplication.instance.addActivity(this);

        // 系统 6.0 以上 状态栏白底黑字的实现方法
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        StaturBar.MIUISetStatusBarLightMode(getWindow(), true);
        StaturBar.FlymeSetStatusBarLightMode(getWindow(), true);

        Intent intent = getIntent();
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

                hud = KProgressHUD.create(MenuListActivity.this)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
                hud.show();

                getList();

                //停止刷新
                swLayout.setRefreshing(false);
            }
        });

    }

    private void getList() {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.CLASSIFYLIST);
        request(0, request, classlistListener, true, true);

    }
    private HttpListener<JSONObject> classlistListener = new HttpListener<JSONObject>() {


        private MenuListAdapter menuListAdapter;
        private List<ClassifyList.DataBean> data;

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            scheduleDismiss();
            try {
                JSONObject js = response.get();
                Log.e(TAG, "list: " + js);
                int code = js.getInt("code");
                if (code == 200) {
                    ClassifyList classifyList = JsonUtil.parseJsonToBean(js.toString(), ClassifyList.class);
                    if (classifyList != null){
                        data = classifyList.getData();
                        menuListAdapter = new MenuListAdapter(data,typename);
                        menuListAdapter.openLoadAnimation();

                        rvMenuList.setAdapter(menuListAdapter);
                        rvMenuList.setHasFixedSize(true);
                        rvMenuList.setLayoutManager(new LinearLayoutManager(getBaseContext()));

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
