package com.example.FurnitureShare.ui.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.FurnitureShare.R;
import com.example.FurnitureShare.app.AllUrl;
import com.example.FurnitureShare.app.FSApplication;
import com.example.FurnitureShare.base.BaseActivity;
import com.example.FurnitureShare.bean.ProductInfo;
import com.example.FurnitureShare.entry.Addcart;
import com.example.FurnitureShare.entry.Destail;
import com.example.FurnitureShare.entry.LeasePrice;
import com.example.FurnitureShare.nohttp.HttpListener;
import com.example.FurnitureShare.utils.JsonUtil;
import com.example.FurnitureShare.utils.StaturBar;
import com.example.FurnitureShare.utils.ToastUtil;
import com.example.FurnitureShare.view.AmountView;
import com.jauker.widget.BadgeView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 物品详情
 */
public class DestailActivity extends BaseActivity {

    private static final String TAG = "DestailActivity";

    @BindView(R.id.bt_back)
    Button btBack;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.iv_shoppingcat)
    ImageView ivShoppingcat;
    @BindView(R.id.rl_shoppingcat)
    RelativeLayout rlShoppingcat;
    @BindView(R.id.rl_joinshoppingcat)
    RelativeLayout rlJoinshoppingcat;
    @BindView(R.id.tv_pay)
    TextView tvPay;
    @BindView(R.id.rl_raise)
    RelativeLayout rlRaise;

    private KProgressHUD hud;
    private String tvtitle;
    private String destailid;
    private int count = 1; //购买数量
    private String lease = ""; //租期
    private String pay_way = "";//付款方式
    private String userid;

    private String username;
    private String per;
    private String thumb;
    private String description;
    private String title;
    private String id;
    private String url;
    private Destail.DataBean data;
    private Destail destail;

    private BadgeView badgeView;
    private String click;
    private String status;
    private String price1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destail);
        ButterKnife.bind(this);
        // 添加到Activity集合
        FSApplication.instance.addActivity(this);

        // 系统 6.0 以上 状态栏白底黑字的实现方法
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        StaturBar.MIUISetStatusBarLightMode(getWindow(), true);
        StaturBar.FlymeSetStatusBarLightMode(getWindow(), true);

        Intent intent = getIntent();
        destailid = intent.getStringExtra("id");
        Log.e(TAG, "onCreate: " + destailid);
        tvtitle = intent.getStringExtra("title");
        tvTitle.setText(tvtitle);

        badgeView = new BadgeView(this);

        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
        hud.show();

        getList();
    }

    @Override
    protected void onResume() {
        super.onResume();

        status = FSApplication.instance.sp.getString("status", "");
        userid = FSApplication.instance.sp.getString("userid", "");
        Log.e(TAG, "onResume: " + userid);
    }

    private void getList() {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.DESTAIL + destailid);
        request(0, request, classdestailListener, true, true);

    }

    private HttpListener<JSONObject> classdestailListener = new HttpListener<JSONObject>() {


        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
//            scheduleDismiss();
            try {
                JSONObject js = response.get();
                Log.e(TAG, "list: " + js);
                int code = js.getInt("code");
                if (code == 200) {
                    destail = JsonUtil.parseJsonToBean(js.toString(), Destail.class);
                    if (destail != null) {
                        data = destail.getData();

                        id = data.getId();
                        title = data.getTitle();
                        description = data.getDescription();
                        thumb = data.getThumb();
                        per = data.getPer();
                        username = data.getUsername();

                        url = data.getUrl();
                        webView.loadUrl(url);
                        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY); // 设置无边框
                        Log.e(TAG, "onCreate: " + "http://www.9fat.com/H5test/farmapp0608/htmls/shoppingdetailspageapp.html?id=" + id);
                        //启用支持javascript
                        WebSettings settings = webView.getSettings();
                        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); // web内容强制满屏
//                        settings.setUseWideViewPort(true);//设置webview推荐使用的窗口
//                        settings.setLoadWithOverviewMode(true);//设置webview加载的页面的模式
//                        settings.setDisplayZoomControls(false);//隐藏webview缩放按钮
//                        settings.setJavaScriptEnabled(true); // 设置支持javascript脚本
//                        settings.setAllowFileAccess(true); // 允许访问文件
//                        settings.setBuiltInZoomControls(true); // 设置显示缩放按钮
//                        settings.setSupportZoom(true); // 支持缩放
                        // 设置显示完整网页
                        settings.setUseWideViewPort(true);
                        // 设置可以支持缩放
                        settings.setSupportZoom(true);
                        settings.setJavaScriptEnabled(true);
                        settings.setDomStorageEnabled(true);// 开启 DOM storage API 功能
                        settings.setDefaultTextEncodingName("utf-8");

                        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
                        webView.setWebViewClient(new WebViewClient() {

                            private String weburl;
                            private String type;

                            @Override
                            public void onPageFinished(WebView view, String url) {
                                super.onPageFinished(view, url);

                                scheduleDismiss();
                            }

                            @Override
                            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                // TODO Auto-generated method stub
                                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器

//                                Log.e(TAG, "shouldOverrideUrlLoading123: "+url);
//                                type = url.substring(0, url.indexOf(":"));
//                                Log.e(TAG, "shouldOverrideUrlLoadingtype: "+type );
//                                weburl = url.substring(url.indexOf(":") + 1);
//
//                                if (type.equals("pictureurl")){
//                                    ToastUtil.showToast(getBaseContext(),weburl);
//                                    return true;
//                                }
//
//                                if (type.equals("goodsid")){
//
//                                    Intent intent = new Intent();
//                                    intent.putExtra("id", weburl);
//                                    intent.putExtra("typename",typename);
//                                    intent.setClass(getBaseContext(),DetailsActivity.class);
//                                    startActivity(intent);
//
//                                    return true;
//                                }
                                return false;
                            }
                        });

                    }
                }


            } catch (Exception e) {
                Log.e(TAG, "Exception: " + "123");
                e.printStackTrace();
            }

        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {
            scheduleDismiss();
            ToastUtil.showToast(getBaseContext(), "请求失败");
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


    @OnClick({R.id.bt_back, R.id.rl_shoppingcat, R.id.rl_joinshoppingcat, R.id.rl_raise})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_back:
                finish();
                break;
            case R.id.rl_shoppingcat:
                if (status.equals("1")) {
                    Intent intent = new Intent();
//                intent.putExtra("id",id);
                    intent.setClass(getBaseContext(), ShoppingActivity.class);
                    startActivity(intent);

                } else {
                    ToastUtil.showToast(getBaseContext(), "请先登录");
                }
                break;
            case R.id.rl_joinshoppingcat:
                click = "1";
                showSelectDialog();
                break;
            case R.id.rl_raise:
                click = "2";
                showSelectDialog();
                break;
        }
    }

    private List<ProductInfo> productLists = new ArrayList<>();
    List<ProductInfo> products = new ArrayList<>();

    private void showSelectDialog(){
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
        window.setContentView(R.layout.selectdialog);
        //商品图片
        ImageView iv_shopp = (ImageView) window.findViewById(R.id.iv_shopp);
        Glide.with(getBaseContext()).load(thumb).into(iv_shopp);
        //商品价格
        TextView tv_price = (TextView) window.findViewById(R.id.tv_price);
        //取整（四舍五入）
        double d1 = Double.valueOf(per);
        double dd1=(double) (d1/24);
        int i1 = (int) Math.round(dd1);
        price1 = String.valueOf(i1);
        tv_price.setText("价格：¥"+price1+"元/月");
        //返回
        Button bt_back = (Button) window.findViewById(R.id.bt_back);
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.cancel();
            }
        });
        //商品租期
        final Button bt_six = (Button) window.findViewById(R.id.bt_six);
        final Button bt_nine = (Button) window.findViewById(R.id.bt_nine);
        final Button bt_twelve = (Button) window.findViewById(R.id.bt_twelve);
        final Button bt_fifteen = (Button) window.findViewById(R.id.bt_fifteen);
        final Button bt_eighteen = (Button) window.findViewById(R.id.bt_eighteen);
        final Button bt_twenty_one = (Button) window.findViewById(R.id.bt_twenty_one);
        final Button bt_twenty_four = (Button) window.findViewById(R.id.bt_twenty_four);
        bt_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lease = "6";
                bt_six.setBackgroundResource(R.drawable.selectdialog_btshape1);
                bt_six.setTextColor(Color.parseColor("#F6890C"));
                bt_nine.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_nine.setTextColor(Color.parseColor("#222222"));
                bt_twelve.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_twelve.setTextColor(Color.parseColor("#222222"));
                bt_fifteen.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_fifteen.setTextColor(Color.parseColor("#222222"));
                bt_eighteen.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_eighteen.setTextColor(Color.parseColor("#222222"));
                bt_twenty_one.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_twenty_one.setTextColor(Color.parseColor("#222222"));
                bt_twenty_four.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_twenty_four.setTextColor(Color.parseColor("#222222"));
            }
        });
        bt_nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lease = "9";
                bt_six.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_six.setTextColor(Color.parseColor("#222222"));
                bt_nine.setBackgroundResource(R.drawable.selectdialog_btshape1);
                bt_nine.setTextColor(Color.parseColor("#F6890C"));
                bt_twelve.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_twelve.setTextColor(Color.parseColor("#222222"));
                bt_fifteen.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_fifteen.setTextColor(Color.parseColor("#222222"));
                bt_eighteen.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_eighteen.setTextColor(Color.parseColor("#222222"));
                bt_twenty_one.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_twenty_one.setTextColor(Color.parseColor("#222222"));
                bt_twenty_four.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_twenty_four.setTextColor(Color.parseColor("#222222"));
            }
        });
        bt_twelve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lease = "12";
                bt_six.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_six.setTextColor(Color.parseColor("#222222"));
                bt_nine.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_nine.setTextColor(Color.parseColor("#222222"));
                bt_twelve.setBackgroundResource(R.drawable.selectdialog_btshape1);
                bt_twelve.setTextColor(Color.parseColor("#F6890C"));
                bt_fifteen.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_fifteen.setTextColor(Color.parseColor("#222222"));
                bt_eighteen.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_eighteen.setTextColor(Color.parseColor("#222222"));
                bt_twenty_one.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_twenty_one.setTextColor(Color.parseColor("#222222"));
                bt_twenty_four.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_twenty_four.setTextColor(Color.parseColor("#222222"));
            }
        });
        bt_fifteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lease = "15";
                bt_six.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_six.setTextColor(Color.parseColor("#222222"));
                bt_nine.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_nine.setTextColor(Color.parseColor("#222222"));
                bt_twelve.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_twelve.setTextColor(Color.parseColor("#222222"));
                bt_fifteen.setBackgroundResource(R.drawable.selectdialog_btshape1);
                bt_fifteen.setTextColor(Color.parseColor("#F6890C"));
                bt_eighteen.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_eighteen.setTextColor(Color.parseColor("#222222"));
                bt_twenty_one.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_twenty_one.setTextColor(Color.parseColor("#222222"));
                bt_twenty_four.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_twenty_four.setTextColor(Color.parseColor("#222222"));
            }
        });
        bt_eighteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lease = "18";
                bt_six.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_six.setTextColor(Color.parseColor("#222222"));
                bt_nine.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_nine.setTextColor(Color.parseColor("#222222"));
                bt_twelve.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_twelve.setTextColor(Color.parseColor("#222222"));
                bt_fifteen.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_fifteen.setTextColor(Color.parseColor("#222222"));
                bt_eighteen.setBackgroundResource(R.drawable.selectdialog_btshape1);
                bt_eighteen.setTextColor(Color.parseColor("#F6890C"));
                bt_twenty_one.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_twenty_one.setTextColor(Color.parseColor("#222222"));
                bt_twenty_four.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_twenty_four.setTextColor(Color.parseColor("#222222"));
            }
        });
        bt_twenty_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lease = "21";
                bt_six.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_six.setTextColor(Color.parseColor("#222222"));
                bt_nine.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_nine.setTextColor(Color.parseColor("#222222"));
                bt_twelve.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_twelve.setTextColor(Color.parseColor("#222222"));
                bt_fifteen.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_fifteen.setTextColor(Color.parseColor("#222222"));
                bt_eighteen.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_eighteen.setTextColor(Color.parseColor("#222222"));
                bt_twenty_one.setBackgroundResource(R.drawable.selectdialog_btshape1);
                bt_twenty_one.setTextColor(Color.parseColor("#F6890C"));
                bt_twenty_four.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_twenty_four.setTextColor(Color.parseColor("#222222"));
            }
        });
        bt_twenty_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lease = "24";
                bt_six.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_six.setTextColor(Color.parseColor("#222222"));
                bt_nine.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_nine.setTextColor(Color.parseColor("#222222"));
                bt_twelve.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_twelve.setTextColor(Color.parseColor("#222222"));
                bt_fifteen.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_fifteen.setTextColor(Color.parseColor("#222222"));
                bt_eighteen.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_eighteen.setTextColor(Color.parseColor("#222222"));
                bt_twenty_one.setBackgroundResource(R.drawable.selectdialog_btshape);
                bt_twenty_one.setTextColor(Color.parseColor("#222222"));
                bt_twenty_four.setBackgroundResource(R.drawable.selectdialog_btshape1);
                bt_twenty_four.setTextColor(Color.parseColor("#F6890C"));
            }
        });
        //付款方式
//        final Button bt_month = (Button) window.findViewById(R.id.bt_month);
//        final Button bt_season = (Button) window.findViewById(R.id.bt_season);
//        final Button bt_year = (Button) window.findViewById(R.id.bt_year);
//        final Button bt_full = (Button) window.findViewById(R.id.bt_full);
//        bt_month.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pay_way = "月付";
//                bt_month.setBackgroundResource(R.drawable.selectdialog_btshape1);
//                bt_month.setTextColor(Color.parseColor("#F6890C"));
//                bt_season.setBackgroundResource(R.drawable.selectdialog_btshape);
//                bt_season.setTextColor(Color.parseColor("#222222"));
//                bt_year.setBackgroundResource(R.drawable.selectdialog_btshape);
//                bt_year.setTextColor(Color.parseColor("#222222"));
//                bt_full.setBackgroundResource(R.drawable.selectdialog_btshape);
//                bt_full.setTextColor(Color.parseColor("#222222"));
//            }
//        });
//        bt_season.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pay_way = "季付";
//                bt_month.setBackgroundResource(R.drawable.selectdialog_btshape);
//                bt_month.setTextColor(Color.parseColor("#222222"));
//                bt_season.setBackgroundResource(R.drawable.selectdialog_btshape1);
//                bt_season.setTextColor(Color.parseColor("#F6890C"));
//                bt_year.setBackgroundResource(R.drawable.selectdialog_btshape);
//                bt_year.setTextColor(Color.parseColor("#222222"));
//                bt_full.setBackgroundResource(R.drawable.selectdialog_btshape);
//                bt_full.setTextColor(Color.parseColor("#222222"));
//            }
//        });
//        bt_year.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pay_way = "年付";
//                bt_month.setBackgroundResource(R.drawable.selectdialog_btshape);
//                bt_month.setTextColor(Color.parseColor("#222222"));
//                bt_season.setBackgroundResource(R.drawable.selectdialog_btshape);
//                bt_season.setTextColor(Color.parseColor("#222222"));
//                bt_year.setBackgroundResource(R.drawable.selectdialog_btshape1);
//                bt_year.setTextColor(Color.parseColor("#F6890C"));
//                bt_full.setBackgroundResource(R.drawable.selectdialog_btshape);
//                bt_full.setTextColor(Color.parseColor("#222222"));
//            }
//        });
//        bt_full.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pay_way = "全款";
//                bt_month.setBackgroundResource(R.drawable.selectdialog_btshape);
//                bt_month.setTextColor(Color.parseColor("#222222"));
//                bt_season.setBackgroundResource(R.drawable.selectdialog_btshape);
//                bt_season.setTextColor(Color.parseColor("#222222"));
//                bt_year.setBackgroundResource(R.drawable.selectdialog_btshape);
//                bt_year.setTextColor(Color.parseColor("#222222"));
//                bt_full.setBackgroundResource(R.drawable.selectdialog_btshape1);
//                bt_full.setTextColor(Color.parseColor("#F6890C"));
//            }
//        });

        AmountView amount_view = (AmountView) window.findViewById(R.id.amount_view);
        amount_view.setGoods_storage(50);
        amount_view.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
//                Toast.makeText(getApplicationContext(), "Amount=>  " + amount, Toast.LENGTH_SHORT).show();
                count = amount;
            }
        });
        Button bt_sure = (Button) window.findViewById(R.id.bt_sure);
        bt_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lease.equals("")){
                    ToastUtil.showToast(getBaseContext(),"请选择租期");
                    return;
                }
//                else if (pay_way.equals("")){
//                    ToastUtil.showToast(getBaseContext(),"请选择付款方式");
//                    return;
//                }
                else {
                    if (click.equals("1")){
                        PostShoppingCart();
                    }else {
                        //商品价格
                        Postleaseprice();
                    }

                    dlg.cancel();
                }
            }
        });


    }

    private void Postleaseprice(){
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.LEASEPRICE, RequestMethod.POST);
        if (request != null) {
            request.add("id", id);//商品id
            request.add("count", count);//数量
            request.add("leasedays", lease);//租借时长

            // 添加到请求队列
            request(0, request, leasepriceListener, true, true);
        }
    }

    private HttpListener<JSONObject> leasepriceListener = new HttpListener<JSONObject>() {

        private int leaseprice;
        private int foregift;
        private LeasePrice.DataBean data;
        private LeasePrice leasePrice;

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            try {

                JSONObject js = response.get();
                Log.e(TAG, "leasepriceListener: " + js);
                int code = js.getInt("code");
                if (code == 200) {
                    leasePrice = JsonUtil.parseJsonToBean(js.toString(), LeasePrice.class);
                    if (leasePrice != null) {
                        data = leasePrice.getData();
                        foregift = data.getForegift();
                        leaseprice = data.getLeaseprice();

                        products.add(new ProductInfo("", id,
                                title, thumb, description,lease,price1
                                , 1, username, "",String.valueOf(foregift),String.valueOf(leaseprice)));


                        Intent intent = new Intent();
                        intent.putExtra("productLists", (Serializable) products);
                        intent.putExtra("result", String.valueOf(leaseprice+foregift));

                        intent.setClass(getBaseContext(), SuerOrderActivity.class);
                        startActivity(intent);
                        products.clear();

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

    //添加购物车
    private void PostShoppingCart() {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.ADDSHOPPINGCAT, RequestMethod.POST);
        if (request != null) {
            request.add("dosubmit", 1);
            request.add("uid", userid);//用户id
            request.add("gid", id);//商品id
            request.add("title", title);//商品标题
            request.add("subhead", description);//商品副标题
            request.add("thumb", thumb);//商品缩略图地址
            request.add("per", per);//价格
            request.add("count", count);//数量
            request.add("leasedays", lease);//租借时长
            request.add("pay_way", pay_way);//支付方式
            request.add("shopname", username);//商店名称

            // 添加到请求队列
            request(0, request, addcartListener, true, true);
        }
    }

    private HttpListener<JSONObject> addcartListener = new HttpListener<JSONObject>() {

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            try {

                JSONObject js = response.get();
                Log.e(TAG, "addcartListener: " + js);
                int code = js.getInt("code");
                if (code == 200) {
                    Addcart addcart = JsonUtil.parseJsonToBean(js.toString(), Addcart.class);
                    if (addcart != null) {
                        Addcart.DataBean data = addcart.getData();
                        int count = data.getCount();

                        badgeView.setTargetView(ivShoppingcat);
                        badgeView.setBadgeCount(count);
                        badgeView.setBadgeGravity(Gravity.RIGHT | Gravity.TOP);

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


}
