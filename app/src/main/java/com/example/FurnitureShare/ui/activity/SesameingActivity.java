package com.example.FurnitureShare.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
 * 芝麻信用认证信息
 */
public class SesameingActivity extends BaseActivity {

    @BindView(R.id.bt_back)
    Button btBack;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.iv3)
    ImageView iv3;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv_creattime)
    TextView tvCreattime;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv_creattime1)
    TextView tvCreattime1;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv_edittime)
    TextView tvEdittime;

    private String ifsesamestatus;
    private String creattimeToString;
    private String edittimeToString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesameing);
        ButterKnife.bind(this);

        // 添加到Activity集合
        FSApplication.instance.addActivity(this);

        // 系统 6.0 以上 状态栏白底黑字的实现方法
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        StaturBar.MIUISetStatusBarLightMode(getWindow(), true);
        StaturBar.FlymeSetStatusBarLightMode(getWindow(), true);

        Intent intent = getIntent();
        ifsesamestatus = intent.getStringExtra("ifsesamestatus");
        creattimeToString = intent.getStringExtra("creattimeToString");
        edittimeToString = intent.getStringExtra("edittimeToString");


        if (ifsesamestatus.equals("1")) {//审核中
            iv.setImageResource(R.mipmap.cering);
            iv1.setImageResource(R.mipmap.cersucess);
            iv2.setImageResource(R.mipmap.cerings);
            iv3.setImageResource(R.mipmap.nocer);

            tvCreattime.setText(creattimeToString);
            tvCreattime1.setText(creattimeToString);
        } else if (ifsesamestatus.equals("2")) {
            iv.setImageResource(R.mipmap.cerok);
            iv1.setImageResource(R.mipmap.cersucess);
            iv2.setImageResource(R.mipmap.cersucess);
            iv3.setImageResource(R.mipmap.cerings);

            tvCreattime.setText(creattimeToString);
            tvCreattime1.setText(creattimeToString);
            tvEdittime.setText(edittimeToString);
        }


    }

    @OnClick(R.id.bt_back)
    public void onViewClicked() {
        finish();
    }
}
