
package com.example.FurnitureShare.view.Swipe;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.example.FurnitureShare.R;
import com.example.FurnitureShare.entry.AddressList;

import java.util.ArrayList;


/**
 * 地址管理adapter
 */
public class SwipeAdapter extends BaseAdapter {
    private static final String TAG = "SwipeAdapter";
    private ArrayList<AddressList.DataBean> lists;
    /**
     * 上下文对象
     */
    private Context mContext = null;

    /**
     *
     */
    private int mRightWidth = 0;

    /**
     * 单击事件监听器
     */
    private IOnItemRightClickListener mListener = null;



    public interface IOnItemRightClickListener {
        void onRightClick(View v, int position);
    }

    /**
     * @param
     */
    public SwipeAdapter(Context ctx, int rightWidth, IOnItemRightClickListener l, ArrayList<AddressList.DataBean> list) {
        mContext = ctx;
        mRightWidth = rightWidth;
        mListener = l;
        lists = list;

    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AddressList.DataBean dataBean = lists.get(position);

        ViewHolder item;
        final int thisPosition = position;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.addressmanage_item, parent, false);
            item = new ViewHolder();

            item.item_left = (View) convertView.findViewById(R.id.shippinginfor);
            item.item_right = (View) convertView.findViewById(R.id.item_right);
            item.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            item.tv_phone = (TextView) convertView.findViewById(R.id.tv_phone);
            item.tv_address = (TextView) convertView.findViewById(R.id.tv_address);
            item.bt = (Button)convertView.findViewById(R.id.bt);

            convertView.setTag(item);
        } else {// 有直接获得ViewHolder
            item = (ViewHolder) convertView.getTag();
        }
        LayoutParams lp1 = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        item.item_left.setLayoutParams(lp1);
        LayoutParams lp2 = new LayoutParams(mRightWidth, LayoutParams.MATCH_PARENT);
        item.item_right.setLayoutParams(lp2);


        String linkman = dataBean.getLinkman();
        String telnumber = dataBean.getTelnumber();
        String province = dataBean.getProvince();
        String city = dataBean.getCity();
        String country = dataBean.getCountry();
        String detail = dataBean.getDetail();

        Log.e(TAG, "getView: "+position );
        if (position == 0){
            item.bt.setVisibility(View.VISIBLE);
        }else {
            item.bt.setVisibility(View.GONE);
        }

        item.tv_name.setText(linkman);


        //手机号带有*
        telnumber = telnumber.substring(0, 3) + "****" + telnumber.substring(7, 11);
        item.tv_phone.setText(telnumber);

        item.tv_address.setText(province+city+country+detail);

        item.item_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onRightClick(v, thisPosition);
                }
            }
        });
        return convertView;
    }

    private class ViewHolder {
        View item_left;
        View item_right;

        TextView tv_name;
        TextView tv_phone;
        TextView tv_address;
        Button bt;

    }
}
