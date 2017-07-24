package com.example.FurnitureShare.ui.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.FurnitureShare.R;
import com.example.FurnitureShare.adapter.ShopcartExpandableListViewAdapter;
import com.example.FurnitureShare.app.AllUrl;
import com.example.FurnitureShare.app.FSApplication;
import com.example.FurnitureShare.base.BaseActivity;
import com.example.FurnitureShare.bean.GroupInfo;
import com.example.FurnitureShare.bean.ProductInfo;
import com.example.FurnitureShare.entry.AllInfo;
import com.example.FurnitureShare.entry.LeasePrice;
import com.example.FurnitureShare.nohttp.HttpListener;
import com.example.FurnitureShare.utils.JsonUtil;
import com.example.FurnitureShare.utils.StaturBar;
import com.example.FurnitureShare.utils.ToastUtil;
import com.example.FurnitureShare.view.AmountView;
import com.example.FurnitureShare.view.widget.SuperExpandableListView;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class ShoppingActivity extends BaseActivity implements ShopcartExpandableListViewAdapter.CheckInterface, View.OnClickListener, ShopcartExpandableListViewAdapter.ModifyCountInterface{

    private static final String TAG = "ShoppingActivity";
    private SwipeRefreshLayout sw_layout;
    private SuperExpandableListView exListView;
    private CheckBox cb_check_all;
    private TextView tv_total_price;
    private TextView tv_delete;
    private TextView tv_go_to_pay;
    private LinearLayout ll_sureOrder;
    private FrameLayout fl_nomessage;

    private double totalPrice = 0.00;// 购买的商品总价
    private double totalforegift = 0.00;//购买商品的押金
    private int totalCount = 0;// 购买的商品总数量

    private ShopcartExpandableListViewAdapter selva;
    private List<GroupInfo> groups = new ArrayList<GroupInfo>();// 组元素数据列表
    private Map<String, List<ProductInfo>> children = new HashMap<>();// 子元素数据列表

    private TextView tv_check;
    private Button bt_delete;
    private TextView tv_head_delete;
    private String status;
    private String userid;

    private AllInfo allInfo;
    private boolean check = true;
    private boolean mark = true;
    private String result;
    private Button bt_back;
    private String point;
    private String lease = ""; //租期
    private int count = 1; //购买数量


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        // 添加到Activity集合
        FSApplication.instance.addActivity(this);

        // 系统 6.0 以上 状态栏白底黑字的实现方法
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        StaturBar.MIUISetStatusBarLightMode(getWindow(), true);
        StaturBar.FlymeSetStatusBarLightMode(getWindow(), true);

        initView();

    }

    private void initView() {
        bt_back = (Button) findViewById(R.id.bt_back);
        exListView = (SuperExpandableListView) findViewById(R.id.exListView);
        cb_check_all = (CheckBox) findViewById(R.id.all_chekbox);
        tv_check = (TextView)findViewById(R.id.tv_check);
        tv_total_price = (TextView) findViewById(R.id.tv_total_price);
        tv_delete = (TextView) findViewById(R.id.tv_delete);
        tv_go_to_pay = (TextView) findViewById(R.id.tv_go_to_pay);
        sw_layout = (SwipeRefreshLayout)findViewById(R.id.sw_layout);
        ll_sureOrder = (LinearLayout) findViewById(R.id.ll_sureOrder);
        fl_nomessage = (FrameLayout)findViewById(R.id.fl_nomessage);
        tv_head_delete = (TextView) findViewById(R.id.tv_head_delete);
        bt_delete = (Button) findViewById(R.id.bt_delete);

        //设置刷新的颜色
        sw_layout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        sw_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                //刷新的时候

//                hud = KProgressHUD.create(getActivity())
//                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
//                hud.show();

//                children = new HashMap<>();
                groups.clear();
                children.clear();
                PostShoppingcart();

                //停止刷新
                sw_layout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        status = FSApplication.instance.sp.getString("status", "");
        userid = FSApplication.instance.sp.getString("userid", "");
        point = FSApplication.instance.sp.getString("point", "");
        Log.e(TAG, "onResume: "+userid );

        tv_total_price.setText("0.00");
        tv_go_to_pay.setText("下单");
        cb_check_all.setChecked(false);

        groups.clear();
        children.clear();
        PostShoppingcart();
    }

    //购物车列表
    private void PostShoppingcart() {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.SHOPPINGCAT, RequestMethod.POST);
        if (request != null) {
//            request.add("dosubmit", 1);
            request.add("uid",userid);//用户id

            // 添加到请求队列
            request(0, request, cartListener, true, true);
        }
    }

    private HttpListener<JSONObject> cartListener = new HttpListener<JSONObject>() {

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            try {

                JSONObject js = response.get();
                Log.e(TAG, "cartListener: " + js);
                int code = js.getInt("code");
                if (code == 200){
                    allInfo = JsonUtil.parseJsonToBean(js.toString(), AllInfo.class);
                    List<AllInfo.DataBean> data = allInfo.getData();
                    if (data.size()==0){

                        sw_layout.setVisibility(View.GONE);
                        ll_sureOrder.setVisibility(View.GONE);
                        fl_nomessage.setVisibility(View.VISIBLE);

                    }else {

                        sw_layout.setVisibility(View.VISIBLE);
                        ll_sureOrder.setVisibility(View.VISIBLE);
                        fl_nomessage.setVisibility(View.GONE);

                        for (int i = 0; i < allInfo.getData().size(); i++) {
                            groups.add(new GroupInfo(i + "", allInfo.getData().get(i).getShopname(),"",allInfo.getData().get(i).getShopname()));
                            List<ProductInfo> products = new ArrayList<>();
                            List<AllInfo.DataBean.GoodsinfoBean> goodsinfo = allInfo.getData().get(i).getGoodsinfo();

           /* public ProductInfo(String id, String name, String imageUrl, String desc, String price, int count)*/
                            for (int j = 0; j < goodsinfo.size(); j++) {
                                products.add(new ProductInfo(goodsinfo.get(j).getId(),goodsinfo.get(j).getGid(), goodsinfo.get(j).getTitle()
                                        ,goodsinfo.get(j).getThumb(), goodsinfo.get(j).getSubhead(),goodsinfo.get(j).getLeasedays()
                                        ,goodsinfo.get(j).getPer(),Integer.parseInt(goodsinfo.get(j).getCount())
                                        ,allInfo.getData().get(i).getShopname(),goodsinfo.get(j).getPay_way(),goodsinfo.get(j).getForegift()
                                        ,goodsinfo.get(j).getLeaseprice()));
                            }
                            children.put(groups.get(i).getId(), products);// 将组元素的一个唯一值，这里取Id，作为子元素List的Key
                        }
                        initEvents();
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {

            ToastUtil.showToast(getBaseContext(),"访问网络失败，请检查您的网络！");

        }
    };

    private void initEvents() {
        selva = new ShopcartExpandableListViewAdapter(groups, children, getBaseContext());
        selva.setCheckInterface(this);// 关键步骤1,设置复选框接口
        selva.setModifyCountInterface(this);// 关键步骤2,设置数量增减接口
        exListView.setAdapter(selva);

        for (int i = 0; i < selva.getGroupCount(); i++) {
            exListView.expandGroup(i);// 关键步骤3,初始化时，将ExpandableListView以展开的方式呈现
        }

        cb_check_all.setOnClickListener(this);
        tv_delete.setOnClickListener(this);
        tv_go_to_pay.setOnClickListener(this);
        bt_delete.setOnClickListener(this);
        bt_back.setOnClickListener(this);
    }

    private List<ProductInfo> productLists = new ArrayList<>();
    private List<GroupInfo> groupsuerorder = new ArrayList<>();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_back:
                finish();
                break;

            case R.id.all_chekbox:
                if (check){
                    tv_check.setText("全部取消");
                    check = false;
                }else {
                    tv_check.setText("全部选中");
                    check = true;
                }
                doCheckAll();
                break;
            case R.id.tv_go_to_pay:
                Log.e(TAG, "onClicktotalCount: "+totalCount );
                if (totalCount == 0) {
                    Toast.makeText(getBaseContext(), "请选择要支付的商品", Toast.LENGTH_LONG).show();
                    return;
                }else if (!status.equals("1")){
                    Toast.makeText(getBaseContext(), "请先登录", Toast.LENGTH_LONG).show();
                    return;
                }

                ProductInfo product = null;
                GroupInfo group = null;

                for (int i = 0; i < groups.size(); i++) {
                    group = groups.get(i);
                    groupsuerorder.add(group);

                    List<ProductInfo> childs = children.get(group.getId());
                    for (int j = 0; j < childs.size(); j++) {
                        product = childs.get(j);

                        if (product.isChoosed()) {
                            totalCount = 0;

//                            String per = product.getPer();
//                            //取整（四舍五入）
//                            double d1 = Double.valueOf(per);
//                            double dd1=(double) (d1/24);
//                            int i1 = (int) Math.round(dd1);
//                            String price1 = String.valueOf(i1);
//                            Log.e(TAG, "onClick: "+price1 );

                            String leaseprice = product.getLeaseprice();
                            String foregift = product.getForegift();

                            totalPrice += Double.parseDouble(leaseprice);
                            totalforegift += Double.parseDouble(foregift);

                            productLists.add(product);


                        }
                    }
                }

                Intent intent = new Intent();
                intent.putExtra("productLists", (Serializable) productLists);
                intent.putExtra("result",result);
                intent.setClass(getBaseContext(),SuerOrderActivity.class);
                startActivity(intent);
                productLists.clear();
                break;
            case R.id.bt_delete:
                if (mark){
                    tv_head_delete.setText("完成");
                    tv_go_to_pay.setVisibility(View.GONE);
                    tv_delete.setVisibility(View.VISIBLE);
                    mark = false;
                }else {
                    tv_head_delete.setText("编辑");
                    tv_go_to_pay.setVisibility(View.VISIBLE);
                    tv_delete.setVisibility(View.GONE);
                    mark = true;
                }

                break;
            case R.id.tv_delete:
                if (totalCount == 0) {
                    Toast.makeText(getBaseContext(), "请选择要移除的商品", Toast.LENGTH_LONG).show();
                    return;
                }

                doDelete();

                break;
        }
    }

    ArrayList<String> list = new ArrayList<>();
    /**
     * 删除操作<br>
     * 1.不要边遍历边删除，容易出现数组越界的情况<br>
     * 2.现将要删除的对象放进相应的列表容器中，待遍历完后，以removeAll的方式进行删除
     */
    public void doDelete() {
        List<GroupInfo> toBeDeleteGroups = new ArrayList<GroupInfo>();// 待删除的组元素列表
        for (int i = 0; i < groups.size(); i++) {
            GroupInfo group = groups.get(i);
            if (group.isChoosed()) {

                toBeDeleteGroups.add(group);
            }
            List<ProductInfo> toBeDeleteProducts = new ArrayList<ProductInfo>();// 待删除的子元素列表
            List<ProductInfo> childs = children.get(group.getId());

            for (int j = 0; j < childs.size(); j++) {
                if (childs.get(j).isChoosed()) {
                    toBeDeleteProducts.add(childs.get(j));

                    int count = childs.get(j).getCount();
                    String cartid = childs.get(j).getId();
                    Log.e(TAG, "要删除的数量: "+count );
                    Log.e(TAG, "要删除的id: "+cartid );
                    list.add(cartid);
                    Log.e(TAG, "doDelete: "+list );

                }
            }
            childs.removeAll(toBeDeleteProducts);
        }

        Log.e(TAG, "doDelete: "+list );

        Postleaseprice();

        groups.removeAll(toBeDeleteGroups);

        selva.notifyDataSetChanged();
        calculate();
    }

    /** 全选与反选 */
    private void doCheckAll() {
        for (int i = 0; i < groups.size(); i++) {
            groups.get(i).setChoosed(cb_check_all.isChecked());
            GroupInfo group = groups.get(i);
            List<ProductInfo> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++) {
                childs.get(j).setChoosed(cb_check_all.isChecked());
            }
        }
        selva.notifyDataSetChanged();
        calculate();
    }

    /**
     * 统计操作<br>
     * 1.先清空全局计数器<br>
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作<br>
     * 3.给底部的textView进行数据填充
     */
    private void calculate() {
        totalCount = 0;
        totalPrice = 0.00;
        totalforegift = 0.00;
        for (int i = 0; i < groups.size(); i++) {
            GroupInfo group = groups.get(i);
            List<ProductInfo> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++) {
                ProductInfo product = childs.get(j);

                if (product.isChoosed()) {
                    totalCount++;

                    String per = product.getPer();
                    //取整（四舍五入）
                    double d1 = Double.valueOf(per);
                    double dd1=(double) (d1/24);
                    int i1 = (int) Math.round(dd1);
                    String price1 = String.valueOf(i1);
                    Log.e(TAG, "onClick: "+price1 );

                    String leaseprice = product.getLeaseprice();
                    String foregift = product.getForegift();

                    totalPrice += Double.parseDouble(leaseprice);
                    totalforegift += Double.parseDouble(foregift);

                }
            }
        }
        Log.e(TAG, "原始押金: "+totalforegift );
        //取整
        double d1 = Double.valueOf(point);
        double dd1=(double) (d1/100);
        Log.e(TAG, "calculatedd1: "+dd1 );
        double v = totalforegift - dd1;
        Log.e(TAG, "calculatevvv: "+v );
        int point1 = (int) Math.floor(v);
        Log.e(TAG, "calculate1111: "+point1 );

        //double 保留两位小数点
        if (point1 < 500 ){
            point1 = 500;
        }
        if (totalPrice == 0.00){
            point1 = 0;
        }

        //double 保留两位小数点
        result = String .format("%.2f",totalPrice + point1);
        tv_total_price.setText("¥" + result);
        tv_go_to_pay.setText("下单(" + totalCount + ")");
    }

    @Override
    public void checkGroup(int groupPosition, boolean isChecked) {
        GroupInfo group = groups.get(groupPosition);
        List<ProductInfo> childs = children.get(group.getId());
        for (int i = 0; i < childs.size(); i++) {
            childs.get(i).setChoosed(isChecked);
        }
        if (isAllCheck())
            cb_check_all.setChecked(true);
        else
            cb_check_all.setChecked(false);
        selva.notifyDataSetChanged();
        calculate();
    }

    @Override
    public void checkChild(int groupPosition, int childPosition, boolean isChecked) {
        boolean allChildSameState = true;// 判断改组下面的所有子元素是否是同一种状态
        GroupInfo group = groups.get(groupPosition);
        List<ProductInfo> childs = children.get(group.getId());
        for (int i = 0; i < childs.size(); i++) {
            if (childs.get(i).isChoosed() != isChecked) {
                allChildSameState = false;
                break;
            }
        }
        if (allChildSameState) {
            group.setChoosed(isChecked);// 如果所有子元素状态相同，那么对应的组元素被设为这种统一状态
        } else {
            group.setChoosed(false);// 否则，组元素一律设置为未选中状态
        }

        if (isAllCheck())
            cb_check_all.setChecked(true);
        else
            cb_check_all.setChecked(false);
        selva.notifyDataSetChanged();
        calculate();
    }


    public String doid = "";
    public String c = "";
    ArrayList<String> addlist = new ArrayList<>();
    Map<Integer, String> testMap = new HashMap<Integer, String>();

    //增加
    @Override
    public void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {
        ProductInfo product = (ProductInfo) selva.getChild(groupPosition, childPosition);
        int currentCount = product.getCount();
        String leasedays = product.getLeasedays();
        currentCount++;
        product.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        String cartid = product.getId();

        testMap.put(Integer.parseInt(cartid),currentCount+"");

        Log.e(TAG, "doIncrease: "+testMap.toString() );

//        doid = "{";
        doid = cartid;
        count = currentCount;
        lease = leasedays;


        Postleaseprice();

        selva.notifyDataSetChanged();
        calculate();
    }

    //减少
    @Override
    public void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {
        ProductInfo product = (ProductInfo) selva.getChild(groupPosition, childPosition);
        int currentCount = product.getCount();
        String leasedays = product.getLeasedays();
        if (currentCount == 1)
            return;
        currentCount--;

        product.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        String cartid = product.getId();

        doid = cartid;
        count = currentCount;
        lease = leasedays;

//        PostShoppingcartEdit();
        Postleaseprice();

        selva.notifyDataSetChanged();
        calculate();
    }

    @Override
    public void cartEdit(int groupPosition, int childPosition, View showCountView, boolean isChecked) {
        ProductInfo product = (ProductInfo) selva.getChild(groupPosition, childPosition);

        String imageUrl = product.getImageUrl();
        String per = product.getPer();
        String cartid = product.getId();

        doid = cartid;

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
        Glide.with(this).load(imageUrl).into(iv_shopp);
        //商品价格
        TextView tv_price = (TextView) window.findViewById(R.id.tv_price);
        //取整（四舍五入）
        double d1 = Double.valueOf(per);
        double dd1 = (double) (d1 / 24);
        int i1 = (int) Math.round(dd1);
        String price1 = String.valueOf(i1);
        tv_price.setText("价格：¥" + price1 + "元/月");
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
                if (lease.equals("")) {
                    ToastUtil.showToast(getBaseContext(), "请选择租期");
                    return;
                }
//                else if (pay_way.equals("")){
//                    ToastUtil.showToast(getBaseContext(),"请选择付款方式");
//                    return;
//                }
                else {
//                    if (click.equals("1")){
//                        PostShoppingCart();
//                    }else {
//                        //商品价格
                    Postleaseprice();
//                    }

                    dlg.cancel();
                }
            }
        });

    }

    private boolean isAllCheck() {
        for (GroupInfo group : groups) {
            if (!group.isChoosed())
                return false;
        }
        return true;
    }


    /**
     * 购物车编辑
     */
    private void Postleaseprice() {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.SHOPPINGCATEDIT, RequestMethod.POST);
        if (request != null) {

            for (int x = 0; x < list.size(); x++) {
                request.add("delete" + "[" + x + "]", list.get(x));
                Log.e(TAG, "onActivityResult2222222: " + list.get(x));
            }

            request.add("dosubmit", 1);
            request.add("edit", 1);
            request.add("id", doid);//商品id
            Log.e(TAG, "Postleaseprice: " + doid);
            request.add("count", count);//数量
            Log.e(TAG, "Postleaseprice: " + count);
            request.add("leasedays", lease);//租借时长
            Log.e(TAG, "Postleaseprice: " + lease);

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
//                    leasePrice = JsonUtil.parseJsonToBean(js.toString(), LeasePrice.class);
//                    if (leasePrice != null) {
                    groups.clear();
                    children.clear();
                    PostShoppingcart();
//                        data = leasePrice.getData();
//                        foregift = data.getForegift();
//                        leaseprice = data.getLeaseprice();
//
//                        products.add(new ProductInfo("", id,
//                                title, thumb, description,lease,String.valueOf(leaseprice)
//                                , 1, username, "",String.valueOf(foregift)));
//
//
//                        Intent intent = new Intent();
//                        intent.putExtra("productLists", (Serializable) products);
//                        intent.putExtra("result", String.valueOf(leaseprice+foregift));
//
//                        intent.setClass(getBaseContext(), SuerOrderActivity.class);
//                        startActivity(intent);
//                        products.clear();

//                    }
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

    //购物车编辑
//    private void PostShoppingcartEdit(){
//        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.SHOPPINGCATEDIT, RequestMethod.POST);
//        if (request != null) {
//
//            for (int x=0 ; x< list.size();x++){
//                request.add("delete"+"["+x+"]", list.get(x));
//                Log.e(TAG, "onActivityResult2222222: "+list.get(x) );
//            }
//
//            request.add("dosubmit", 1);
//            request.add("count", c);//购物车id+对应的数量
//            request.add("cartid", doid);//购物车id+对应的数量
//
//
//            // 添加到请求队列
//            request(0, request, carteditListener, true, true);
//        }
//    }
//
//    private HttpListener<JSONObject> carteditListener = new HttpListener<JSONObject>() {
//
//        @Override
//        public void onSucceed(int what, Response<JSONObject> response) {
//            try {
//
//                JSONObject js = response.get();
//                Log.e(TAG, "carteditListener: " + js);
//                int code = js.getInt("code");
//                String mgs = js.getString("msg");
//                if (code == 200){
//                    ToastUtil.showToast(getBaseContext(),mgs);
//                }else {
//                    ToastUtil.showToast(getBaseContext(),mgs);
//                }
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
//            ToastUtil.showToast(getBaseContext(),"访问网络失败，请检查您的网络！");
//
//        }
//    };


}
