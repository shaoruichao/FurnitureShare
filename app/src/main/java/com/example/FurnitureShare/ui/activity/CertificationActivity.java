package com.example.FurnitureShare.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.FurnitureShare.R;
import com.example.FurnitureShare.adapter.ImagePickerAdapter;
import com.example.FurnitureShare.app.AllUrl;
import com.example.FurnitureShare.app.FSApplication;
import com.example.FurnitureShare.base.BaseActivity;
import com.example.FurnitureShare.nohttp.HttpListener;
import com.example.FurnitureShare.utils.ImageUtil;
import com.example.FurnitureShare.utils.StaturBar;
import com.example.FurnitureShare.utils.ToastUtil;
import com.example.FurnitureShare.view.imageloader.GlideImageLoader;
import com.example.FurnitureShare.view.imageloader.SelectDialog;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 实名认证
 */
public class CertificationActivity extends BaseActivity implements ImagePickerAdapter.OnRecyclerViewItemClickListener{

    private static final String TAG = "CertificationActivity";

    @BindView(R.id.bt_back)
    Button btBack;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.text1)
    TextInputLayout text1;
    @BindView(R.id.tv_number)
    EditText tvNumber;
    @BindView(R.id.text2)
    TextInputLayout text2;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.bt_ok)
    Button btOk;


    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 3;               //允许选择图片最大数
    private String[] PicStr;                  //图片数组（base64）
    private ArrayList<String> list;
    private String userid;

    private KProgressHUD hud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification);
        ButterKnife.bind(this);

        // 添加到Activity集合
        FSApplication.instance.addActivity(this);

        // 系统 6.0 以上 状态栏白底黑字的实现方法
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        StaturBar.MIUISetStatusBarLightMode(getWindow(), true);
        StaturBar.FlymeSetStatusBarLightMode(getWindow(), true);

        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);


        initImagePicker();

        initWidget();

        tvName.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {

                if (tvName.length() != 0 && tvNumber.length()!= 0 && PicStr != null) {
                    btOk.setBackgroundResource(R.drawable.bt_altername1);
                } else {
                    btOk.setBackgroundResource(R.drawable.bt_altername);
                }

            }
        });

        tvNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (tvName.length() != 0 && tvNumber.length()!= 0 && PicStr != null) {
                    btOk.setBackgroundResource(R.drawable.bt_altername1);
                } else {
                    btOk.setBackgroundResource(R.drawable.bt_altername);
                }


            }
        });

    }

    private void initImagePicker() {
        //仿微信图片选择
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(false);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }

    private void initWidget() {
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(this, R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }


    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case IMAGE_ITEM_ADD:
                List<String> names = new ArrayList<>();
                names.add("拍照");
                names.add("相册");
                showDialog(new SelectDialog.SelectDialogListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0: // 直接调起相机
                                /**
                                 * 0.4.7 目前直接调起相机不支持裁剪，如果开启裁剪后不会返回图片，请注意，后续版本会解决
                                 *
                                 * 但是当前直接依赖的版本已经解决，考虑到版本改动很少，所以这次没有上传到远程仓库
                                 *
                                 * 如果实在有所需要，请直接下载源码引用。
                                 */
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent = new Intent(CertificationActivity.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent1 = new Intent(CertificationActivity.this, ImageGridActivity.class);
                                /* 如果需要进入选择的时候显示已经选中的图片，
                                 * 详情请查看ImagePickerActivity
                                 * */
//                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                                startActivityForResult(intent1, REQUEST_CODE_SELECT);
                                break;
                            default:
                                break;
                        }

                    }
                }, names);

                break;
            default:
                //打开预览
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }

    ArrayList<ImageItem> images = null;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        }

        list = new ArrayList<>();
        for (int i = 0; i < selImageList.size(); i++) {

            //取出图片路径
            String path = selImageList.get(i).path;
            //路径转成bitmap,这样会造成oom
//            Bitmap bitmap = BitmapFactory.decodeFile(path);
            Bitmap bitmap = BitmapFactory.decodeByteArray(ImageUtil.decodeBitmap(path), 0, ImageUtil.decodeBitmap(path).length);
//            imageCache.put(imagePath, new SoftReference<Bitmap>(bitmap));//缓存图片到软引用，方便内存回收
            //Base64的编码
            String imgCode = ImageUtil.getBitmapStrBase64(bitmap);
            Log.e(TAG, "onActivityResult: " + imgCode);

            list.add(imgCode);

        }

        //图片数组
        PicStr = (String[]) list.toArray(new String[list.size()]);
        Log.e(TAG, "onActivityResult12121212: "+PicStr.toString() );
        if (tvName.length() != 0 && tvNumber.length()!= 0 && PicStr != null) {
            btOk.setBackgroundResource(R.drawable.bt_altername1);
        } else {
            btOk.setBackgroundResource(R.drawable.bt_altername);
        }


    }


    @OnClick({R.id.bt_back, R.id.bt_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_back:
                finish();
                break;
            case R.id.bt_ok:
                btOk.setEnabled(false);

                hud.show();
                postPush();
                break;
        }
    }

    private void postPush(){
        String name = tvName.getText().toString();
        String tvnumber = tvNumber.getText().toString();
        userid = FSApplication.instance.sp.getString("userid", "");

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(tvnumber)){

            ToastUtil.showToast(this,"请输入完整信息1");
            btOk.setEnabled(true);
            scheduleDismiss();
            return;
        }

        if (PicStr == null || PicStr.equals("")){
            ToastUtil.showToast(this,"请输入完整信息2");
            btOk.setEnabled(true);
            scheduleDismiss();
            return;
        }
        if (PicStr.length < 3){
            ToastUtil.showToast(this,"请输入完整信息3");
            btOk.setEnabled(true);
            scheduleDismiss();
            return;
        }

        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.CERTIFICATION, RequestMethod.POST);
        if (request != null){
            request.add("uid",userid);
            request.add("name", name);
            request.add("id_number",tvnumber);


            for (int x=0 ; x< PicStr.length;x++){
                request.add("thumbdata"+"["+x+"]",PicStr[x]);
                Log.e(TAG, "onActivityResult2222222: "+PicStr[x] );
            }

            // 添加到请求队列
            request(0, request, pushobjectListener, true, true);
        }

    }

    private HttpListener<JSONObject> pushobjectListener = new HttpListener<JSONObject>() {


        @Override
        public void onSucceed(int what, Response<JSONObject> response) {

            btOk.setEnabled(true);
            hud.dismiss();

            try {
                JSONObject js = response.get();
                Log.e(TAG, "onSucceed123: "+js );
                int code = js.getInt("code");
                String msg = js.getString("msg");
                if (code == 200){
                    finish();
                }else {
                    ToastUtil.showToast(getApplicationContext(),msg);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {

            hud.dismiss();
            ToastUtil.showToast(getApplicationContext(),"访问网络失败，请检查您的网络！");
            btOk.setEnabled(true);
        }
    };

    private void scheduleDismiss() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hud.dismiss();
            }
        }, 2000);
    }
}
