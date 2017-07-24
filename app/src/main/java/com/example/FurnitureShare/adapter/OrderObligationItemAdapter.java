package com.example.FurnitureShare.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.FurnitureShare.R;
import com.example.FurnitureShare.entry.MyOrderAll;

import java.util.List;


/**
 * 我的订单-待付款-item
 * Created by yusheng on 2016/11/28.
 */
public class OrderObligationItemAdapter extends BaseQuickAdapter<MyOrderAll.DataBean.GoodsinfoBean> {


    public OrderObligationItemAdapter(List<MyOrderAll.DataBean.GoodsinfoBean> data) {
        super(R.layout.myorder_all_item_item,data);
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, MyOrderAll.DataBean.GoodsinfoBean dataBean) {

        ImageView iv_thumb = baseViewHolder.getView(R.id.iv_thumb);
        Glide.with(mContext).load(dataBean.getThumb()).into(iv_thumb);

        baseViewHolder.setText(R.id.tv_title,dataBean.getTitle());
        baseViewHolder.setText(R.id.tv_leasedays,dataBean.getLeasedays()+"个月");
        String per = dataBean.getPer();
        //取整（四舍五入）
        double d1 = Double.valueOf(per);
        double dd1=(double) (d1/24);
        int i1 = (int) Math.round(dd1);
        String price1 = String.valueOf(i1);
        baseViewHolder.setText(R.id.tv_price,"月租：¥"+price1);
        baseViewHolder.setText(R.id.tv_foregift,"押金：¥"+dataBean.getForegift());
        baseViewHolder.setText(R.id.tv_count,"×"+dataBean.getCount());
    }
}
