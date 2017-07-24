package com.example.FurnitureShare.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.FurnitureShare.R;
import com.example.FurnitureShare.entry.ClassifyList;
import com.example.FurnitureShare.ui.activity.MenuDestailActivity;

import java.util.List;

/**
 * 精选分类-全部分类-item
 * Created by src on 2017/7/11.
 */

public class MenuListItemAdapter extends BaseQuickAdapter<ClassifyList.DataBean.CdataBean> {


    private String typename;
    public MenuListItemAdapter(List<ClassifyList.DataBean.CdataBean> data, String typename) {
        super(R.layout.menu_list_item1,data);
        this.typename = typename;
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, final ClassifyList.DataBean.CdataBean dataBean) {



        ImageView iv_thumb = baseViewHolder.getView(R.id.iv_menu);
        Glide.with(mContext).load(dataBean.getImage()).into(iv_thumb);
        baseViewHolder.setText(R.id.tv_menu,dataBean.getCatname());
        RelativeLayout rl_menulist_item = baseViewHolder.getView(R.id.rl_menulist_item);
        rl_menulist_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("catid",dataBean.getCatid());
//                intent.putExtra("typename",typename);
                intent.putExtra("typename",dataBean.getCatname());
                intent.setClass(mContext, MenuDestailActivity.class);
                mContext.startActivity(intent);
            }
        });

    }
}
