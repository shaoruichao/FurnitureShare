package com.example.FurnitureShare.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.FurnitureShare.R;
import com.example.FurnitureShare.app.AllUrl;
import com.example.FurnitureShare.app.FSApplication;
import com.example.FurnitureShare.base.BaseActivity;
import com.example.FurnitureShare.entry.AddressList;
import com.example.FurnitureShare.entry.UserMessage;
import com.example.FurnitureShare.nohttp.HttpListener;
import com.example.FurnitureShare.utils.JsonUtil;
import com.example.FurnitureShare.utils.SharePreferencesUtils;
import com.example.FurnitureShare.utils.StaturBar;
import com.example.FurnitureShare.utils.ToastUtil;
import com.example.FurnitureShare.view.addresspick.AddressPickTask;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;

/**
 * 新建地址
 */
public class AddressActivity extends BaseActivity {

    private static final String TAG = "AddressActivity";

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
    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.tv_phone)
    EditText tvPhone;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.tv_address)
    EditText tvAddress;
    @BindView(R.id.rl_goodsinfor)
    RelativeLayout rlGoodsinfor;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv_wx)
    TextView tvWx;
    @BindView(R.id.rl_default)
    RelativeLayout rlDefault;
    @BindView(R.id.bt_save)
    Button btSave;

    private boolean mark = true;
    private String status = "1";
    private String userid;
    private String addprovince;
    private String addcity;
    private String addcountry;
    private AddressList.DataBean dataBean;
    private String id;//收货id
    private String dataBeanstatus;//是否为默认地址的判断值

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.bind(this);
        // 添加到Activity集合
        FSApplication.instance.addActivity(this);

        // 系统 6.0 以上 状态栏白底黑字的实现方法
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        StaturBar.MIUISetStatusBarLightMode(getWindow(), true);
        StaturBar.FlymeSetStatusBarLightMode(getWindow(), true);

        userid = FSApplication.instance.sp.getString("userid", "");

        Intent intent = getIntent();
        dataBean = (AddressList.DataBean) intent.getSerializableExtra("dataBean");
        Log.e(TAG, "onCreate: "+dataBean );
        if (dataBean != null){
            String linkman = dataBean.getLinkman();
            tvName.setText(linkman);
            String telnumber = dataBean.getTelnumber();
            tvPhone.setText(telnumber);
            addprovince = dataBean.getProvince();
            addcity = dataBean.getCity();
            addcountry = dataBean.getCountry();
            tvArea.setText(addprovince + addcity + addcountry);
            String detail = dataBean.getDetail();
            tvAddress.setText(detail);
            dataBeanstatus = dataBean.getStatus();
            if (dataBeanstatus.equals("2")){
                iv.setImageResource(R.drawable.dot_check);
                status = "2";
                mark = false;
                rlDefault.setClickable(false);
            }else {
                iv.setImageResource(R.drawable.dot_uncheck);
                status = "1";
                mark = true;
            }
            id = dataBean.getId();
        }

    }

    @OnClick({R.id.bt_back, R.id.tv_area, R.id.rl_default, R.id.bt_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_back:
                finish();
                break;
            case R.id.tv_area:

                AddressPickTask task = new AddressPickTask(this);
                task.setHideProvince(false);
                task.setHideCounty(false);
                task.setCallback(new AddressPickTask.Callback() {
                    @Override
                    public void onAddressInitFailed() {
                        ToastUtil.showToast(getBaseContext(),"数据初始化失败");
                    }

                    @Override
                    public void onAddressPicked(Province province, City city, County county) {
                        if (county == null) {
                            ToastUtil.showToast(getBaseContext(),province.getAreaName() + city.getAreaName());
                        } else {
                            addprovince = province.getAreaName();
                            addcity = city.getAreaName();
                            addcountry = county.getAreaName();
                            tvArea.setText(addprovince+addcity+addcountry);

                        }
                    }
                });
                task.execute("北京", "北京", "东城区");

                break;
            case R.id.rl_default:

                if (mark){
                    iv.setImageResource(R.drawable.dot_check);
                    status = "2";
                    mark = false;
                }else {
                    iv.setImageResource(R.drawable.dot_uncheck);
                    status = "1";
                    mark = true;
                }

                break;
            case R.id.bt_save:

                if (TextUtils.isEmpty(tvName.getText().toString())){
                    ToastUtil.showToast(getBaseContext(),"请输入您的姓名");
                    return;
                }else if (TextUtils.isEmpty(tvPhone.getText().toString())){
                    ToastUtil.showToast(getBaseContext(),"请输入您的手机号");
                    return;
                }else if (!isMobileNO(tvPhone.getText().toString().trim())) {
                    ToastUtil.showToast(getBaseContext(),"手机格式不对");
                    return;
                }else if (TextUtils.isEmpty(tvAddress.getText().toString())){
                    ToastUtil.showToast(getBaseContext(),"请输入您的详细地址");
                    return;
                }else {

                    if (id != null && !id.equals("")){
                        PostAddressManage(id);
                    }else {
                        PostAddAddress();
                    }

                }

                break;
        }
    }

    private void PostAddressManage(String id){
        String linkman = tvName.getText().toString();
        String detail = tvAddress.getText().toString();
        String telnumber = tvPhone.getText().toString();

        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.ADRESSEDIT, RequestMethod.POST);
        if (request != null){
            request.add("dosubmit",1);
            request.add("uid",userid);
            request.add("id",id);
            request.add("province",addprovince);
            request.add("city",addcity);
            request.add("country",addcountry);
            request.add("detail",detail);
            request.add("linkman",linkman);
            request.add("telnumber",telnumber);
            request.add("status",status);


            // 添加到请求队列
            request(0, request, addressshanobjectListener, true, true);
        }
    }

    private HttpListener<JSONObject> addressshanobjectListener = new HttpListener<JSONObject>() {

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            getUserMessage();

        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {

            ToastUtil.showToast(getBaseContext(),"访问网络失败，请检查您的网络！");

        }
    };

    private void PostAddAddress(){

        String linkman = tvName.getText().toString();
        String detail = tvAddress.getText().toString();
        String telnumber = tvPhone.getText().toString();

        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.ADDADRESS, RequestMethod.POST);
        if (request != null){
            request.add("dosubmit",1);
            request.add("uid",userid);
            request.add("province",addprovince);
            request.add("city",addcity);
            request.add("country",addcountry);
            request.add("detail",detail);
            request.add("linkman",linkman);
            request.add("telnumber",telnumber);
            request.add("status",status);
            Log.e(TAG, "PostAddAddress: "+status );

            // 添加到请求队列
            request(0, request, addressobjectListener, true, true);
        }

    }

    private HttpListener<JSONObject> addressobjectListener = new HttpListener<JSONObject>() {

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            getUserMessage();

        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {

            ToastUtil.showToast(getBaseContext(),"访问网络失败，请检查您的网络！");

        }
    };

    //获取用户信息
    private void getUserMessage(){
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.USERMESSAGE);
        request(0, request, userMessageListener, true, true);
    }
    private HttpListener<JSONObject> userMessageListener = new HttpListener<JSONObject>() {

        private UserMessage.DataBean data;
        private String avatar;
        private String uid;
        private String nickname;
        private String userName;
        private String status;

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onSucceed(int what, Response<JSONObject> response) {

            try {

                JSONObject js = response.get();
                Log.e(TAG, "usermessage: "+js );
                status = String.valueOf(js.getInt("status"));
                UserMessage userMessage = JsonUtil.parseJsonToBean(js.toString(), UserMessage.class);

                if (status.equals("1")){

                    if (userMessage != null){
                        data = userMessage.getData();
                        avatar = data.getAvatar();
                        userName = data.getUserName();
                        nickname = data.getNickname();
                        uid = data.getUid();

                        SharedPreferences.Editor edit = FSApplication.instance.sp.edit();
                        edit.putString("status", status);
                        edit.putString("userid", uid);
                        edit.putString("avatar", avatar);
                        edit.putString("nickname", nickname);
                        edit.putString("userName", userName);
                        edit.commit();

                        //保存请求到的数据
                        SharePreferencesUtils.putBean(getBaseContext(), "userMessage",
                                userMessage);

                        finish();
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {
            ToastUtil.showToast(getBaseContext(),"请求网络失败，请稍后重试");
        }
    };

    /*
     * 手机格式 String的值只读序列
	 */
    public boolean isMobileNO(String mobiles) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

}
