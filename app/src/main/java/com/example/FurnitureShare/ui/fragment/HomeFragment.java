package com.example.FurnitureShare.ui.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.FurnitureShare.R;
import com.example.FurnitureShare.adapter.HomeListAdapter;
import com.example.FurnitureShare.app.AllUrl;
import com.example.FurnitureShare.base.BaseFragment;
import com.example.FurnitureShare.entry.Banner;
import com.example.FurnitureShare.entry.Classify;
import com.example.FurnitureShare.entry.HomeLIst;
import com.example.FurnitureShare.nohttp.HttpListener;
import com.example.FurnitureShare.ui.activity.MenuDestailActivity;
import com.example.FurnitureShare.ui.activity.MenuListActivity;
import com.example.FurnitureShare.utils.JsonUtil;
import com.example.FurnitureShare.utils.StaturBar;
import com.example.FurnitureShare.utils.ToastUtil;
import com.example.FurnitureShare.view.FlyBanner;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment {

    private static final String TAG = "HomeFragment";
    @BindView(R.id.rv_home)
    RecyclerView rvHome;
    @BindView(R.id.sw_layout)
    SwipeRefreshLayout swLayout;
    Unbinder unbinder;
    private View view;

    //banner图片集合
    private List<String> bigPics;
    private ArrayList<HomeLIst.DataBeanX> data;
    private HomeListAdapter homeListAdapter;
    private List<Classify.DataBean> classifyData;
    private String msg;//分类

    private KProgressHUD hud;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        // 系统 6.0 以上 状态栏白底黑字的实现方法
        getActivity().getWindow()
                .getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        StaturBar.MIUISetStatusBarLightMode(getActivity().getWindow(), true);
        StaturBar.FlymeSetStatusBarLightMode(getActivity().getWindow(), true);

        unbinder = ButterKnife.bind(this, view);

        setinit();

        hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
        hud.show();

        getList();
        return view;
    }

    /*设置刷新的效果*/

    private void setinit() {


//设置刷新的颜色
        swLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                //刷新的时候

                hud = KProgressHUD.create(getActivity())
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
                hud.show();

                getList();

                //停止刷新
                swLayout.setRefreshing(false);
            }
        });


    }

    private void getList() {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.HOMELIST);
        request(0, request, listListener, true, true);

    }

    private HttpListener<JSONObject> listListener = new HttpListener<JSONObject>() {

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            scheduleDismiss();
            try {
                JSONObject js = response.get();
                Log.e(TAG, "list: " + js);
                int code = js.getInt("code");
                if (code == 200) {
                    HomeLIst homeLIst = JsonUtil.parseJsonToBean(js.toString(), HomeLIst.class);
                    if (homeLIst != null) {
                        data = homeLIst.getData();
                        homeListAdapter = new HomeListAdapter(getActivity(), data);
                        getBanner();
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
            ToastUtil.showToast(getActivity(), "请求失败");
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


    //请求轮播图
    private void getBanner() {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.BANNER);
        request(0, request, bannerListener, true, true);
    }

    private HttpListener<JSONObject> bannerListener = new HttpListener<JSONObject>() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onSucceed(int what, Response<JSONObject> response) {

            try {

                JSONObject js = response.get();
                Log.e(TAG, "bannerListener: " + js);
                int code = js.getInt("code");
                if (code == 200) {
                    Banner banner = JsonUtil.parseJsonToBean(js.toString(), Banner.class);
                    if (banner != null) {
                        final List<Banner.DataBean> data = banner.getData();
                        if (data.size()!=0){
                            bigPics = new ArrayList<>();
                            for (int i = 0; i < data.size(); i++) {
                                String thumb = data.get(i).getThumb();
                                Log.e(TAG, "onSucceed: " + thumb);
                                bigPics.add(thumb);
                            }
                            getClassify();
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

        }
    };


    //分类
    private void getClassify(){
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.CLASSIFY);
        request(0, request, classifyListener, true, true);
    }
    private HttpListener<JSONObject> classifyListener = new HttpListener<JSONObject>() {


        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            try {
                JSONObject js = response.get();
                Log.e(TAG, "classifyListener: "+js );
                int code = js.getInt("code");
                if (code == 200){
                    Classify classify = JsonUtil.parseJsonToBean(js.toString(), Classify.class);
                    if (classify!=null){
                        classifyData = classify.getData();
                        msg = classify.getMsg();

                        rvHome.setHasFixedSize(true);
                        rvHome.setLayoutManager(new LinearLayoutManager(getActivity()));

                        initBannerTop(homeListAdapter);
                        initGridMenu(homeListAdapter);
                        rvHome.setAdapter(homeListAdapter);

                    }
                }


            } catch (Exception e) {
                Log.e(TAG, "Exception: "+"123");
                e.printStackTrace();
            }

        }
        @Override
        public void onFailed(int what, Response<JSONObject> response) {

            ToastUtil.showToast(getActivity(),"请求失败");
        }
    };


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initBannerTop(HomeListAdapter homeListAdapter) {
        View bannerBigView = LayoutInflater.from(getActivity()).inflate(R.layout.banner_top, rvHome, false);
        FlyBanner bannerTop = (FlyBanner) bannerBigView.findViewById(R.id.bannerTop);
        homeListAdapter.addHeadView0(bannerBigView);
        bannerTop.setImagesUrl(bigPics);
        bannerTop.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                ToastUtil.showToast(getActivity(),"position--->"+position);
            }
        });
    }


    /**
     * 分类菜单
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initGridMenu(HomeListAdapter homeListAdapter) {
        View gridMenu = LayoutInflater.from(getActivity()).inflate(R.layout.grid_menu, rvHome, false);
        ImageView iv_grid = (ImageView) gridMenu.findViewById(R.id.iv_grid);
        Glide.with(getActivity()).load("http://rutu.9fat.com/352.jpg").into(iv_grid);
        TextView tv_menu_title = (TextView) gridMenu.findViewById(R.id.tv_menu_title);
        tv_menu_title.setText(msg);
        Button bt_menu = (Button) gridMenu.findViewById(R.id.bt_menu);
        bt_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("typename",msg);
                intent.setClass(getActivity(), MenuListActivity.class);
                startActivity(intent);
            }
        });

        RelativeLayout rl_menu1 = (RelativeLayout) gridMenu.findViewById(R.id.rl_menu1);
        rl_menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.putExtra("catid",classifyData.get(0).getCatid());
//                intent.putExtra("typename",msg);
                intent.putExtra("typename",classifyData.get(0).getCatname());
                intent.setClass(getActivity(), MenuDestailActivity.class);
                startActivity(intent);

            }
        });
        TextView tv_menu1 = (TextView) gridMenu.findViewById(R.id.tv_menu1);
        tv_menu1.setText(classifyData.get(0).getCatname());
        ImageView iv_menu1 = (ImageView) gridMenu.findViewById(R.id.iv_menu1);
        Glide.with(getActivity()).load(classifyData.get(0).getImage()).into(iv_menu1);


        RelativeLayout rl_menu2 = (RelativeLayout) gridMenu.findViewById(R.id.rl_menu2);
        rl_menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("catid",classifyData.get(1).getCatid());
//                intent.putExtra("typename",msg);
                intent.putExtra("typename",classifyData.get(1).getCatname());
                intent.setClass(getActivity(), MenuDestailActivity.class);
                startActivity(intent);
            }
        });
        TextView tv_menu2 = (TextView) gridMenu.findViewById(R.id.tv_menu2);
        tv_menu2.setText(classifyData.get(1).getCatname());
        ImageView iv_menu2 = (ImageView) gridMenu.findViewById(R.id.iv_menu2);
        Glide.with(getActivity()).load(classifyData.get(1).getImage()).into(iv_menu2);

        RelativeLayout rl_menu3 = (RelativeLayout) gridMenu.findViewById(R.id.rl_menu3);
        rl_menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("catid",classifyData.get(2).getCatid());
//                intent.putExtra("typename",msg);
                intent.putExtra("typename",classifyData.get(2).getCatname());
                intent.setClass(getActivity(), MenuDestailActivity.class);
                startActivity(intent);
            }
        });
        TextView tv_menu3 = (TextView) gridMenu.findViewById(R.id.tv_menu3);
        tv_menu3.setText(classifyData.get(2).getCatname());
        ImageView iv_menu3 = (ImageView) gridMenu.findViewById(R.id.iv_menu3);
        Glide.with(getActivity()).load(classifyData.get(2).getImage()).into(iv_menu3);

        RelativeLayout rl_menu4 = (RelativeLayout) gridMenu.findViewById(R.id.rl_menu4);
        rl_menu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("catid",classifyData.get(3).getCatid());
//                intent.putExtra("typename",msg);
                intent.putExtra("typename",classifyData.get(3).getCatname());
                intent.setClass(getActivity(), MenuDestailActivity.class);
                startActivity(intent);
            }
        });
        TextView tv_menu4 = (TextView) gridMenu.findViewById(R.id.tv_menu4);
        tv_menu4.setText(classifyData.get(3).getCatname());
        ImageView iv_menu4 = (ImageView) gridMenu.findViewById(R.id.iv_menu4);
        Glide.with(getActivity()).load(classifyData.get(3).getImage()).into(iv_menu4);

        RelativeLayout rl_menu5 = (RelativeLayout) gridMenu.findViewById(R.id.rl_menu5);
        rl_menu5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("catid",classifyData.get(4).getCatid());
//                intent.putExtra("typename",msg);
                intent.putExtra("typename",classifyData.get(4).getCatname());
                intent.setClass(getActivity(), MenuDestailActivity.class);
                startActivity(intent);
            }
        });
        TextView tv_menu5 = (TextView) gridMenu.findViewById(R.id.tv_menu5);
        tv_menu5.setText(classifyData.get(4).getCatname());
        ImageView iv_menu5 = (ImageView) gridMenu.findViewById(R.id.iv_menu5);
        Glide.with(getActivity()).load(classifyData.get(4).getImage()).into(iv_menu5);

        RelativeLayout rl_menu6 = (RelativeLayout) gridMenu.findViewById(R.id.rl_menu6);
        rl_menu6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("catid",classifyData.get(5).getCatid());
//                intent.putExtra("typename",msg);
                intent.putExtra("typename",classifyData.get(5).getCatname());
                intent.setClass(getActivity(), MenuDestailActivity.class);
                startActivity(intent);
            }
        });
        TextView tv_menu6 = (TextView) gridMenu.findViewById(R.id.tv_menu6);
        tv_menu6.setText(classifyData.get(5).getCatname());
        ImageView iv_menu6 = (ImageView) gridMenu.findViewById(R.id.iv_menu6);
        Glide.with(getActivity()).load(classifyData.get(5).getImage()).into(iv_menu6);

        homeListAdapter.addHeaderView1(gridMenu);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
