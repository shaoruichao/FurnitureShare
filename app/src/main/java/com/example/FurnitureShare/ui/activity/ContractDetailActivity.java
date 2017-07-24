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
import com.example.FurnitureShare.entry.ContractDetail;
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
 * 合同详情
 */
public class ContractDetailActivity extends BaseActivity {

    private static final String TAG = "ContractDetailActivity";

    @BindView(R.id.bt_back)
    Button btBack;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title1)
    TextView tvTitle1;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.rl_number)
    RelativeLayout rlNumber;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_data)
    TextView tvData;
    @BindView(R.id.rl_data)
    RelativeLayout rlData;
    @BindView(R.id.tv_rent)
    TextView tvRent;
    @BindView(R.id.rl_rent)
    RelativeLayout rlRent;
    @BindView(R.id.tv_way)
    TextView tvWay;
    @BindView(R.id.rl_way)
    RelativeLayout rlWay;
    @BindView(R.id.iv_warehouse)
    ImageView ivWarehouse;
    @BindView(R.id.rl_information)
    RelativeLayout rlInformation;
    @BindView(R.id.iv_bill)
    ImageView ivBill;
    @BindView(R.id.rl_bill)
    RelativeLayout rlBill;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_detail);
        ButterKnife.bind(this);
        // 添加到Activity集合
        FSApplication.instance.addActivity(this);

        // 系统 6.0 以上 状态栏白底黑字的实现方法
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        StaturBar.MIUISetStatusBarLightMode(getWindow(), true);
        StaturBar.FlymeSetStatusBarLightMode(getWindow(), true);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        PostDetail();

    }

    private void PostDetail(){
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.CONTRACTDETAIL, RequestMethod.POST);
        if (request != null) {
            request.add("dosubmit",1);
            request.add("id", id);

            // 添加到请求队列
            request(0, request, cdobjectListener, true, true);
        }
    }

    private HttpListener<JSONObject> cdobjectListener = new HttpListener<JSONObject>() {


        private String price;
        private String endtime;
        private String begintime;
        private String title;
        private String number;
        private ContractDetail.DataBean data;

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            try {

                JSONObject js = response.get();
                Log.e(TAG, "ifcerobjectListener: " + js);
                int code = js.getInt("code");
                ContractDetail contractDetail = JsonUtil.parseJsonToBean(js.toString(), ContractDetail.class);

                if (code == 200){
                    if (contractDetail != null){
                        data = contractDetail.getData();
                        number = data.getNumber();
                        tvNumber.setText(number);
                        title = data.getTitle();
                        tvTitle.setText(title);
                        begintime = data.getBegintime();
                        endtime = data.getEndtime();
                        tvData.setText(begintime+"-"+endtime);
                        price = data.getPrice();
                        tvRent.setText("¥"+price+"/月");

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

    @OnClick({R.id.bt_back, R.id.rl_information, R.id.rl_bill})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_back:
                finish();
                break;
            case R.id.rl_information:
                break;
            case R.id.rl_bill:
                break;
        }
    }
}
