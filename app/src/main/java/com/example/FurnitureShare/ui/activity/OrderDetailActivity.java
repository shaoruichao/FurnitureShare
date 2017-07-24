package com.example.FurnitureShare.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.FurnitureShare.R;
import com.example.FurnitureShare.adapter.OrderDetailAdapter;
import com.example.FurnitureShare.app.AllUrl;
import com.example.FurnitureShare.app.FSApplication;
import com.example.FurnitureShare.base.BaseActivity;
import com.example.FurnitureShare.entry.OrderDetail;
import com.example.FurnitureShare.entry.OrderEdit;
import com.example.FurnitureShare.nohttp.HttpListener;
import com.example.FurnitureShare.utils.DataUtils;
import com.example.FurnitureShare.utils.JsonUtil;
import com.example.FurnitureShare.utils.StaturBar;
import com.example.FurnitureShare.utils.ToastUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.PostRequest;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.example.FurnitureShare.app.FSApplication.mContext;

/**
 * 订单详情
 */
public class OrderDetailActivity extends BaseActivity {

    private static final String TAG = "OrderDetailActivity";
    @BindView(R.id.bt_back)
    Button btBack;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_cancle)
    TextView tvCancle;
    @BindView(R.id.rl_number)
    RelativeLayout rlNumber;
    @BindView(R.id.tv_merchants)
    TextView tvMerchants;
    @BindView(R.id.rv_order_detail)
    RecyclerView rvOrderDetail;
    @BindView(R.id.bt_refund)
    Button btRefund;
    @BindView(R.id.rl_refund)
    RelativeLayout rlRefund;
    @BindView(R.id.bt_cancelorder)
    Button btCancelorder;
    @BindView(R.id.bt_pay)
    Button btPay;
    @BindView(R.id.rl_operation)
    RelativeLayout rlOperation;
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
    @BindView(R.id.tv6)
    TextView tv6;
    @BindView(R.id.tv6_colon)
    TextView tv6Colon;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.rl_freight)
    RelativeLayout rlFreight;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.tv5_colon)
    TextView tv5Colon;
    @BindView(R.id.tv_shoptotal)
    TextView tvShoptotal;
    @BindView(R.id.rl_shoptotal)
    RelativeLayout rlShoptotal;
    @BindView(R.id.tv8)
    TextView tv8;
    @BindView(R.id.tv5_foregift)
    TextView tv5Foregift;
    @BindView(R.id.tv_foregift)
    TextView tvForegift;
    @BindView(R.id.rl_foregift)
    RelativeLayout rlForegift;
    @BindView(R.id.tv7)
    TextView tv7;
    @BindView(R.id.tv7_colon)
    TextView tv7Colon;
    @BindView(R.id.tv_realpay)
    TextView tvRealpay;
    @BindView(R.id.rl_realpay)
    RelativeLayout rlRealpay;
    @BindView(R.id.rl)
    LinearLayout rl;


    private String orderid;
    private String orderstatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);
        // 添加到Activity集合
        FSApplication.instance.addActivity(this);

        // 系统 6.0 以上 状态栏白底黑字的实现方法
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        StaturBar.MIUISetStatusBarLightMode(getWindow(), true);
        StaturBar.FlymeSetStatusBarLightMode(getWindow(), true);

        Intent intent = getIntent();
        orderid = intent.getStringExtra("orderid");
        orderstatus = intent.getStringExtra("orderstatus");
        if (orderstatus.equals("1")) {
            rlRefund.setVisibility(View.GONE);
            tvCancle.setVisibility(View.GONE);
        } else if (orderstatus.equals("2")) {
            rlOperation.setVisibility(View.GONE);
            tvCancle.setVisibility(View.GONE);
        } else if (orderstatus.equals("5")) {
            rlOperation.setVisibility(View.GONE);
            rlRefund.setVisibility(View.GONE);
        }
        PostOrderDetail();

    }

    private void PostOrderDetail() {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.ORDERDETAIL, RequestMethod.POST);
        if (request != null) {
            request.add("id", orderid);
            request.add("dosubmit", 1);

            // 添加到请求队列
            request(0, request, orderdetailobjectListener, true, true);
        }
    }

    private HttpListener<JSONObject> orderdetailobjectListener = new HttpListener<JSONObject>() {

        private String orderid;
        private String price;
        private String pay_way;
        private String freight;
        private String detail;
        private String province;
        private String country;
        private String city;
        private String telnumber;
        private String linkman;
        private OrderDetail.DataBean.AddressinfoBean addressinfo;
        private OrderDetailAdapter orderDetailAdapter;
        private List<OrderDetail.DataBean.GoodsinfoBean> goodsinfo;
        private String shopname;
        private String number;
        private String dateToString;
        private String creattime;
        private OrderDetail.DataBean.OrderinfoBean orderinfo;
        private OrderDetail.DataBean data;

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            try {

                JSONObject js = response.get();
                Log.e(TAG, "orderdetailobjectListener: " + js);
                int code = js.getInt("code");
                OrderDetail orderDetail = JsonUtil.parseJsonToBean(js.toString(), OrderDetail.class);
                if (code == 200) {
                    data = orderDetail.getData();
                    orderinfo = data.getOrderinfo();//订单状态
                    creattime = orderinfo.getCreattime();//下单时间
                    dateToString = DataUtils.getDateToString(Long.parseLong(creattime));
                    tvTime.setText(dateToString);
                    number = orderinfo.getNumber();//编号
                    tvNumber.setText(number);
//                    pay_way = orderinfo.getPay_way();

                    price = orderinfo.getPrice();
                    tvShoptotal.setText("¥" + price);
                    freight = orderinfo.getFreight();
//                    tvFreight.setText("¥" + freight);


                    shopname = data.getShopname();
                    tvMerchants.setText(shopname);

                    goodsinfo = data.getGoodsinfo();
                    orderDetailAdapter = new OrderDetailAdapter(goodsinfo);
                    orderDetailAdapter.openLoadAnimation();
                    rvOrderDetail.setAdapter(orderDetailAdapter);
                    rvOrderDetail.setHasFixedSize(true);
                    rvOrderDetail.setLayoutManager(new LinearLayoutManager(mContext));

                    addressinfo = data.getAddressinfo();//订单信息
                    orderid = addressinfo.getId();
                    linkman = addressinfo.getLinkman();
                    tvName.setText(linkman);
                    telnumber = addressinfo.getTelnumber();
                    tvPhone.setText(telnumber);
                    province = addressinfo.getProvince();
                    city = addressinfo.getCity();
                    country = addressinfo.getCountry();
                    detail = addressinfo.getDetail();
                    tvAddress.setText(province + city + country + detail);


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


    @OnClick({R.id.bt_back, R.id.bt_refund, R.id.bt_cancelorder, R.id.bt_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_back:
                finish();
                break;
            case R.id.bt_refund:
                break;
            case R.id.bt_cancelorder:
                OrderEdit(orderid);
                break;
            case R.id.bt_pay:
                break;
        }
    }

    private void OrderEdit(String orderid) {
        PostRequest tag = OkGo.post(AllUrl.ORDEREDIT).tag(this);
        tag.params("dosubmit", 1);
        tag.params("status", 5);
        tag.params("id", orderid);

        tag.execute(new StringCallback() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                Log.e(TAG, "onSuccess1: " + response.body().toString());
                OrderEdit orderEdit = JsonUtil.parseJsonToBean(response.body().toString(), OrderEdit.class);
                int code = orderEdit.getCode();
                if (code == 200) {
                    ToastUtil.showToast(getBaseContext(), "取消订单成功");
                    finish();
                }
            }
        });
    }

}
