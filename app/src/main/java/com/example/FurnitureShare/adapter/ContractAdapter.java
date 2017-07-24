package com.example.FurnitureShare.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.FurnitureShare.R;
import com.example.FurnitureShare.entry.Contract;
import com.example.FurnitureShare.entry.MyOrderAll;

import java.util.List;


/**
 * 我的合同
 * Created by yusheng on 2016/11/28.
 */
public class ContractAdapter extends BaseQuickAdapter<Contract.DataBean> {


    public ContractAdapter(List<Contract.DataBean> data) {
        super(R.layout.mycontract_item,data);
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, Contract.DataBean dataBean) {

        final int adapterPosition = baseViewHolder.getAdapterPosition();

        baseViewHolder.setText(R.id.tv_number,"合同编号"+dataBean.getNumber());
        String status = dataBean.getStatus();
        LinearLayout ll_operation = baseViewHolder.getView(R.id.ll_operation);
        final RelativeLayout rl_contract = baseViewHolder.getView(R.id.rl_contract);
        final Button bt_change = baseViewHolder.getView(R.id.bt_change);
        final Button bt_refund = baseViewHolder.getView(R.id.bt_refund);
        final Button bt_pay = baseViewHolder.getView(R.id.bt_pay);
        if (status.equals("1")){
            baseViewHolder.setText(R.id.tv_status,"履行中");
            ll_operation.setVisibility(View.GONE);
//            bt_change.setVisibility(View.GONE);
//            bt_refund.setVisibility(View.GONE);
//            bt_pay.setVisibility(View.GONE);
        }else if (status.equals("2")){
            baseViewHolder.setText(R.id.tv_status,"合同已到期");
            ll_operation.setVisibility(View.VISIBLE);
            bt_change.setVisibility(View.GONE);
            bt_refund.setVisibility(View.VISIBLE);
            bt_pay.setVisibility(View.GONE);
        }
        ImageView iv_thumb = baseViewHolder.getView(R.id.iv_thumb);
        Glide.with(mContext).load(dataBean.getThumb()).into(iv_thumb);
        baseViewHolder.setText(R.id.tv_title,dataBean.getTitle());
        String begintime = dataBean.getBegintime();
        String endtime = dataBean.getEndtime();
        baseViewHolder.setText(R.id.tv_time,begintime+"-"+endtime);
        baseViewHolder.setText(R.id.tv_price,"月租：¥"+dataBean.getPrice());

        if (mOnItemClickLitener != null){
            bt_change.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.changeonItemClick(bt_change,adapterPosition);

                }
            });
            bt_refund.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.refundonItemClick(bt_refund,adapterPosition);
                }
            });
            bt_pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.payonItemClick(bt_pay,adapterPosition);
                }
            });

            rl_contract.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.contractonItemClick(rl_contract,adapterPosition);
                }
            });
        }

//        baseViewHolder.setText(R.id.tv_leasedays,dataBean.getLeasedays()+"个月");
//        String per = dataBean.getPer();
//        //取整（四舍五入）
//        double d1 = Double.valueOf(per);
//        double dd1=(double) (d1/24);
//        int i1 = (int) Math.round(dd1);
//        String price1 = String.valueOf(i1);
//        baseViewHolder.setText(R.id.tv_price,"月租：¥"+price1);
//        baseViewHolder.setText(R.id.tv_foregift,"押金：¥"+dataBean.getForegift());
//        baseViewHolder.setText(R.id.tv_count,"×"+dataBean.getCount());
    }



    public interface OnItemClickListener{
        void changeonItemClick(Button bt_change, int postion);
        void refundonItemClick(Button bt_refund, int postion);
        void payonItemClick(Button bt_pay, int postion);
        void contractonItemClick(RelativeLayout rl_contract, int postion);
    }
    /**自定义条目点击监听*/
    private OnItemClickListener mOnItemClickLitener;

    public void setmOnItemClickLitener(OnItemClickListener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public void remove(int position) {
        super.remove(position);
        notifyItemRemoved(position);
    }

}
