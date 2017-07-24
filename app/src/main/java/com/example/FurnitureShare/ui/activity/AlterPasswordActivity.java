package com.example.FurnitureShare.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.FurnitureShare.R;
import com.example.FurnitureShare.app.FSApplication;
import com.example.FurnitureShare.base.BaseActivity;
import com.example.FurnitureShare.utils.StaturBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改密码
 */
public class AlterPasswordActivity extends BaseActivity {

    @BindView(R.id.bt_back)
    Button btBack;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_head_title)
    TextView tvHeadTitle;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
    @BindView(R.id.tv_firstPassword)
    EditText tvFirstPassword;
    @BindView(R.id.first_bt_hiddenmima)
    ImageView firstBtHiddenmima;
    @BindView(R.id.first_bt_hiddenmima1)
    ImageView firstBtHiddenmima1;
    @BindView(R.id.rl_firstPassword)
    RelativeLayout rlFirstPassword;
    @BindView(R.id.tv_secondPassword)
    EditText tvSecondPassword;
    @BindView(R.id.second_bt_hiddenmima)
    ImageView secondBtHiddenmima;
    @BindView(R.id.second_bt_hiddenmima1)
    ImageView secondBtHiddenmima1;
    @BindView(R.id.rl_secondPassword)
    RelativeLayout rlSecondPassword;
    @BindView(R.id.bt_ok)
    Button btOk;

    private boolean isHidden = true;
    private boolean isHidden1 = true;
    private String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_password);
        ButterKnife.bind(this);

        // 添加到Activity集合
        FSApplication.instance.addActivity(this);

        // 系统 6.0 以上 状态栏白底黑字的实现方法
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        StaturBar.MIUISetStatusBarLightMode(getWindow(), true);
        StaturBar.FlymeSetStatusBarLightMode(getWindow(), true);

        userid = FSApplication.instance.sp.getString("userid", "");

        tvFirstPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (tvFirstPassword.length() != 0 && tvSecondPassword.length() != 0) {
                    btOk.setBackgroundResource(R.drawable.bt_altername1);
                } else {
                    btOk.setBackgroundResource(R.drawable.bt_altername);
                }


            }
        });

        tvSecondPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (tvFirstPassword.length() != 0 && tvSecondPassword.length() != 0) {
                    btOk.setBackgroundResource(R.drawable.bt_altername1);
                } else {
                    btOk.setBackgroundResource(R.drawable.bt_altername);
                }


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
            int[] l = {0, 0};
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


    @OnClick({R.id.bt_back, R.id.first_bt_hiddenmima, R.id.second_bt_hiddenmima, R.id.bt_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_back:
                finish();
                break;
            case R.id.first_bt_hiddenmima:

                if (isHidden) {
                    //设置EditText文本为可见的
                    tvFirstPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                    firstBtHiddenmima1.setVisibility(View.GONE);
                } else {
                    //设置EditText文本为隐藏的
                    tvFirstPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    firstBtHiddenmima1.setVisibility(View.VISIBLE);
                }
                isHidden = !isHidden;
                tvFirstPassword.postInvalidate();
                //切换后将EditText光标置于末尾
                CharSequence charSequence = tvFirstPassword.getText();
                if (charSequence instanceof Spannable) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText, charSequence.length());
                }

                break;
            case R.id.second_bt_hiddenmima:

                if (isHidden1) {
                    //设置EditText文本为可见的
                    tvSecondPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                    secondBtHiddenmima1.setVisibility(View.GONE);
                } else {
                    //设置EditText文本为隐藏的
                    tvSecondPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    secondBtHiddenmima1.setVisibility(View.VISIBLE);
                }
                isHidden1 = !isHidden1;
                tvFirstPassword.postInvalidate();
                //切换后将EditText光标置于末尾
                CharSequence charSequence1 = tvFirstPassword.getText();
                if (charSequence1 instanceof Spannable) {
                    Spannable spanText = (Spannable) charSequence1;
                    Selection.setSelection(spanText, charSequence1.length());
                }


                break;
            case R.id.bt_ok:


                break;
        }
    }
}
