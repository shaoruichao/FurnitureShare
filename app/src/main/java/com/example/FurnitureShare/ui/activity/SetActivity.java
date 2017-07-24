package com.example.FurnitureShare.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.FurnitureShare.R;
import com.example.FurnitureShare.app.FSApplication;
import com.example.FurnitureShare.base.BaseActivity;
import com.example.FurnitureShare.utils.DataCleanManager;
import com.example.FurnitureShare.utils.StaturBar;
import com.example.FurnitureShare.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 设置
 */
public class SetActivity extends BaseActivity {

    @BindView(R.id.bt_back)
    Button btBack;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
    @BindView(R.id.tv_wifi)
    TextView tvWifi;
    @BindView(R.id.iv_switch)
    ImageView ivSwitch;
    @BindView(R.id.rl_phone)
    RelativeLayout rlPhone;
    @BindView(R.id.tv_remove)
    TextView tvRemove;
    @BindView(R.id.tv_cache)
    TextView tvCache;
    @BindView(R.id.bt_remove)
    Button btRemove;
    @BindView(R.id.rl_remove)
    RelativeLayout rlRemove;
    @BindView(R.id.tv_msg)
    TextView tvMsg;
    @BindView(R.id.iv_switch1)
    ImageView ivSwitch1;
    @BindView(R.id.rl_msg)
    RelativeLayout rlMsg;
    @BindView(R.id.iv_about)
    ImageView ivAbout;
    @BindView(R.id.rl_about)
    RelativeLayout rlAbout;
    @BindView(R.id.iv_recommended)
    ImageView ivRecommended;
    @BindView(R.id.rl_recommended)
    RelativeLayout rlRecommended;
    @BindView(R.id.iv_agreement)
    ImageView ivAgreement;
    @BindView(R.id.rl_agreement)
    RelativeLayout rlAgreement;
    @BindView(R.id.iv_update)
    ImageView ivUpdate;
    @BindView(R.id.rl_update)
    RelativeLayout rlUpdate;
    @BindView(R.id.tv_update)
    TextView tvUpdate;

    private String mark_switch;
    private String mark_switch1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        ButterKnife.bind(this);

        // 添加到Activity集合
        FSApplication.instance.addActivity(this);

        // 系统 6.0 以上 状态栏白底黑字的实现方法
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        StaturBar.MIUISetStatusBarLightMode(getWindow(), true);
        StaturBar.FlymeSetStatusBarLightMode(getWindow(), true);

        mark_switch = FSApplication.instance.sp.getString("mark_switch", mark_switch);
        if (mark_switch != null && !mark_switch.equals("")) {
            if (mark_switch.equals("1")) {
                ivSwitch.setImageResource(R.drawable.guan);
            } else {
                ivSwitch.setImageResource(R.drawable.kai);
            }
        }

        mark_switch1 = FSApplication.instance.sp.getString("mark_switch1", mark_switch1);
        if (mark_switch1 != null && !mark_switch1.equals("")) {
            if (mark_switch1.equals("1")) {
                ivSwitch1.setImageResource(R.drawable.guan);
            } else {
                ivSwitch1.setImageResource(R.drawable.kai);
            }
        }

        //计算缓存
        try {
            String totalCacheSize = DataCleanManager.getTotalCacheSize(getApplicationContext());
            tvCache.setText("(" + totalCacheSize + ")");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @OnClick({R.id.bt_back, R.id.iv_switch, R.id.bt_remove, R.id.iv_switch1, R.id.rl_about, R.id.rl_recommended, R.id.rl_agreement, R.id.rl_update})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_back:
                finish();
                break;
            case R.id.iv_switch:
                if (mark_switch != null && !mark_switch.equals("")) {
                    if (mark_switch.equals("0")) {
                        ivSwitch.setImageResource(R.drawable.guan);
                        mark_switch = "1";

                    } else {
                        ivSwitch.setImageResource(R.drawable.kai);
                        mark_switch = "0";
                    }
                } else {
                    ivSwitch.setImageResource(R.drawable.kai);
                    mark_switch = "0";
                }

                SharedPreferences.Editor edit = FSApplication.instance.sp.edit();
                edit.putString("mark_switch", mark_switch);
                edit.commit();

                break;
            case R.id.bt_remove:

                DataCleanManager.clearAllCache(getApplicationContext());
                tvCache.setText("");
                ToastUtil.showToast(getBaseContext(),"清除完毕");

                break;
            case R.id.iv_switch1:

                if (mark_switch1 != null && !mark_switch1.equals("")) {
                    if (mark_switch1.equals("0")) {
                        ivSwitch1.setImageResource(R.drawable.guan);
                        mark_switch1 = "1";

                    } else {
                        ivSwitch1.setImageResource(R.drawable.kai);
                        mark_switch1 = "0";
                    }
                } else {
                    ivSwitch1.setImageResource(R.drawable.kai);
                    mark_switch1 = "0";
                }

                SharedPreferences.Editor edit1 = FSApplication.instance.sp.edit();
                edit1.putString("mark_switch1", mark_switch1);
                edit1.commit();

                break;
            case R.id.rl_about:

                startActivity(new Intent(this,AboutActivity.class));

                break;
            case R.id.rl_recommended:
                break;
            case R.id.rl_agreement:

                startActivity(new Intent(this,AgreementActivity.class));

                break;
            case R.id.rl_update:
                break;
        }
    }
}
