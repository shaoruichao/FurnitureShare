package com.example.FurnitureShare.ui;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.FurnitureShare.R;
import com.example.FurnitureShare.app.AllUrl;
import com.example.FurnitureShare.app.FSApplication;
import com.example.FurnitureShare.base.BaseFragmentActivity;
import com.example.FurnitureShare.entry.UserMessage;
import com.example.FurnitureShare.nohttp.HttpListener;
import com.example.FurnitureShare.ui.activity.LoginActivity;
import com.example.FurnitureShare.ui.fragment.HomeFragment;
import com.example.FurnitureShare.ui.fragment.MyFragment;
import com.example.FurnitureShare.ui.fragment.ShoppingFragment;
import com.example.FurnitureShare.utils.JsonUtil;
import com.example.FurnitureShare.utils.SharePreferencesUtils;
import com.example.FurnitureShare.utils.ToastUtil;
import com.example.FurnitureShare.view.MainNavigateTabBar;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

import java.net.CookieManager;
import java.net.HttpCookie;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseFragmentActivity {

    private static final String TAG = "HomeActivity";

    private static final String TAG_PAGE_HOME = "首页";
    private static final String TAG_PAGE_SHOPPING = " 购物车";
    private static final String TAG_PAGE_PERSON = "我的";
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.rl_home)
    RelativeLayout rlHome;
    @BindView(R.id.iv3)
    ImageView iv3;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.rl_shopping)
    RelativeLayout rlShopping;
    @BindView(R.id.iv5)
    ImageView iv5;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.rl_my)
    RelativeLayout rlMy;
    @BindView(R.id.ll_tab)
    LinearLayout llTab;

    private long mExitTime;

    @BindView(R.id.main_container)
    FrameLayout mainContainer;
//    @BindView(R.id.mainTabBar)
//    MainNavigateTabBar mainTabBar;

    private String realy;
    private String point;
    private String sex;
    private String birthday;
    private String userid;
    private String nickname;
    private String userName;
    private String avatar;
    private UserMessage.DataBean data;
    private String status;
    private HomeFragment homeFragment;
    private ShoppingFragment shoppingFragment;
    private MyFragment myFragment;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // 添加到Activity集合
        FSApplication.instance.addActivity(this);

        ButterKnife.bind(this);
        homeFragment = new HomeFragment();
        shoppingFragment = new ShoppingFragment();
        myFragment = new MyFragment();

        if (!homeFragment.isAdded()){
            fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.main_container, homeFragment).hide(shoppingFragment).hide(myFragment).show(homeFragment).commit();
        }else {
            fragmentTransaction.hide(shoppingFragment).hide(myFragment).show(homeFragment).commit();
        }
//        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.main_container,new HomeFragment());
//        fragmentTransaction.commit();
        iv1.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_home_selected));
        tv1.setTextColor(Color.parseColor("#181818"));

//        mainTabBar.onRestoreInstanceState(savedInstanceState);
//
//        mainTabBar.addTab(HomeFragment.class, new MainNavigateTabBar.TabParam(R.mipmap.comui_tab_home, R.mipmap.comui_tab_home_selected, TAG_PAGE_HOME));
//        mainTabBar.addTab(ShoppingFragment.class, new MainNavigateTabBar.TabParam(R.mipmap.comui_tab_shopping, R.mipmap.comui_tab_shopping_selected, TAG_PAGE_SHOPPING));
//        mainTabBar.addTab(MyFragment.class, new MainNavigateTabBar.TabParam(R.mipmap.comui_tab_person, R.mipmap.comui_tab_person_selected, TAG_PAGE_PERSON));

//        mainTabBar.setTabSelectListener(new MainNavigateTabBar.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(MainNavigateTabBar.ViewHolder holder) {
//                int tabIndex = holder.tabIndex;
//                Log.e(TAG, "onTabSelected: "+tabIndex );
//                if (tabIndex == 1){
//                    if (!status.equals("1")){
//                        startActivity(new Intent(getBaseContext(), LoginActivity.class));
//                    }
//                }
//            }
//        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        getUserMessage();


    }

    //获取用户信息
    private void getUserMessage() {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.USERMESSAGE);
        request(0, request, userMessageListener, true, true);
    }

    private HttpListener<JSONObject> userMessageListener = new HttpListener<JSONObject>() {

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onSucceed(int what, Response<JSONObject> response) {

            try {
                CookieManager cookieManager = NoHttp.getCookieManager();
                List<HttpCookie> cookies1 = cookieManager.getCookieStore().getCookies();
                String cookies_login = String.valueOf(cookies1);
                Log.e(TAG, "cookies_loginonSucceed: " + cookies_login);
                String cookies = cookies_login.substring(1, cookies_login.indexOf("]"));
                Log.e(TAG, "cookies_loginon: " + cookies);

                JSONObject js = response.get();
                Log.e(TAG, "usermessage: " + js);
                status = String.valueOf(js.getInt("status"));
                UserMessage userMessage = JsonUtil.parseJsonToBean(js.toString(), UserMessage.class);

                if (status.equals("1")) {

                    if (userMessage != null) {
                        data = userMessage.getData();
                        avatar = data.getAvatar();
                        userName = data.getUserName();
                        nickname = data.getNickname();
                        userid = data.getUserid();
                        birthday = data.getBirthday();
                        sex = data.getSex();
                        point = data.getPoint();//积分
                        realy = data.getRealy();//认证状态


                        SharedPreferences.Editor edit = FSApplication.instance.sp.edit();
                        edit.putString("cookies", cookies);
                        edit.putString("status", status);
                        edit.putString("userid", userid);
                        edit.putString("avatar", avatar);
                        edit.putString("nickname", nickname);
                        edit.putString("userName", userName);
                        edit.putString("birthday", birthday);
                        edit.putString("sex", sex);
                        edit.putString("point", point);
                        edit.putString("realy", realy);
                        edit.commit();

                        //保存请求到的数据
                        SharePreferencesUtils.putBean(getBaseContext(), "userMessage",
                                userMessage);
                    }
                } else {
                    SharedPreferences.Editor edit = FSApplication.instance.sp.edit();
                    edit.putString("status", status);
                    edit.commit();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {
            ToastUtil.showToast(getBaseContext(), "请求网络失败，请稍后重试");
        }
    };

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        mainTabBar.onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {


        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Object mHelperUtils;
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

    @OnClick({R.id.rl_home, R.id.rl_shopping, R.id.rl_my})
    public void onViewClicked(View view) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        switch (view.getId()) {
            case R.id.rl_home:
                if (homeFragment!=null && shoppingFragment!=null && myFragment!=null){
                    if (!homeFragment.isAdded()){
                        fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.add(R.id.main_container, homeFragment).hide(shoppingFragment).hide(myFragment).show(homeFragment).commit();
                    }else {
                        fragmentTransaction.hide(shoppingFragment).hide(myFragment).show(homeFragment).commit();
                    }
//                    fragmentTransaction.replace(R.id.main_container,new HomeFragment());
                    iv1.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_home_selected));
                    tv1.setTextColor(Color.parseColor("#181818"));
                    iv3.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_shopping));
                    tv3.setTextColor(Color.parseColor("#606060"));
                    iv5.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_person));
                    tv5.setTextColor(Color.parseColor("#606060"));
                }

                break;
            case R.id.rl_shopping:
                if (status.equals("1")){
                    if (homeFragment!=null && shoppingFragment!=null && myFragment!=null) {
                        if (!shoppingFragment.isAdded()) {
                            fragmentTransaction = getFragmentManager().beginTransaction();
                            fragmentTransaction.add(R.id.main_container, shoppingFragment).hide(homeFragment).hide(myFragment).show(shoppingFragment).commit();
                        } else {
                            fragmentTransaction.hide(homeFragment).hide(myFragment).show(shoppingFragment).commit();
                        }
//                        fragmentTransaction.replace(R.id.main_container,new ShoppingFragment());
                        iv1.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_home));
                        tv1.setTextColor(Color.parseColor("#606060"));
                        iv3.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_shopping_selected));
                        tv3.setTextColor(Color.parseColor("#181818"));
                        iv5.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_person));
                        tv5.setTextColor(Color.parseColor("#606060"));
                    }

                }else {
                    startActivity(new Intent(getBaseContext(), LoginActivity.class));
                }
                break;
            case R.id.rl_my:
                if (homeFragment!=null && shoppingFragment!=null && myFragment!=null) {
                    if (!myFragment.isAdded()) {
                        fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.add(R.id.main_container, myFragment).hide(homeFragment).hide(shoppingFragment).show(myFragment).commit();
                    } else {
                        fragmentTransaction.hide(homeFragment).hide(shoppingFragment).show(myFragment).commit();
                    }
//                    fragmentTransaction.replace(R.id.main_container,new MyFragment());
                    iv1.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_home));
                    tv1.setTextColor(Color.parseColor("#606060"));
                    iv3.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_shopping));
                    tv3.setTextColor(Color.parseColor("#606060"));
                    iv5.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_person_selected));
                    tv5.setTextColor(Color.parseColor("#181818"));
                }

                break;
        }
//        fragmentTransaction.commit();
    }
}
