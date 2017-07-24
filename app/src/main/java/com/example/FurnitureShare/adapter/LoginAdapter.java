package com.example.FurnitureShare.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.Time;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.FurnitureShare.R;
import com.example.FurnitureShare.app.AllUrl;
import com.example.FurnitureShare.app.FSApplication;
import com.example.FurnitureShare.base.BaseActivity;
import com.example.FurnitureShare.entry.FindPassword;
import com.example.FurnitureShare.entry.Login;
import com.example.FurnitureShare.entry.Register;
import com.example.FurnitureShare.entry.SendCode;
import com.example.FurnitureShare.nohttp.CallServer;
import com.example.FurnitureShare.nohttp.HttpListener;
import com.example.FurnitureShare.utils.JsonUtil;
import com.example.FurnitureShare.utils.ToastUtil;
import com.example.FurnitureShare.view.Clearedittext;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;


import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.logging.Handler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mac on 15-11-26.
 */
public class LoginAdapter extends PagerAdapter {

    private static final String TAG = "LoginAdapter";
    private LayoutInflater mInflater;
    private Context context;
    private ViewPager viewPager;
    private ImageView ivLogo;
    public Handler parentHandler;

    LayoutInflater inflater;
    private boolean isHidden = true;

    private Clearedittext login_tv_phone;
    private EditText login_tv_mima;
    private Button bt_login;
    private ImageView bt_hiddenmima;
    private ImageView bt_hiddenmima1;
//    private ProgressBar login_pr;

    private Clearedittext register_tv_phone;
    private EditText register_tv_code;
    private Button register_bt_code;
    private EditText register_tv_mima;
    private ImageView register_bt_hiddenmima;
    private ImageView register_bt_hiddenmima1;
    private Button bt_register;
//    private ProgressBar register_pr;
    TimeCount tc;
    TimeCount1 tc1;




    private Clearedittext find_tv_phone;
    private EditText find_tv_code;
    private Button find_bt_code;
    private EditText find_tv_mima;
    private ImageView find_bt_hiddenmima;
    private ImageView find_bt_hiddenmima1;
    private Button bt_find;
//    private ProgressBar find_pr;

    private KProgressHUD hud;


    public LoginAdapter(Context context, ViewPager viewPager, ImageView ivLogo){
        mInflater = LayoutInflater.from(context);
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.viewPager = viewPager;
        this.ivLogo = ivLogo;
    }
    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {


        final ViewHolder holder;
        holder = new ViewHolder();
        View imageLayout = mInflater.inflate(R.layout.login_item, null);

        holder.image = (LinearLayout) imageLayout.findViewById(R.id.gallery_image_item_view);

        if(position == 4) {
            holder.image.setEnabled(true);
        } else {
            holder.image.setEnabled(false);
        }
        if(position == 0) {
            holder.image.removeAllViews();
            View view0 = inflater.inflate(R.layout.activity_find_password, null);
            holder.image.addView(view0);

            //退出
            Button find_bt = (Button)view0.findViewById(R.id.find_bt);
            find_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(1);
                }
            });
            //找回密码-账号
            find_tv_phone = (Clearedittext)view0.findViewById(R.id.find_tv_phone);
            //找回密码-验证码
            find_tv_code = (EditText)view0.findViewById(R.id.find_tv_code);
            //找回密码-获取验证码
            find_bt_code = (Button) view0.findViewById(R.id.find_bt_code);
            //找回密码-密码
            find_tv_mima = (EditText)view0.findViewById(R.id.find_tv_mima);
            //找回密码-隐藏密码
            find_bt_hiddenmima = (ImageView) view0.findViewById(R.id.find_bt_hiddenmima);
            find_bt_hiddenmima1 = (ImageView) view0.findViewById(R.id.find_bt_hiddenmima1);
            //找回密码-确定找回
            bt_find = (Button)view0.findViewById(R.id.bt_find);


            find_bt_code.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(find_tv_phone.getText().toString())) {
                        ToastUtil.showToast(context,"请输入手机号");
                        return;
                    } else if (!isMobileNO(find_tv_phone.getText().toString().trim())) {
                        ToastUtil.showToast(context,"手机格式不对");
                        return;
                    }

                    GetSendPhone2();

                    tc1 = new TimeCount1(30000, 1000);
                    tc1.start();
                }
            });

            find_bt_hiddenmima.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isHidden) {
                        //设置EditText文本为可见的
                        find_tv_mima.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                        find_bt_hiddenmima1.setVisibility(View.GONE);
                    } else {
                        //设置EditText文本为隐藏的
                        find_tv_mima.setTransformationMethod(PasswordTransformationMethod.getInstance());

                        find_bt_hiddenmima1.setVisibility(View.VISIBLE);
                    }
                    isHidden = !isHidden;
                    find_tv_mima.postInvalidate();
                    //切换后将EditText光标置于末尾
                    CharSequence charSequence = find_tv_mima.getText();
                    if (charSequence instanceof Spannable) {
                        Spannable spanText = (Spannable) charSequence;
                        Selection.setSelection(spanText, charSequence.length());
                    }
                }
            });

            bt_find.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(find_tv_phone.getText().toString())) {
                        ToastUtil.showToast(context,"请输入手机号");
                        return;
                    } else if (!isMobileNO(find_tv_phone.getText().toString().trim())) {
                        ToastUtil.showToast(context,"手机格式不对");
                        return;
                    } else if (TextUtils.isEmpty(find_tv_code.getText().toString())) {
                        ToastUtil.showToast(context,"请输入验证码");
                        return;
                    } else if (TextUtils.isEmpty(find_tv_mima.getText().toString())) {
                        ToastUtil.showToast(context,"请输入密码");
                        return;
                    } else if (!PassWord(find_tv_mima.getText().toString())) {
                        ToastUtil.showToast(context,"密码只能是数字、英文或者常用特殊字符");
                    } else {

                        //loading
                        hud = KProgressHUD.create(context)
                                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
                        hud.show();
                        //找回密码接口
                        getFindPassword();
                    }
                }
            });

        } else if (position == 1) {
            holder.image.removeAllViews();
            final View view1 = inflater.inflate(R.layout.activity_login2, null);
            holder.image.addView(view1);
            //退出
            Button login_bt = (Button)view1.findViewById(R.id.login_bt);
            login_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //在adapter中关闭所在的activity
                    if (context instanceof Activity){
                        Activity activity = (Activity)context;
                        activity.finish();
                    }
                }
            });
            //登录-账号
            login_tv_phone = (Clearedittext)view1.findViewById(R.id.login_tv_phone);
            //登录-密码
            login_tv_mima = (EditText)view1.findViewById(R.id.login_tv_mima);

            //登录-登录按钮
            bt_login = (Button)view1.findViewById(R.id.bt_login);
            //登录-显示密码
            bt_hiddenmima = (ImageView) view1.findViewById(R.id.bt_hiddenmima);
            bt_hiddenmima1 = (ImageView) view1.findViewById(R.id.bt_hiddenmima1);


            bt_hiddenmima.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isHidden) {
                    //设置EditText文本为可见的
                        login_tv_mima.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                        bt_hiddenmima1.setVisibility(View.GONE);
                } else {
                    //设置EditText文本为隐藏的
                        login_tv_mima.setTransformationMethod(PasswordTransformationMethod.getInstance());

                        bt_hiddenmima1.setVisibility(View.VISIBLE);
                }
                isHidden = !isHidden;
                    login_tv_mima.postInvalidate();
                //切换后将EditText光标置于末尾
                CharSequence charSequence = login_tv_mima.getText();
                if (charSequence instanceof Spannable) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText, charSequence.length());
                }
                }
            });

            bt_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    bt_login.setEnabled(false);

                    Login();
                }
            });
            //注册
            TextView tv_register = (TextView)view1.findViewById(R.id.tv_register);
            tv_register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(2);
                }
            });
            //忘记密码
            TextView tv_forgotmima = (TextView)view1.findViewById(R.id.tv_forgotmima);
            tv_forgotmima.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(0);
                }
            });

        } else if (position == 2) {
            holder.image.removeAllViews();
            View view2 = inflater.inflate(R.layout.activity_register, null);
            holder.image.addView(view2);
            //返回登录
            Button register_bt = (Button)view2.findViewById(R.id.register_bt);
            register_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(1);
                }
            });

            //注册-账号
            register_tv_phone = (Clearedittext)view2.findViewById(R.id.register_tv_phone);
            //注册-验证码
            register_tv_code = (EditText)view2.findViewById(R.id.register_tv_code);
            //注册-获取验证码
            register_bt_code = (Button) view2.findViewById(R.id.register_bt_code);
            //注册-密码
            register_tv_mima = (EditText)view2.findViewById(R.id.register_tv_mima);
            //注册-隐藏密码
            register_bt_hiddenmima = (ImageView) view2.findViewById(R.id.register_bt_hiddenmima);
            register_bt_hiddenmima1 = (ImageView) view2.findViewById(R.id.register_bt_hiddenmima1);
            //注册-注册按钮
            bt_register = (Button)view2.findViewById(R.id.bt_register);

            register_bt_code.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(register_tv_phone.getText().toString())) {
                        ToastUtil.showToast(context,"请输入手机号");
                        return;
                    } else if (!isMobileNO(register_tv_phone.getText().toString().trim())) {
                        ToastUtil.showToast(context,"手机格式不对");
                        return;
                    }
                    //判断手机号是否注册
//                    getJudge();
                    GetSendPhone1();
                    tc = new TimeCount(30000, 1000);
                    tc.start();
                }
            });

            register_bt_hiddenmima.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isHidden) {
                        //设置EditText文本为可见的
                        register_tv_mima.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                        register_bt_hiddenmima1.setVisibility(View.GONE);
                    } else {
                        //设置EditText文本为隐藏的
                        register_tv_mima.setTransformationMethod(PasswordTransformationMethod.getInstance());

                        register_bt_hiddenmima1.setVisibility(View.VISIBLE);
                    }
                    isHidden = !isHidden;
                    register_tv_mima.postInvalidate();
                    //切换后将EditText光标置于末尾
                    CharSequence charSequence = register_tv_mima.getText();
                    if (charSequence instanceof Spannable) {
                        Spannable spanText = (Spannable) charSequence;
                        Selection.setSelection(spanText, charSequence.length());
                    }
                }
            });

            bt_register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(register_tv_phone.getText().toString())) {
                        ToastUtil.showToast(context,"请输入手机号");
                        return;
                    } else if (!isMobileNO(register_tv_phone.getText().toString().trim())) {
                        ToastUtil.showToast(context,"手机格式不对");
                        return;
                    } else if (TextUtils.isEmpty(register_bt_code.getText().toString())) {
                        ToastUtil.showToast(context,"请输入验证码");
                        return;
                    } else if (TextUtils.isEmpty(register_tv_mima.getText().toString())) {
                        ToastUtil.showToast(context,"请输入密码");
                        return;
                    } else if (!PassWord(register_tv_mima.getText().toString())) {
                        ToastUtil.showToast(context,"密码只能是数字、英文或者常用特殊字符");
                    } else {
                        //loading
                        hud = KProgressHUD.create(context)
                                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
                        hud.show();
                        //注册接口
                        getRegister();
                    }
                }
            });

        }

        ((ViewPager) container).addView(imageLayout, 0);

        return imageLayout;

    }

    class ViewHolder{
        private LinearLayout image;
    }


    //找回密码
    private void getFindPassword(){

        String phone = find_tv_phone.getText().toString();
        String code = find_tv_code.getText().toString();
        String password = find_tv_mima.getText().toString();

        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.FIND_PASSWORD, RequestMethod.POST);
        if (request != null){
            request.add("mobile", phone);
            request.add("newpassword",password);
            request.add("dosubmit",1);
            request.add("mobile_verity",code);

            // 添加到请求队列
            request(0, request, findpasswordobjectListener, true, true);
        }

    }

    public <T> void request(int what, Request<T> request, HttpListener<T> callback, boolean canCancel, boolean isLoading) {
        request.setCancelSign(this);
        CallServer.getInstance().requests((BaseActivity) context, what, request, callback, canCancel, isLoading);
    }

    private HttpListener<JSONObject> findpasswordobjectListener = new HttpListener<JSONObject>() {

        private int status;
        private int sex;
        private String birthday;
        private String avatar;
        private String nickname;
        private String userid;
        private String username;
        private String msg;
        private FindPassword.DataBean data;
        private FindPassword findPassword;

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {

            try {
                JSONObject js = response.get();
                Log.e(TAG, "onSucceed123: "+js );
                int code = js.getInt("code");
                findPassword = JsonUtil.parseJsonToBean(js.toString(), FindPassword.class);

                data = findPassword.getData();
                msg = findPassword.getMsg();
                status = findPassword.getStatus();

                // 判断
                    if (code == 200) {
                        userid = data.getUserid();
                        username = data.getUsername();
                        nickname = data.getNickname();
                        avatar = data.getAvatar();
                        birthday = data.getBirthday();
                        sex = data.getSex();

                        SharedPreferences.Editor edit = FSApplication.instance.sp.edit();
                        edit.putString("userid",userid);
                        edit.putString("username",username);
                        edit.putString("nickname",nickname);
                        edit.putString("avatar",avatar);
                        edit.putString("birthday",birthday);
                        edit.putString("sex",String.valueOf(sex));
                        edit.putString("status", String.valueOf(status));
                        edit.commit();

                        hud.dismiss();

                        ToastUtil.showToast(context,msg);
                        //在adapter中关闭所在的activity
                        if (context instanceof Activity){
                            Activity activity = (Activity)context;
                            activity.finish();
                        }
                    }else if (code == 0){
                        ToastUtil.showToast(context,msg);
                        scheduleDismiss();
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onFailed(int what, Response<JSONObject> response) {
            ToastUtil.showToast(context,"访问网络失败，请检查您的网络！");
            scheduleDismiss();
        }
    };

    /**
     * 注册接口
     */
    private void getRegister() {


        String phone = register_tv_phone.getText().toString();
        String code = register_tv_code.getText().toString();
        final String password = register_tv_mima.getText().toString();

        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.REGISTER, RequestMethod.POST);
        if (request != null){
            request.add("mobile", phone);
            request.add("password",password);
            request.add("dosubmit",1);
            request.add("mobile_verity",code);

            // 添加到请求队列
            request(0, request, registerobjectListener, true, true);
        }

    }

    private HttpListener<JSONObject> registerobjectListener = new HttpListener<JSONObject>() {

        private int status;
        private int sex;
        private String birthday;
        private String avatar;
        private String username;
        private String userid;
        private String nickname;
        private String msg;
        private Register.DataBean data;
        private Register register;

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            try {
                JSONObject js = response.get();
                Log.e(TAG, "onSucceed123: "+js );
                int code = js.getInt("code");
                register = JsonUtil.parseJsonToBean(js.toString(), Register.class);
                data = register.getData();
                msg = register.getMsg();
                status = register.getStatus();
                // 判断
                    if (code == 200) {

                        nickname = data.getNickname();
                        userid = data.getUserid();
                        username = data.getUsername();
                        avatar = data.getAvatar();
                        birthday = data.getBirthday();
                        sex = data.getSex();

                        SharedPreferences.Editor edit = FSApplication.instance.sp.edit();
                        edit.putString("nickname",nickname);
                        edit.putString("userid",userid);
                        edit.putString("username",username);
                        edit.putString("avatar",avatar);
                        edit.putString("birthday",birthday);
                        edit.putString("sex",String.valueOf(sex));
                        edit.putString("status",String.valueOf(status));
                        edit.commit();

                        hud.dismiss();

                        ToastUtil.showToast(context,msg);
                        //在adapter中关闭所在的activity
                        if (context instanceof Activity){
                            Activity activity = (Activity)context;
                            activity.finish();
                        }
                    }else if (code == 0){
                        ToastUtil.showToast(context,msg);
//                        register_pr.setVisibility(View.GONE);
                        scheduleDismiss();
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        @Override
        public void onFailed(int what, Response<JSONObject> response) {

            ToastUtil.showToast(context,"访问网络失败，请检查您的网络！");
            scheduleDismiss();
        }
    };


    /**
     * 密码格式
     *
     * @param password
     * @return
     */
    public boolean PassWord(String password) {
        Pattern p = Pattern.compile("[0-9a-zA-Z.]{6,20}");
        Matcher m = p.matcher(password);
        return m.matches();
    }



    //发送手机号(找回密码)
    private void GetSendPhone2(){

        String phone = find_tv_phone.getText().toString();
        Log.e(TAG, "GetSendPhone: "+phone );


        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.SENDCODE+ phone);
        request(0, request, sendphoneobjectListener, true, true);

    }

    private HttpListener<JSONObject> sendphoneobjectListener = new HttpListener<JSONObject>() {


        private int return_id;
        private SendCode sendCode;

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {

            try {
                JSONObject js = response.get();
                Log.e(TAG, "发送手机号(找回密码): "+js );
                int code = js.getInt("code");
                    String msg = js.getString("msg");
                    if (code == 200 ){
                        sendCode = JsonUtil.parseJsonToBean(js.toString(), SendCode.class);
                        return_id = sendCode.getReturn_id();
                        find_tv_code.setText(return_id+"");
                        ToastUtil.showToast(context,return_id+"");

                    }else {
                        ToastUtil.showToast(context,msg);
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {

            ToastUtil.showToast(context,"访问网络失败，请检查您的网络！");
        }
    };

    //发送手机号(注册)
    private void GetSendPhone1(){

        String phone = register_tv_phone.getText().toString();
        Log.e(TAG, "GetSendPhone: "+phone );

        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.SENDCODE+ phone);
        request(0, request, sengphonobjectListener, true, true);

    }

    private HttpListener<JSONObject> sengphonobjectListener = new HttpListener<JSONObject>() {


        private int return_id;
        private SendCode sendCode;

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {

            try {
                JSONObject js = response.get();
                Log.e(TAG, "发送手机号: "+js );
                int code = js.getInt("code");
                String msg = js.getString("msg");
                if (code == 200 ){
                    sendCode = JsonUtil.parseJsonToBean(js.toString(), SendCode.class);
                    return_id = sendCode.getReturn_id();
                    register_tv_code.setText(return_id+"");
                    ToastUtil.showToast(context,return_id+"");

                }else {
                    ToastUtil.showToast(context,msg);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {
            ToastUtil.showToast(context,"访问网络失败，请检查您的网络！");
        }
    };

    /* 定义一个倒计时的内部类 */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {// 计时完毕时触发

            register_bt_code.setText("重新验证");
            register_bt_code.setClickable(true);

        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            register_bt_code.setClickable(false);
            register_bt_code.setText("重发(" + millisUntilFinished / 1000 + ")");

        }
    }

    /* 定义一个倒计时的内部类 */
    class TimeCount1 extends CountDownTimer {
        public TimeCount1(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {// 计时完毕时触发

//            register_bt_code.setText("重新验证");
//            register_bt_code.setClickable(true);
            find_bt_code.setText("重新验证");
            find_bt_code.setClickable(true);

        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            find_bt_code.setClickable(false);
            find_bt_code.setText("重发(" + millisUntilFinished / 1000 + ")");
        }
    }

    private void scheduleDismiss() {
        android.os.Handler handler = new android.os.Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hud.dismiss();
            }
        }, 2000);
    }

    /*
     * 手机格式 String的值只读序列
	 */
    public boolean isMobileNO(String mobiles) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    //登录判断账号密码
    private void Login(){

        if (TextUtils.isEmpty(login_tv_phone.getText().toString()) || TextUtils.isEmpty(login_tv_mima.getText().toString())) {
            ToastUtil.showToast(context,"请输入帐号密码");
//            bt_login.setEnabled(true);
            return;
        } else {

            String name = login_tv_phone.getText().toString().trim();
            String password = login_tv_mima.getText().toString().trim();

            if (password.length()<6){
                ToastUtil.showToast(context,"密码必须超过6个字符");
//                bt_login.setEnabled(true);
                return;
            }
            hud = KProgressHUD.create(context)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
            hud.show();
            loging(name, password);
        }
    }
    //账号密码登录
    private void loging(final String name, final String password){

        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.LOGIN, RequestMethod.POST);
        if (request != null){
            request.add("mobile", name);
            request.add("password",password);
            request.add("dosubmit",1);

            // 添加到请求队列
            request(0, request, logingobjectListener, true, true);
        }

    }

    private HttpListener<JSONObject> logingobjectListener = new HttpListener<JSONObject>() {

        private int status;
        private int sex;
        private String birthday;
        private String avatar;
        private String nickname;
        private String username;
        private String userid;
        private String msg;
        private Login.DataBean data;
        private Login login;

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            try {
                JSONObject js = response.get();
                Log.e(TAG, "onSucceed123: "+js );
                int code = js.getInt("code");
                login = JsonUtil.parseJsonToBean(js.toString(), Login.class);

                data = login.getData();
                msg = login.getMsg();
                status = login.getStatus();
                if (code == 200){

//                    bt_login.setEnabled(true);
                    userid = data.getUserid();
                    username = data.getUsername();
                    nickname = data.getNickname();
                    avatar = data.getAvatar();
                    birthday = data.getBirthday();
                    sex = data.getSex();

                    SharedPreferences.Editor edit = FSApplication.instance.sp.edit();
                    edit.putString("userid",userid);
                    edit.putString("username",username);
                    edit.putString("nickname",nickname);
                    edit.putString("avatar",avatar);
                    edit.putString("birthday",birthday);
                    edit.putString("sex",String.valueOf(sex));
                    edit.putString("status",String.valueOf(status));
                    edit.commit();

                    hud.dismiss();
                        //在adapter中关闭所在的activity
                        if (context instanceof Activity){
                            Activity activity = (Activity)context;
                            activity.finish();
                        }
                    }else if (code == 0){
//                        bt_login.setEnabled(true);
                        ToastUtil.showToast(context,"请输入正确的手机号和密码！");
                        scheduleDismiss();
                    }else {
                        ToastUtil.showToast(context,"登录失败，请重新登录！");
//                        bt_login.setEnabled(true);
                        scheduleDismiss();
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {
            ToastUtil.showToast(context,"访问网络失败，请检查您的网络！");
//            bt_login.setEnabled(true);
            scheduleDismiss();
        }
    };
}
