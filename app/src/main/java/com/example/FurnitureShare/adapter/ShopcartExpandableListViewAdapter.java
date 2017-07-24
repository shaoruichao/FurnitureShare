package com.example.FurnitureShare.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.FurnitureShare.R;
import com.example.FurnitureShare.app.AllUrl;
import com.example.FurnitureShare.bean.GroupInfo;
import com.example.FurnitureShare.bean.ProductInfo;
import com.example.FurnitureShare.utils.JsonUtil;
import com.example.FurnitureShare.view.widget.SlideView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;


public class ShopcartExpandableListViewAdapter extends BaseExpandableListAdapter {

    private static final String TAG = "ShopcartExpandableListV";
    private List<GroupInfo> groups;
    private Map<String, List<ProductInfo>> children;
    private Context context;
    //HashMap<Integer, View> groupMap = new HashMap<Integer, View>();
    //HashMap<Integer, View> childrenMap = new HashMap<Integer, View>();
    private CheckInterface checkInterface;
    private ModifyCountInterface modifyCountInterface;

    ArrayList<String> list = new ArrayList<>();

    private String editor = "1";

    /**
     * 构造函数
     *
     * @param groups   组元素列表
     * @param children 子元素列表
     * @param context
     */
    public ShopcartExpandableListViewAdapter(List<GroupInfo> groups, Map<String, List<ProductInfo>> children, Context context) {
        super();
        this.groups = groups;
        this.children = children;
        this.context = context;
    }

    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }

    public void setModifyCountInterface(ModifyCountInterface modifyCountInterface) {
        this.modifyCountInterface = modifyCountInterface;
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String groupId = groups.get(groupPosition).getId();
        return children.get(groupId).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        List<ProductInfo> childs = children.get(groups.get(groupPosition).getId());

        return childs.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder gholder;
        if (convertView == null) {
            gholder = new GroupHolder();
            convertView = View.inflate(context, R.layout.item_shopcart_group, null);
            gholder.cb_check = (CheckBox) convertView.findViewById(R.id.determine_chekbox);
            gholder.tv_group_name = (TextView) convertView.findViewById(R.id.tv_source_name);
            gholder.bt_editor = (Button) convertView.findViewById(R.id.bt_editor);
            //groupMap.put(groupPosition, convertView);
            convertView.setTag(gholder);
        } else {
            // convertView = groupMap.get(groupPosition);
            gholder = (GroupHolder) convertView.getTag();
        }
        final GroupInfo group = (GroupInfo) getGroup(groupPosition);
        if (group != null) {
            gholder.tv_group_name.setText(group.getName());
            gholder.cb_check.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v)

                {
                    group.setChoosed(((CheckBox) v).isChecked());
                    checkInterface.checkGroup(groupPosition, ((CheckBox) v).isChecked());// 暴露组选接口
                }
            });
            gholder.cb_check.setChecked(group.isChoosed());
            gholder.bt_editor.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        SlideView slideView = null;
        final ChildHolder cholder;
        if (convertView == null) {
            cholder = new ChildHolder();
            View view = View.inflate(context, R.layout.item_shopcart_product, null);
            slideView = new SlideView(context, context.getResources(), view);
            convertView = slideView;
            cholder.cb_check = (CheckBox) convertView.findViewById(R.id.check_box);

            cholder.iv_adapter_list_pic = (ImageView) convertView.findViewById(R.id.iv_adapter_list_pic);
            cholder.tv_product_desc = (TextView) convertView.findViewById(R.id.tv_intro);
            cholder.tv_leasepay = (TextView) convertView.findViewById(R.id.tv_leasepay);
            cholder.rl_leasepay = (RelativeLayout) convertView.findViewById(R.id.rl_leasepay);
            cholder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            cholder.iv_increase = (TextView) convertView.findViewById(R.id.tv_add);
            cholder.iv_decrease = (TextView) convertView.findViewById(R.id.tv_reduce);
            cholder.tv_count = (TextView) convertView.findViewById(R.id.tv_num);

            cholder.tv_delete = (ImageView) convertView.findViewById(R.id.back);
            // childrenMap.put(groupPosition, convertView);
            convertView.setTag(cholder);
        } else {
            // convertView = childrenMap.get(groupPosition);
            cholder = (ChildHolder) convertView.getTag();
        }
        final ProductInfo product = (ProductInfo) getChild(groupPosition, childPosition);

        if (product != null) {

            Glide.with(context)
                    .load(product.getImageUrl())
                    .into(cholder.iv_adapter_list_pic);
            cholder.tv_product_desc.setText(product.getSrcname());

            String leasedays = product.getLeasedays();
            String pay_way = product.getPay_way();
            cholder.tv_leasepay.setText(leasedays+"个月");
            String per = product.getPer();
            //取整（四舍五入）
            double d1 = Double.valueOf(per);
            double dd1=(double) (d1/24);
            int i1 = (int) Math.round(dd1);
            String price1 = String.valueOf(i1);
            cholder.tv_price.setText("月租 ¥"+price1);


            cholder.tv_count.setText(product.getCount() + "");
            cholder.cb_check.setChecked(product.isChoosed());
            cholder.cb_check.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    product.setChoosed(((CheckBox) v).isChecked());
                    cholder.cb_check.setChecked(((CheckBox) v).isChecked());
                    checkInterface.checkChild(groupPosition, childPosition, ((CheckBox) v).isChecked());// 暴露子选接口
                }
            });
            cholder.iv_increase.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    modifyCountInterface.doIncrease(groupPosition, childPosition, cholder.tv_count, cholder.cb_check.isChecked());// 暴露增加接口
                }
            });
            cholder.iv_decrease.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    modifyCountInterface.doDecrease(groupPosition, childPosition, cholder.tv_count, cholder.cb_check.isChecked());// 暴露删减接口

                }
            });
            cholder.rl_leasepay.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    modifyCountInterface.cartEdit(groupPosition, childPosition, cholder.tv_count, cholder.cb_check.isChecked());
                }
            });
        }
        cholder.tv_delete.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                List<ProductInfo> childs = children.get(groups.get(groupPosition).getId());
                ProductInfo remove = childs.remove(childPosition);
                //id和数量
                Log.e(TAG, "onClick: "+remove.getId()+"======"+remove.getCount() );
                String cartid = remove.getId();
                int count = remove.getCount();

                list.add(cartid);
                Log.e(TAG, "onClick: "+list );

//                //购物车编辑
                PostShoppingcartEdit();

                if(childs.size() ==0){//child没有了，group也就没有了
                    groups.remove(groupPosition);
                }
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    /**
     * 组元素绑定器
     */
    private class GroupHolder {
        CheckBox cb_check;
        TextView tv_group_name;
        Button bt_editor;
    }

    /**
     * 子元素绑定器
     */
    private class ChildHolder {
        CheckBox cb_check;

        ImageView iv_adapter_list_pic;
        TextView tv_product_name;
        TextView tv_product_desc;
        TextView tv_leasepay;
        RelativeLayout rl_leasepay;
        TextView tv_price;
        TextView iv_increase;
        TextView tv_count;
        TextView iv_decrease;
        ImageView tv_delete;
    }

    /**
     * 复选框接口
     */
    public interface CheckInterface {
        /**
         * 组选框状态改变触发的事件
         *
         * @param groupPosition 组元素位置
         * @param isChecked     组元素选中与否
         */
        public void checkGroup(int groupPosition, boolean isChecked);

        /**
         * 子选框状态改变时触发的事件
         *
         * @param groupPosition 组元素位置
         * @param childPosition 子元素位置
         * @param isChecked     子元素选中与否
         */
        public void checkChild(int groupPosition, int childPosition, boolean isChecked);
    }

    /**
     * 改变数量的接口
     */
    public interface ModifyCountInterface {
        /**
         * 增加操作
         *
         * @param groupPosition 组元素位置
         * @param childPosition 子元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        public void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked);

        /**
         * 删减操作
         *
         * @param groupPosition 组元素位置
         * @param childPosition 子元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        public void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked);

        /**
         * 购物车编辑
         */
        public void cartEdit(int groupPosition, int childPosition, View showCountView, boolean isChecked);
    }



    //购物车编辑
    private void PostShoppingcartEdit(){

        PostRequest tag = OkGo.post(AllUrl.SHOPPINGCATEDIT).tag(this);
        tag.params("dosubmit",1);
        for (int x=0 ; x< list.size();x++){
            tag.params("delete"+"["+x+"]", list.get(x));
            Log.e(TAG, "onActivityResult2222222: "+list.get(x) );
        }

        tag.execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.e(TAG, "onSuccess1: " + response);
//                DeleteCart deleteCart = JsonUtil.parseJsonToBean(response.toString(), DeleteCart.class);
//                int code = deleteCart.getCode();
//                String msg = deleteCart.getMsg();
//                if (code == 200){
//                    ToastUtil.showToast(context,msg);
//                }else {
//                    ToastUtil.showToast(context,msg);
//                }
            }

//            @Override
//            public void onSuccess(String s, Call call, okhttp3.Response response) {
//                Log.e(TAG, "onSuccess1: " + s);
//                DeleteCart deleteCart = JsonUtil.parseJsonToBean(s.toString(), DeleteCart.class);
//                int code = deleteCart.getCode();
//                String msg = deleteCart.getMsg();
//                if (code == 200){
//                    ToastUtil.showToast(context,msg);
//                }else {
//                    ToastUtil.showToast(context,msg);
//                }
//            }
        });

    }


}
