package com.example.FurnitureShare.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.FurnitureShare.R;
import com.example.FurnitureShare.adapter.LoginAdapter;
import com.example.FurnitureShare.app.FSApplication;
import com.example.FurnitureShare.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录-注册-找回密码
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.iv_background)
    ImageView ivBackground;
    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.image_pager)
    ViewPager imagePager;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.rl_loginway)
    RelativeLayout rlLoginway;
    @BindView(R.id.iv_wechat)
    ImageView ivWechat;
    @BindView(R.id.iv_qq)
    ImageView ivQq;
    @BindView(R.id.iv_weibo)
    ImageView ivWeibo;
    @BindView(R.id.iv_weibo1)
    ImageView ivWeibo1;
    @BindView(R.id.rl_loginway2)
    RelativeLayout rlLoginway2;
    private LoginAdapter loginAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        // 添加到Activity集合
        FSApplication.instance.addActivity(this);

        loginAdapter = new LoginAdapter(this, imagePager, ivLogo);
        imagePager.setAdapter(loginAdapter);
        //指定初始化页面
        imagePager.setCurrentItem(1);
        //判断viewpager当前页数
        imagePager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override//滑动状态   这个方法会在屏幕滚动过程中不断被调用
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override // 选中状态
            public void onPageSelected(int position) {
                if (position == 0){
                    rlLoginway.setVisibility(View.GONE);
                    rlLoginway2.setVisibility(View.GONE);
                }else if (position == 1){
                    rlLoginway.setVisibility(View.VISIBLE);
                    rlLoginway2.setVisibility(View.VISIBLE);
                }else if (position == 2){
                    rlLoginway.setVisibility(View.GONE);
                    rlLoginway2.setVisibility(View.GONE);
                }
            }
            @Override //此方法是在状态改变的时候调用
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //隐藏软键盘
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {

            // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                hideSoftInput(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = { 0, 0 };
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 多种隐藏软件盘方法的其中一种
     *
     * @param token
     */
    private void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    @OnClick({R.id.iv_wechat, R.id.iv_qq, R.id.iv_weibo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_wechat:
                break;
            case R.id.iv_qq:
                break;
            case R.id.iv_weibo:
                break;
        }
    }
}
