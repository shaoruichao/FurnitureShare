package com.example.FurnitureShare.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.FurnitureShare.R;
import com.example.FurnitureShare.entry.Classify;
import com.example.FurnitureShare.entry.ClassifyList;

import java.util.List;

/**
 * 精选分类-全部分类
 * Created by src on 2017/7/11.
 */

public class MenuListAdapter extends BaseQuickAdapter<ClassifyList.DataBean> {


    private MenuListItemAdapter menuListItemAdapter;
    private String typename;

    public MenuListAdapter(List<ClassifyList.DataBean> data, String typename) {
        super(R.layout.menu_list_item,data);
        this.typename = typename;
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, ClassifyList.DataBean dataBean) {
        final int adapterPosition = baseViewHolder.getAdapterPosition();

        TextView tv_title = baseViewHolder.getView(R.id.tv_title);
        RecyclerView rv_menu_list = baseViewHolder.getView(R.id.rv_menu_list);

        baseViewHolder.setText(R.id.tv_title,dataBean.getCatname());

        List<ClassifyList.DataBean.CdataBean> cdata = dataBean.getCdata();
        menuListItemAdapter = new MenuListItemAdapter(cdata,typename);
        menuListItemAdapter.openLoadAnimation();
        rv_menu_list.setAdapter(menuListItemAdapter);
        rv_menu_list.setHasFixedSize(true);
        GridLayoutManager mgr = new GridLayoutManager(mContext, 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rv_menu_list.setLayoutManager(mgr);



    }

}
