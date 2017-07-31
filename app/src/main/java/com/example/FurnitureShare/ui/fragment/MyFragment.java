package com.example.FurnitureShare.ui.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.FurnitureShare.R;
import com.example.FurnitureShare.app.AllUrl;
import com.example.FurnitureShare.app.FSApplication;
import com.example.FurnitureShare.base.BaseFragment;
import com.example.FurnitureShare.entry.Ifcer;
import com.example.FurnitureShare.entry.UserMessage;
import com.example.FurnitureShare.nohttp.HttpListener;
import com.example.FurnitureShare.ui.activity.AddressManageActivity;
import com.example.FurnitureShare.ui.activity.CertificationActivity;
import com.example.FurnitureShare.ui.activity.CertificationingActivity;
import com.example.FurnitureShare.ui.activity.CreditActivity;
import com.example.FurnitureShare.ui.activity.DataActivity;
import com.example.FurnitureShare.ui.activity.LoginActivity;
import com.example.FurnitureShare.ui.activity.MyContractActivity;
import com.example.FurnitureShare.ui.activity.MyOrderActivity;
import com.example.FurnitureShare.ui.activity.SetActivity;
import com.example.FurnitureShare.ui.activity.ShoppingActivity;
import com.example.FurnitureShare.utils.DataUtils;
import com.example.FurnitureShare.utils.JsonUtil;
import com.example.FurnitureShare.utils.SharePreferencesUtils;
import com.example.FurnitureShare.utils.ToastUtil;
import com.example.FurnitureShare.view.CircleImageView;
import com.squareup.picasso.Picasso;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 我的
 */
public class MyFragment extends BaseFragment {
    private static final String TAG = "MyFragment";
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.iv_myorder)
    ImageView ivMyorder;
    @BindView(R.id.rl_myorder)
    RelativeLayout rlMyorder;
    @BindView(R.id.iv_obligtion)
    ImageView ivObligtion;
    @BindView(R.id.rl_obligation)
    RelativeLayout rlObligation;
    @BindView(R.id.iv_sendgoods)
    ImageView ivSendgoods;
    @BindView(R.id.rl_sendgoods)
    RelativeLayout rlSendgoods;
    @BindView(R.id.iv_beenshipped)
    ImageView ivBeenshipped;
    @BindView(R.id.rl_beenshipped)
    RelativeLayout rlBeenshipped;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv_warehouse)
    ImageView ivWarehouse;
    @BindView(R.id.rl_contract)
    RelativeLayout rlContract;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.iv_integral)
    ImageView ivIntegral;
    @BindView(R.id.rl_certification)
    RelativeLayout rlCertification;
    @BindView(R.id.iv3)
    ImageView iv3;
    @BindView(R.id.iv_address)
    ImageView ivAddress;
    @BindView(R.id.rl_address)
    RelativeLayout rlAddress;
    @BindView(R.id.iv4)
    ImageView iv4;
    @BindView(R.id.iv_goodsshelves)
    ImageView ivGoodsshelves;
    @BindView(R.id.rl_warranty)
    RelativeLayout rlWarranty;
    @BindView(R.id.iv5)
    ImageView iv5;
    @BindView(R.id.iv_set)
    ImageView ivSet;
    @BindView(R.id.rl_set)
    RelativeLayout rlSet;
    private View view;
    private String nickname;
    private String avatar;
    private String status;
    private String userid;
    private String realy;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_my, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        nickname = FSApplication.instance.sp.getString("nickname", "");
        avatar = FSApplication.instance.sp.getString("avatar", "");
        status = FSApplication.instance.sp.getString("status", "");
        userid = FSApplication.instance.sp.getString("userid", "");
        realy = FSApplication.instance.sp.getString("realy", "");

        if (nickname != null && !nickname.equals("")) {
            tvName.setText(nickname);
        }
        if (avatar != null && !avatar.equals("")) {
            Picasso.with(getActivity()).load(avatar).into(ivHead);
        }


    }


    @OnClick({R.id.iv_head, R.id.rl_myorder, R.id.rl_obligation, R.id.rl_sendgoods, R.id.rl_beenshipped, R.id.rl_contract, R.id.rl_certification, R.id.rl_address, R.id.rl_warranty, R.id.rl_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_head:

                if (status.equals("1")) {
                    startActivity(new Intent(getActivity(), DataActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.rl_myorder:
                if (status.equals("1")) {
                    startActivity(new Intent(getActivity(),MyOrderActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.rl_obligation:
                if (status.equals("1")) {
                    Intent intent = new Intent(getActivity(),MyOrderActivity.class);
                    intent.putExtra("userloginflag", 1);
                    startActivity(intent);
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.rl_sendgoods:
                if (status.equals("1")) {
                    Intent intent = new Intent(getActivity(),MyOrderActivity.class);
                    intent.putExtra("userloginflag", 2);
                    startActivity(intent);
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.rl_beenshipped:
                if (status.equals("1")) {
                    Intent intent = new Intent(getActivity(),MyOrderActivity.class);
                    intent.putExtra("userloginflag", 3);
                    startActivity(intent);
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.rl_contract:
                if (status.equals("1")) {
                    startActivity(new Intent(getActivity(), MyContractActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.rl_certification:
                if (status.equals("1")) {
//                    Ifcertification();
                    startActivity(new Intent(getActivity(), CreditActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.rl_address:
                if (status.equals("1")) {
                    startActivity(new Intent(getActivity(), AddressManageActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.rl_warranty:
                if (status.equals("1")) {
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.rl_set:
                if (status.equals("1")) {
                    startActivity(new Intent(getActivity(), SetActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
        }
    }

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
                if (code == 200){
                    if (ifcer != null) {
                        data = ifcer.getData();
                        ifcerstatus = data.getStatus();
                        creattime = data.getCreattime();
                        edittime = data.getEdittime();
                        creattimeToString = DataUtils.getDateToString(Long.parseLong(creattime+"000"));
                        edittimeToString = DataUtils.getDateToString(Long.parseLong(edittime+"000"));

                        if (ifcerstatus.equals("0")){
                            startActivity(new Intent(getActivity(),CertificationActivity.class));
                        }else if (ifcerstatus.equals("1")){//审核中
                            Intent intent = new Intent();
                            intent.putExtra("ifcerstatus","1");
                            intent.putExtra("creattimeToString",creattimeToString);
                            intent.setClass(getActivity(), CertificationingActivity.class);
                            startActivity(intent);
                        }else if (ifcerstatus.equals("2")){
                            Intent intent = new Intent();
                            intent.putExtra("ifcerstatus","2");
                            intent.putExtra("creattimeToString",creattimeToString);
                            intent.putExtra("edittimeToString",edittimeToString);
                            intent.setClass(getActivity(), CertificationingActivity.class);
                            startActivity(intent);
                        }

                    }
                }else {
                    startActivity(new Intent(getActivity(),CertificationActivity.class));
                }



            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {

            ToastUtil.showToast(getActivity(), "访问网络失败，请检查您的网络！");

        }
    };

}
