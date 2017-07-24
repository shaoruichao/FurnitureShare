package com.example.FurnitureShare.ui;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.FurnitureShare.R;
import com.example.FurnitureShare.app.AllUrl;
import com.example.FurnitureShare.app.FSApplication;
import com.example.FurnitureShare.base.BaseFragmentActivity;
import com.example.FurnitureShare.entry.UserMessage;
import com.example.FurnitureShare.nohttp.HttpListener;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseFragmentActivity {

    private static final String TAG = "HomeActivity";

    private static final String TAG_PAGE_HOME = "首页";
    private static final String TAG_PAGE_SHOPPING = " 购物车";
    private static final String TAG_PAGE_PERSON = "我的";

    private long mExitTime;

    @BindView(R.id.main_container)
    FrameLayout mainContainer;
    @BindView(R.id.mainTabBar)
    MainNavigateTabBar mainTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // 添加到Activity集合
        FSApplication.instance.addActivity(this);

        ButterKnife.bind(this);

        mainTabBar.onRestoreInstanceState(savedInstanceState);

        mainTabBar.addTab(HomeFragment.class, new MainNavigateTabBar.TabParam(R.mipmap.comui_tab_home, R.mipmap.comui_tab_home_selected, TAG_PAGE_HOME));
        mainTabBar.addTab(ShoppingFragment.class, new MainNavigateTabBar.TabParam(R.mipmap.comui_tab_shopping, R.mipmap.comui_tab_shopping_selected, TAG_PAGE_SHOPPING));
        mainTabBar.addTab(MyFragment.class, new MainNavigateTabBar.TabParam(R.mipmap.comui_tab_person, R.mipmap.comui_tab_person_selected, TAG_PAGE_PERSON));

    }

    @Override
    protected void onResume() {
        super.onResume();

        getUserMessage();
    }

    //获取用户信息
    private void getUserMessage(){
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.USERMESSAGE);
        request(0, request, userMessageListener, true, true);
    }

    private HttpListener<JSONObject> userMessageListener = new HttpListener<JSONObject>() {


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

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onSucceed(int what, Response<JSONObject> response) {

            try {

                JSONObject js = response.get();
                Log.e(TAG, "usermessage: "+js );
                status = String.valueOf(js.getInt("status"));
                UserMessage userMessage = JsonUtil.parseJsonToBean(js.toString(), UserMessage.class);

                if (status.equals("1")){

                    if (userMessage != null){
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
                        edit.putString("status", status);
                        edit.putString("userid", userid);
                        edit.putString("avatar", avatar);
                        edit.putString("nickname", nickname);
                        edit.putString("userName", userName);
                        edit.putString("birthday", birthday);
                        edit.putString("sex", sex);
                        edit.putString("point",point);
                        edit.putString("realy",realy);
                        edit.commit();

                        //保存请求到的数据
                        SharePreferencesUtils.putBean(getBaseContext(), "userMessage",
                                userMessage);
                    }
                }else {
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
            ToastUtil.showToast(getBaseContext(),"请求网络失败，请稍后重试");
        }
    };

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mainTabBar.onSaveInstanceState(outState);
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
}
