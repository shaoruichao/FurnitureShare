package com.example.FurnitureShare.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.FurnitureShare.R;
import com.example.FurnitureShare.app.AllUrl;
import com.example.FurnitureShare.app.FSApplication;
import com.example.FurnitureShare.base.BaseActivity;
import com.example.FurnitureShare.entry.CreditList;
import com.example.FurnitureShare.entry.Ifcer;
import com.example.FurnitureShare.entry.Ifsesame;
import com.example.FurnitureShare.nohttp.HttpListener;
import com.example.FurnitureShare.utils.DataUtils;
import com.example.FurnitureShare.utils.JsonUtil;
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
 * 信用认证
 */
public class CreditActivity extends BaseActivity {

    private static final String TAG = "CreditActivity";

    @BindView(R.id.bt_back)
    Button btBack;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv_warehouse)
    ImageView ivWarehouse;
    @BindView(R.id.rl_realname)
    RelativeLayout rlRealname;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.iv_sesame)
    ImageView ivSesame;
    @BindView(R.id.rl_sesame)
    RelativeLayout rlSesame;
    @BindView(R.id.tv_realname)
    TextView tvRealname;
    @BindView(R.id.tv_sesame)
    TextView tvSesame;
    private String userid;

    private int sesamestatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);
        ButterKnife.bind(this);

        // 添加到Activity集合
        FSApplication.instance.addActivity(this);

        // 系统 6.0 以上 状态栏白底黑字的实现方法
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        StaturBar.MIUISetStatusBarLightMode(getWindow(), true);
        StaturBar.FlymeSetStatusBarLightMode(getWindow(), true);



    }

    @Override
    protected void onResume() {
        super.onResume();

        userid = FSApplication.instance.sp.getString("userid", "");

        PostCertificationlist();

    }

    private void PostCertificationlist() {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.CERTIFICATIONLIST, RequestMethod.POST);
        if (request != null) {
            request.add("uid", userid);

            // 添加到请求队列
            request(0, request, listobjectListener, true, true);
        }
    }

    private HttpListener<JSONObject> listobjectListener = new HttpListener<JSONObject>() {


        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            try {

                JSONObject js = response.get();
                Log.e(TAG, "listobjectListener: " + js);
                CreditList creditList = JsonUtil.parseJsonToBean(js.toString(), CreditList.class);
                int code = js.getInt("code");
                if (code == 200) {
                    if (creditList != null) {
                        CreditList.DataBean data = creditList.getData();
                        String authenticationstatus = data.getAuthenticationstatus();
                        if (authenticationstatus.equals("0")){
                            tvRealname.setText("未认证");
                        }else if (authenticationstatus.equals("1")){
                            tvRealname.setText("审核中");
                        }else if (authenticationstatus.equals("2")){
                            tvRealname.setText("审核通过");
                        }

                        sesamestatus = data.getSesamestatus();
                        if (sesamestatus == 0){
                            tvSesame.setText("未认证");
                        }else if (sesamestatus == 1){
                            tvSesame.setText("审核中");
                        }else if (sesamestatus == 2){
                            tvSesame.setText("审核通过");
                        }

                    }

                } else {
                    startActivity(new Intent(getBaseContext(), CertificationActivity.class));
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

    @OnClick({R.id.bt_back, R.id.rl_realname, R.id.rl_sesame})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_back:
                finish();
                break;
            case R.id.rl_realname:
                Ifcertification();
                break;
            case R.id.rl_sesame:
                if (sesamestatus == 0){
//                    tvSesame.setText("未认证");
                    startActivity(new Intent(this,SesameActivity.class));
                }else{
                    Ifsesame();
                }
                break;
        }
    }

    //实名认证信息
    private void Ifsesame() {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.IFSESAME, RequestMethod.POST);
        if (request != null) {
            request.add("uid", userid);

            // 添加到请求队列
            request(0, request, ifsesameobjectListener, true, true);
        }
    }
    private HttpListener<JSONObject> ifsesameobjectListener = new HttpListener<JSONObject>() {


        private String edittime;
        private String creattime;
        private String aliname;
        private Ifsesame.DataBean data;
        private String edittimeToString;
        private String creattimeToString;

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            try {

                JSONObject js = response.get();
                Log.e(TAG, "ifsesameobjectListener: " + js);
                Ifsesame ifsesame = JsonUtil.parseJsonToBean(js.toString(), Ifsesame.class);

                int code = js.getInt("code");
                if (code == 200){
                    if (ifsesame != null){
                        data = ifsesame.getData();
                        aliname = data.getAliname();
                        creattime = data.getCreattime();
                        edittime = data.getEdittime();
                        creattimeToString = DataUtils.getDateToString(Long.parseLong(creattime + "000"));
                        edittimeToString = DataUtils.getDateToString(Long.parseLong(edittime + "000"));

                        Intent intent = new Intent();
                        intent.putExtra("ifsesamestatus", String.valueOf(sesamestatus));
                        intent.putExtra("creattimeToString", creattimeToString);
                        intent.putExtra("edittimeToString", edittimeToString);
                        intent.setClass(getBaseContext(), SesameingActivity.class);
                        startActivity(intent);


                    }
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

    //实名认证信息
    private void Ifcertification() {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.IFCERTIFICATION, RequestMethod.POST);
        if (request != null) {
            request.add("uid", userid);

            // 添加到请求队列
            request(0, request, ifcerobjectListener, true, true);
        }
    }

    private HttpListener<JSONObject> ifcerobjectListener = new HttpListener<JSONObject>() {


        private String ifcerstatus;
        private Ifcer.DataBean data;
        private String edittimeToString;
        private String creattimeToString;
        private String edittime;
        private String creattime;

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            try {

                JSONObject js = response.get();
                Log.e(TAG, "ifcerobjectListener: " + js);
                Ifcer ifcer = JsonUtil.parseJsonToBean(js.toString(), Ifcer.class);

                int code = js.getInt("code");
                if (code == 200) {
                    if (ifcer != null) {
                        data = ifcer.getData();
                        ifcerstatus = data.getStatus();
                        creattime = data.getCreattime();
                        edittime = data.getEdittime();
                        creattimeToString = DataUtils.getDateToString(Long.parseLong(creattime + "000"));
                        edittimeToString = DataUtils.getDateToString(Long.parseLong(edittime + "000"));

                        if (ifcerstatus.equals("0")) {
                            startActivity(new Intent(getBaseContext(), CertificationActivity.class));
                        } else if (ifcerstatus.equals("1")) {//审核中
                            Intent intent = new Intent();
                            intent.putExtra("ifcerstatus", "1");
                            intent.putExtra("creattimeToString", creattimeToString);
                            intent.setClass(getBaseContext(), CertificationingActivity.class);
                            startActivity(intent);
                        } else if (ifcerstatus.equals("2")) {
                            Intent intent = new Intent();
                            intent.putExtra("ifcerstatus", "2");
                            intent.putExtra("creattimeToString", creattimeToString);
                            intent.putExtra("edittimeToString", edittimeToString);
                            intent.setClass(getBaseContext(), CertificationingActivity.class);
                            startActivity(intent);
                        }

                    }
                } else {
                    startActivity(new Intent(getBaseContext(), CertificationActivity.class));
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
