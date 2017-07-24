package com.example.FurnitureShare.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.FurnitureShare.R;
import com.example.FurnitureShare.app.FSApplication;
import com.example.FurnitureShare.base.BaseActivity;
import com.example.FurnitureShare.entry.UserMessage;
import com.example.FurnitureShare.ui.HomeActivity;
import com.example.FurnitureShare.utils.SharePreferencesUtils;
import com.example.FurnitureShare.utils.StaturBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaySuccessfulActivity extends BaseActivity {

    private static final String TAG = "PaySuccessfulActivity";

    @BindView(R.id.bt_back)
    Button btBack;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.rl1)
    RelativeLayout rl1;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.bt_lookorder)
    Button btLookorder;
    @BindView(R.id.bt_gohome)
    Button btGohome;
    @BindView(R.id.rl2)
    RelativeLayout rl2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.shippinginfor)
    RelativeLayout shippinginfor;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tv_pay)
    TextView tvPay;
    @BindView(R.id.rl_pay)
    RelativeLayout rlPay;
    @BindView(R.id.tv7)
    TextView tv7;
    @BindView(R.id.tv_realpay)
    TextView tvRealpay;
    @BindView(R.id.rl_realpay)
    RelativeLayout rlRealpay;

    private UserMessage userMessage;
    private List<UserMessage.DataBean.AddressBean> address;
    private String addrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_successful);
        ButterKnife.bind(this);

        // 添加到Activity集合
        FSApplication.instance.addActivity(this);

        // 系统 6.0 以上 状态栏白底黑字的实现方法
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        StaturBar.MIUISetStatusBarLightMode(getWindow(), true);
        StaturBar.FlymeSetStatusBarLightMode(getWindow(), true);

        Intent intent = getIntent();
        String way = intent.getStringExtra("way");
        if (way.equals("ali")){
            tvPay.setText("支付宝");
        }else if (way.equals("wx")){
            tvPay.setText("微信");
        }

        String result = intent.getStringExtra("result");
        tvRealpay.setText(result);

    }

    @Override
    protected void onResume() {
        super.onResume();

        userMessage = (UserMessage) SharePreferencesUtils.getBean(
                getBaseContext(), "userMessage");
        Log.e(TAG, "onResume: " + userMessage);
        if (userMessage != null) {
            address = userMessage.getData().getAddress();
            if (!address.isEmpty()) {

                shippinginfor.setVisibility(View.VISIBLE);

                addrid = address.get(0).getId();
                String linkman = address.get(0).getLinkman();
                tvName.setText(linkman);
                String telnumber = address.get(0).getTelnumber();
                tvPhone.setText(telnumber);
                String province = address.get(0).getProvince();
                String city = address.get(0).getCity();
                String country = address.get(0).getCountry();
                String detail = address.get(0).getDetail();
                tvAddress.setText(province + city + country + detail);

            }
        }

    }

    @OnClick({R.id.bt_back, R.id.bt_lookorder, R.id.bt_gohome})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_back:
                startActivity(new Intent(PaySuccessfulActivity.this, HomeActivity.class));
                PaySuccessfulActivity.this.finish();
                FSApplication.instance.exit();
                break;
            case R.id.bt_lookorder:
                startActivity(new Intent(PaySuccessfulActivity.this, MyOrderActivity.class));
                break;
            case R.id.bt_gohome:
                startActivity(new Intent(PaySuccessfulActivity.this, HomeActivity.class));
                PaySuccessfulActivity.this.finish();
                FSApplication.instance.exit();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {


        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(PaySuccessfulActivity.this, HomeActivity.class));
            PaySuccessfulActivity.this.finish();
            FSApplication.instance.exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

}
