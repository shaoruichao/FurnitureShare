package com.example.FurnitureShare.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.FurnitureShare.R;
import com.example.FurnitureShare.app.AllUrl;
import com.example.FurnitureShare.app.FSApplication;
import com.example.FurnitureShare.base.BaseActivity;
import com.example.FurnitureShare.nohttp.HttpListener;
import com.example.FurnitureShare.utils.StaturBar;
import com.example.FurnitureShare.utils.ToastUtil;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改昵称
 */
public class AlterNameActivity extends BaseActivity {

    private static final String TAG = "AlterNameActivity";

    @BindView(R.id.bt_back)
    Button btBack;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_head_title)
    TextView tvHeadTitle;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.text1)
    TextInputLayout text1;
    @BindView(R.id.bt_ok)
    Button btOk;
    @BindView(R.id.rl_altername)
    RelativeLayout rlAltername;

    private String userid;
    private String nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_name);
        ButterKnife.bind(this);
        // 添加到Activity集合
        FSApplication.instance.addActivity(this);

        // 系统 6.0 以上 状态栏白底黑字的实现方法
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        StaturBar.MIUISetStatusBarLightMode(getWindow(), true);
        StaturBar.FlymeSetStatusBarLightMode(getWindow(), true);

        userid = FSApplication.instance.sp.getString("userid", "");

        tvName.addTextChangedListener(new TextWatcher() {
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

                if (tvName.length() != 0) {
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

                rlAltername.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        rlAltername.setFocusable(true);
                        rlAltername.setFocusableInTouchMode(true);
                        rlAltername.requestFocus();
                        return true;
                    }
                });

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


    @OnClick({R.id.bt_back, R.id.bt_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_back:
                finish();
                break;
            case R.id.bt_ok:

                nickname = tvName.getText().toString();
                if (!nickname.equals("")) {

                    getUpdate(nickname);

                } else {
                    ToastUtil.showToast(getBaseContext(), "请输入昵称");
                }

                break;
        }
    }

    //修改个人资料
    private void getUpdate(String nickname) {

        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.UPDATEPERSONAL, RequestMethod.POST);
        if (request != null) {
            request.add("uid", userid);
            request.add("nickname", nickname);

            // 添加到请求队列
            request(0, request, nameobjectListener, true, true);
        }

    }

    private HttpListener<JSONObject> nameobjectListener = new HttpListener<JSONObject>() {


        @Override
        public void onSucceed(int what, Response<JSONObject> response) {


            try {
                JSONObject js = response.get();
                Log.e(TAG, "onSucceed123: " + js);

                int code = js.getInt("code");
                String message = js.getString("message");

                if (code == 200) {

                    SharedPreferences.Editor edit = FSApplication.instance.sp.edit();
                    edit.putString("nickname", nickname);
                    edit.commit();

                    Toast toast = Toast.makeText(getApplicationContext(),
                            "修改成功", Toast.LENGTH_SHORT);
                    //放在左上角。如果你想往右边移动，将第二个参数设为>0；往下移动，增大第三个参数；后两个参数都只得像素
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    finish();

                }
                if (code == 0) {
                    ToastUtil.showToast(getBaseContext(), message);
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

}
