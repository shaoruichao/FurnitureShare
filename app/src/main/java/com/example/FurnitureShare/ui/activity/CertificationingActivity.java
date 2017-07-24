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
import com.example.FurnitureShare.entry.Ifcer;
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
 * 实名认证信息
 */
public class CertificationingActivity extends BaseActivity {

    private static final String TAG = "CertificationingActivit";
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
    private String userid;

    private String ifcerstatus;
    private Ifcer.DataBean data;
    private String ifcerstatus1;
    private String creattimeToString;
    private String edittimeToString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificationing);
        ButterKnife.bind(this);

        // 添加到Activity集合
        FSApplication.instance.addActivity(this);

        // 系统 6.0 以上 状态栏白底黑字的实现方法
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        StaturBar.MIUISetStatusBarLightMode(getWindow(), true);
        StaturBar.FlymeSetStatusBarLightMode(getWindow(), true);

        userid = FSApplication.instance.sp.getString("userid", "");
//        Ifcertification();

        Intent intent = getIntent();
        ifcerstatus = intent.getStringExtra("ifcerstatus");
        creattimeToString = intent.getStringExtra("creattimeToString");
        edittimeToString = intent.getStringExtra("edittimeToString");


        if (ifcerstatus.equals("1")) {//审核中
            iv.setImageResource(R.mipmap.cering);
            iv1.setImageResource(R.mipmap.cersucess);
            iv2.setImageResource(R.mipmap.cerings);
            iv3.setImageResource(R.mipmap.nocer);

            tvCreattime.setText(creattimeToString);
            tvCreattime1.setText(creattimeToString);
        } else if (ifcerstatus.equals("2")) {
            iv.setImageResource(R.mipmap.cerok);
            iv1.setImageResource(R.mipmap.cersucess);
            iv2.setImageResource(R.mipmap.cersucess);
            iv3.setImageResource(R.mipmap.cerings);

            tvCreattime.setText(creattimeToString);
            tvCreattime1.setText(creattimeToString);
            tvEdittime.setText(edittimeToString);
        }
    }

//    private void Ifcertification() {
//        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.IFCERTIFICATION, RequestMethod.POST);
//        if (request != null) {
//            request.add("uid", userid);
//
//            // 添加到请求队列
//            request(0, request, ifcerobjectListener, true, true);
//        }
//    }
//
//    private HttpListener<JSONObject> ifcerobjectListener = new HttpListener<JSONObject>() {
//
//
//        private String edittimeToString;
//        private String creattimeToString;
//        private String edittime;
//        private String creattime;
//
//        @Override
//        public void onSucceed(int what, Response<JSONObject> response) {
//            try {
//
//                JSONObject js = response.get();
//                Log.e(TAG, "ifcerobjectListener: " + js);
//                Ifcer ifcer = JsonUtil.parseJsonToBean(js.toString(), Ifcer.class);
//
//                if (ifcer != null) {
//                    data = ifcer.getData();
//                    ifcerstatus = data.getStatus();
//                    creattime = data.getCreattime();
//                    edittime = data.getEdittime();
//                    creattimeToString = DataUtils.getDateToString(Long.parseLong(creattime));
//                    edittimeToString = DataUtils.getDateToString(Long.parseLong(edittime));
//
//                    if (ifcerstatus.equals("1")){//审核中
//                        iv.setImageResource(R.mipmap.cering);
//                        iv1.setImageResource(R.mipmap.cersucess);
//                        iv2.setImageResource(R.mipmap.cerings);
//                        iv3.setImageResource(R.mipmap.nocer);
//
//
//                        tvCreattime.setText(creattimeToString);
//                        tvCreattime1.setText(creattimeToString);
//                    }else if (ifcerstatus.equals("2")){
//                        iv.setImageResource(R.mipmap.cerok);
//                        iv1.setImageResource(R.mipmap.cersucess);
//                        iv2.setImageResource(R.mipmap.cersucess);
//                        iv3.setImageResource(R.mipmap.cerings);
//
//                        tvCreattime.setText(creattimeToString);
//                        tvCreattime1.setText(creattimeToString);
//                        tvEdittime.setText(edittimeToString);
//                    }
//
//                }
//
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//
//        @Override
//        public void onFailed(int what, Response<JSONObject> response) {
//
//            ToastUtil.showToast(getBaseContext(), "访问网络失败，请检查您的网络！");
//
//        }
//    };


    @OnClick(R.id.bt_back)
    public void onViewClicked() {
        finish();
    }
}
