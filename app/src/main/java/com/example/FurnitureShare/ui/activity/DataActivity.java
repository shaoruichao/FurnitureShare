package com.example.FurnitureShare.ui.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.FurnitureShare.R;
import com.example.FurnitureShare.app.AllUrl;
import com.example.FurnitureShare.app.FSApplication;
import com.example.FurnitureShare.base.BaseActivity;
import com.example.FurnitureShare.entry.PushAvatar;
import com.example.FurnitureShare.nohttp.HttpListener;
import com.example.FurnitureShare.ui.HomeActivity;
import com.example.FurnitureShare.utils.ImageUtil;
import com.example.FurnitureShare.utils.JsonUtil;
import com.example.FurnitureShare.utils.StaturBar;
import com.example.FurnitureShare.utils.ToastUtil;
import com.example.FurnitureShare.view.ChangeBirthdayPopwindow;
import com.example.FurnitureShare.view.CircleImageView;
import com.squareup.picasso.Picasso;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 个人资料
 */
public class DataActivity extends BaseActivity {

    private static final String TAG = "DataActivity";

    @BindView(R.id.bt_back)
    Button btBack;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_head_title)
    TextView tvHeadTitle;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.tv_nickname_ok)
    Button tvNicknameOk;
    @BindView(R.id.rl_nickname)
    RelativeLayout rlNickname;
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.iv_nan)
    ImageView ivNan;
    @BindView(R.id.tv_nan)
    TextView tvNan;
    @BindView(R.id.rl_nan)
    RelativeLayout rlNan;
    @BindView(R.id.iv_nv)
    ImageView ivNv;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.rl_nv)
    RelativeLayout rlNv;
    @BindView(R.id.rl_sex)
    RelativeLayout rlSex;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.rl_birthday)
    RelativeLayout rlBirthday;
    @BindView(R.id.tv_showphone)
    TextView tvShowphone;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.iv_phone)
    ImageView ivPhone;
    @BindView(R.id.rl_phone)
    RelativeLayout rlPhone;
    @BindView(R.id.tv_password)
    TextView tvPassword;
    @BindView(R.id.iv_password)
    ImageView ivPassword;
    @BindView(R.id.rl_password)
    RelativeLayout rlPassword;
    @BindView(R.id.tv_wx)
    TextView tvWx;
    @BindView(R.id.iv_wx)
    ImageView ivWx;
    @BindView(R.id.rl_wx)
    RelativeLayout rlWx;
    @BindView(R.id.tv_wb)
    TextView tvWb;
    @BindView(R.id.iv_wb)
    ImageView ivWb;
    @BindView(R.id.rl_wb)
    RelativeLayout rlWb;
    @BindView(R.id.tv_qq)
    TextView tvQq;
    @BindView(R.id.iv_qq)
    ImageView ivQq;
    @BindView(R.id.rl_qq)
    RelativeLayout rlQq;
    @BindView(R.id.rl_exit)
    RelativeLayout rlExit;
    private String avatar;
    private String nickname;
    private String userid;
    private String sp_sex;
    private String birthday;
    private String sex;
    private String birth;

    private Bitmap head;// 头像Bitmap
    // 截图返回的uri
    private Uri outPutUri;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        ButterKnife.bind(this);
        // 添加到Activity集合
        FSApplication.instance.addActivity(this);

        // 系统 6.0 以上 状态栏白底黑字的实现方法
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        StaturBar.MIUISetStatusBarLightMode(getWindow(), true);
        StaturBar.FlymeSetStatusBarLightMode(getWindow(), true);

        avatar = FSApplication.instance.sp.getString("avatar", "");
        if (avatar != null && !avatar.equals("")) {
            Picasso.with(this).load(avatar).into(ivHead);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        nickname = FSApplication.instance.sp.getString("nickname", "");
        userid = FSApplication.instance.sp.getString("userid", "");
        sp_sex = FSApplication.instance.sp.getString("sex", "");
        birthday = FSApplication.instance.sp.getString("birthday", "");

        if (nickname != null && !nickname.equals("")) {
            tvName.setText(nickname);
        }

        if (sp_sex != null && !sp_sex.equals("")) {
            if (sp_sex.equals("1")) {
                ivNan.setImageResource(R.drawable.dot_check);
                ivNv.setImageResource(R.drawable.dot_uncheck);
            } else if (sp_sex.equals("0")) {
                ivNv.setImageResource(R.drawable.dot_check);
                ivNan.setImageResource(R.drawable.dot_uncheck);
            }
        }
        if (birthday != null && !birthday.equals("")) {
            tvBirthday.setText(birthday);
        }
    }

    @OnClick({R.id.bt_back, R.id.iv_head, R.id.rl_name, R.id.rl_nan, R.id.rl_nv, R.id.rl_birthday, R.id.rl_phone, R.id.rl_password, R.id.rl_wx, R.id.rl_wb, R.id.rl_qq, R.id.rl_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_back:
                finish();
                break;
            case R.id.iv_head:
                showPhotoDialog();
                break;
            case R.id.rl_name:
                startActivity(new Intent(this, AlterNameActivity.class));
                break;
            case R.id.rl_nan:
                sex = "1";
                ivNan.setImageResource(R.drawable.dot_check);
                ivNv.setImageResource(R.drawable.dot_uncheck);
                getUpdate();
                break;
            case R.id.rl_nv:
                sex = "0";
                ivNv.setImageResource(R.drawable.dot_check);
                ivNan.setImageResource(R.drawable.dot_uncheck);
                getUpdate();
                break;
            case R.id.rl_birthday:
                alterBirthday();
                break;
            case R.id.rl_phone:
                break;
            case R.id.rl_password:
                startActivity(new Intent(getBaseContext(),AlterPasswordActivity.class));
                break;
            case R.id.rl_wx:
                break;
            case R.id.rl_wb:
                break;
            case R.id.rl_qq:
                break;
            case R.id.rl_exit:
                exit();
                break;
        }
    }

    private void exit() {
        final AlertDialog dlg =
                new AlertDialog.Builder(this, R.style.MyDialogStyle).create();
        dlg.setCanceledOnTouchOutside(true);
        dlg.show();
        Window window = dlg.getWindow();
        //设置窗口的内容页面
        window.setContentView(R.layout.exit_dialog);
        //取消
        Button bt_cancel = (Button) window.findViewById(R.id.bt_cancel);
        bt_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dlg.cancel();
            }
        });
        //确定
        Button bt_ok = (Button) window.findViewById(R.id.bt_ok);
        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logout();

                dlg.cancel();
            }
        });
    }

    private void logout() {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.LOGOUT);
        request(0, request, objectListener, true, true);
    }

    private HttpListener<JSONObject> objectListener = new HttpListener<JSONObject>() {

        private int code;
        private String msg;

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {


            JSONObject js = response.get();
            Log.e(TAG, "on3: " + js);
            try {

                code = js.getInt("code");
                msg = js.getString("msg");
                if (code == 200) {
                    startActivity(new Intent(DataActivity.this, HomeActivity.class));
                    DataActivity.this.finish();
                    FSApplication.instance.exit();
                    /**sp清空数据（账号密码）*/
                    FSApplication.instance.sp.edit().clear().commit();

                } else {
                    ToastUtil.showToast(getApplicationContext(), msg);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {

            ToastUtil.showToast(getApplicationContext(), "访问网络失败，请检查您的网络！");
        }
    };

    //修改生日
    private void alterBirthday() {
        ChangeBirthdayPopwindow mChangeBirthdayPopwindow = new ChangeBirthdayPopwindow(DataActivity.this);
        mChangeBirthdayPopwindow.showAtLocation(rlBirthday, Gravity.BOTTOM, 0, 0);
        setBackgroundAlpha(0.5f);//设置屏幕透明度
        mChangeBirthdayPopwindow.setBackgroundDrawable(new BitmapDrawable());
        mChangeBirthdayPopwindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        mChangeBirthdayPopwindow
                .setBirthdayListener(new ChangeBirthdayPopwindow.OnBirthListener() {

                    @Override
                    public void onClick(String year, String month, String day) {

                        if (Integer.parseInt(month) < 10) {
                            month = "0" + month;
                        }
                        if (Integer.parseInt(day) < 10) {
                            day = "0" + day;
                        }
                        birth = year + "-" + month + "-" + day;
                        if (!birth.equals("")) {
                            tvBirthday.setText(birth);
                            getUpdate();
                        }

                    }

                });

        mChangeBirthdayPopwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // popupWindow隐藏时恢复屏幕正常透明度
                setBackgroundAlpha(1.0f);
            }
        });
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha 屏幕透明度0.0-1.0 1表示完全不透明
     */
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }

    //修改个人资料
    private void getUpdate() {

        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.UPDATEPERSONAL, RequestMethod.POST);
        if (request != null) {
            request.add("uid", userid);
            request.add("nickname", name);
            request.add("sex", sex);
            request.add("birthday", birth);
            // 添加到请求队列
            request(0, request, nameobjectListener, true, true);
        }

    }

    private HttpListener<JSONObject> nameobjectListener = new HttpListener<JSONObject>() {


        @Override
        public void onSucceed(int what, Response<JSONObject> response) {

            try {
                JSONObject js = response.get();
                Log.e(TAG, "onSucceed123: " + js);

                int code = js.getInt("code");
                String message = js.getString("message");
                if (code == 200) {
                    SharedPreferences.Editor edit = FSApplication.instance.sp.edit();
                    edit.putString("nickname", name);
                    edit.commit();

                    Toast toast = Toast.makeText(getApplicationContext(),
                            "修改成功", Toast.LENGTH_SHORT);
                    //放在左上角。如果你想往右边移动，将第二个参数设为>0；往下移动，增大第三个参数；后两个参数都只得像素
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                }
                if (code == 0) {
                    ToastUtil.showToast(getBaseContext(), message);
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


    /**
     * 头像
     */
    private void showPhotoDialog() {

        final AlertDialog dlg = new AlertDialog.Builder(this, R.style.MyDialogStyle).create();
        //点击空白区域消失
        dlg.setCanceledOnTouchOutside(true);
        dlg.show();
        Window window = dlg.getWindow();
        // 可以在此设置显示动画
        window.setWindowAnimations(R.style.mystyle);
        window.setGravity(Gravity.BOTTOM);
        //内容区域外围的灰色去掉了
//        window.setDimAmount(0);

        WindowManager.LayoutParams wl = window.getAttributes();

        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        dlg.onWindowAttributesChanged(wl);

        // 设置窗口的内容页面,shrew_exit_dialog.xml文件中定义view内容
        window.setContentView(R.layout.alertdialog);
        TextView tv_paizhao = (TextView) window.findViewById(R.id.tv_content1);
        tv_paizhao.setText("拍照");
        tv_paizhao.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
                        Environment.getExternalStorageDirectory(), "head.jpg")));
                startActivityForResult(intent2, 2);// 采用ForResult打开

                dlg.cancel();
            }
        });
        TextView tv_xiangce = (TextView) window.findViewById(R.id.tv_content2);
        tv_xiangce.setText("相册");
        tv_xiangce.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                intent1.setDataAndType(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent1, 1);
                dlg.cancel();
            }
        });
        //取消
        Button bt_cancel = (Button) window.findViewById(R.id.bt_cancel);
        bt_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dlg.cancel();
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());// 裁剪图片
                }

                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory()
                            + "/head.jpg");
                    cropPhoto(Uri.fromFile(temp));// 裁剪图片
                }

                break;
            case 3:
                if (data != null) {
//                    Bundle extras = data.getExtras();
//                    head = extras.getParcelable("data");
//                    head = getBitmapFromBigImagByUri(outPutUri);//这个方法也是可行，应该是只是尺寸压缩，没有压缩质量，故调用了之前写的ImageUtil（不这么写的话，在裁剪那里的时候返回就甭了）
                    try {

                        head = ImageUtil.getBitmapFormUri(DataActivity.this, outPutUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (head != null) {
                        Log.e(TAG, "onActivityResult11111: " + head);
                        /*** 上传服务器代码*/
                        //Base64的编码
                        String imgCode = ImageUtil.getBitmapStrBase64(head);
                        Log.e(TAG, "onActivityResult: " + imgCode);
                        //上传头像
                        upload(imgCode);

                        //setPicToView(head);// 保存在SD卡中
                        ivHead.setImageBitmap(head);// 用ImageView显示出来
                    }
                }
                break;
            default:
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    /*
     * 上传图片
     * 上传图片
     */
    public void upload(String imgCode) {

        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.PUSH_AVATAR, RequestMethod.POST);

        if (request != null) {
            request.add("avatardata", imgCode);

            // 添加到请求队列
            request(0, request, uploadobjectListener, true, true);
        }
    }

    private HttpListener<JSONObject> uploadobjectListener = new HttpListener<JSONObject>() {

        private PushAvatar pushAvatar;

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {

            try {
                JSONObject js = response.get();
                Log.e(TAG, "onSucceed123: " + js);

                int code = js.getInt("code");
                pushAvatar = JsonUtil.parseJsonToBean(js.toString(), PushAvatar.class);
                if (code == 200) {
                    avatar = pushAvatar.getAvatar();
                    SharedPreferences.Editor edit = FSApplication.instance.sp.edit();
                    edit.putString("avatar", avatar);

                    edit.commit();
                } else {
                    ToastUtil.showToast(getBaseContext(), "上传失败，请重新上传！");
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


    /**
     * 调用系统的裁剪
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", false);// true:不返回uri，false：返回uri
        //获取uri并压缩  不然在乐视手机上会bug（剪裁的时候返回崩溃）
        File tempFiles = getTempFile();
        outPutUri = Uri.fromFile(tempFiles);
        intent.putExtra("output", outPutUri);

        startActivityForResult(intent, 3);
    }

    private File getTempFile() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/fs_crop_image.jpg");
        return file;
    }


}
