package com.example.FurnitureShare.adapter;

import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.FurnitureShare.R;
import com.example.FurnitureShare.bean.ProductInfo;

import java.util.List;

/**
 *  确认订单adapter
 */
public class SureOrderAdapter extends BaseQuickAdapter<ProductInfo> {


    public SureOrderAdapter(List<ProductInfo> data) {
        super(R.layout.suerorder_item,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ProductInfo guessBean) {


        TextView tv_shopname = baseViewHolder.getView(R.id.tv_shopname);
        String shopname = guessBean.getShopname();
        Log.e(TAG, "convert: "+shopname );

        boolean top = guessBean.isTop();
        if (top == true){

            baseViewHolder.setText(R.id.tv_shopname,shopname);
        }else {
            tv_shopname.setVisibility(View.GONE);
        }
        String leasedays = guessBean.getLeasedays();
        baseViewHolder.setText(R.id.tv_leasedays,leasedays+"个月");

        String per = guessBean.getPer();
        //取整（四舍五入）
//        double d1 = Double.valueOf(per);
//        double dd1=(double) (d1/24);
//        int i1 = (int) Math.round(dd1);
//        String price1 = String.valueOf(i1);
        baseViewHolder.setText(R.id.tv_price,"月租：¥"+per);

        String foregift = guessBean.getForegift();//押金
//        baseViewHolder.setText(R.id.tv_foregift,"押金：¥"+foregift);

        ImageView iv_thumb = baseViewHolder.getView(R.id.iv_thumb);

        Glide.with(mContext).load(guessBean.getImageUrl()).into(iv_thumb);

        baseViewHolder.setText(R.id.tv_title,guessBean.getSrcname());
        baseViewHolder.setText(R.id.tv_count,"×"+String.valueOf(guessBean.getCount()));

    }
}
