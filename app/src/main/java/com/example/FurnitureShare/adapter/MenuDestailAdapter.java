package com.example.FurnitureShare.adapter;

import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.FurnitureShare.R;
import com.example.FurnitureShare.entry.ClassifyDestail;

import java.util.List;

/**
 * 精选分类- 详情adapter
 * Created by src on 2017/7/12.
 */

public class MenuDestailAdapter extends BaseQuickAdapter<ClassifyDestail.DataBean> {

    public MenuDestailAdapter(List<ClassifyDestail.DataBean> data) {
        super(R.layout.menu_destail,data);

    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ClassifyDestail.DataBean dataBean) {

        ImageView iv_thumb = baseViewHolder.getView(R.id.iv_thumb);
        Glide.with(mContext).load(dataBean.getThumb()).into(iv_thumb);
        baseViewHolder.setText(R.id.tv_title,dataBean.getTitle());
        String per = dataBean.getPer();

        //取整（四舍五入）
        double d = Double.valueOf(per);
        double dd=(double) (d/24);
        int i = (int) Math.round(dd);
        String price = String.valueOf(i);
        baseViewHolder.setText(R.id.tv_price,"¥"+price+"元/月");
    }
}
