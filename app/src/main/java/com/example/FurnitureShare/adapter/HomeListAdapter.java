package com.example.FurnitureShare.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.FurnitureShare.R;
import com.example.FurnitureShare.entry.HomeLIst;
import com.example.FurnitureShare.ui.activity.DestailActivity;
import com.example.FurnitureShare.ui.activity.HomeListDestailActivity;
import com.example.FurnitureShare.ui.activity.MenuDestailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.FurnitureShare.app.FSApplication.mContext;

/**
 * 首页adapter
 */
public class HomeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private static final String TAG = "RvAdapter";

    /**普通商品的（normalHolder）的标题集合,调用者传入*/
    private ArrayList<HomeLIst.DataBeanX> normalGoodsTitls;
    /**头布局总数*/
    private int HEADER_CONUNT = 2;

    private Context mcontext;
    private HomeLIst.DataBeanX dataBean;
    private List<HomeLIst.DataBeanX.DataBean> data;


    public HomeListAdapter(Context context, ArrayList<HomeLIst.DataBeanX> normalGoodsTitls) {
        this.mcontext = context;
        this.normalGoodsTitls = normalGoodsTitls;
    }

    private int HEADER0 = 0;
    private int HEADER1 = 1;

    private int NORMAL = 100;

    private View headView0;
    private View headView1;


    public void setHeadView0(View headView0) {
        this.headView0 = headView0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER0) {
            return new BannerHolder(headView0);
        }else if(viewType==HEADER1){
            return new GridMenuHolder(headView1);
        } else {
            View view = LayoutInflater.from(mcontext).inflate(R.layout.home_list, parent,false);

            return new NormalHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int viewType = getItemViewType(position);
        Log.e(TAG, "holder位置: "+holder.getLayoutPosition() );

        if (viewType == HEADER0 ) {
            return;
        }else if(viewType==HEADER1){
            return;
        }else if(viewType==NORMAL){
            NormalHolder normalHolder= (NormalHolder) holder;
//            final int realPostion=position - HEADER_CONUNT;//获取真正的位置

            if (position > 1 && position <= normalGoodsTitls.size()+1){
                if (mOnItemClickLitener != null) {
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            int pos = realPostion;
                            mOnItemClickLitener.onItemClick(holder.itemView, position);
                        }
                    });
                    holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
                    {
                        @Override
                        public boolean onLongClick(View v)
                        {
//                            int pos = realPostion;
                            mOnItemClickLitener.onItemLongClick(holder.itemView, position);
                            return true;
                        }
                    });
                }

                Log.e(TAG, "位置: "+position );

                dataBean = normalGoodsTitls.get(position - 2);
                data = dataBean.getData();

                String thumb = dataBean.getThumb();
                Glide.with(mcontext)
                        .load(thumb)
                        .into(normalHolder.ivScenario);
                final String name = dataBean.getName();
                normalHolder.tvScenarioTitle.setText(name);
                normalHolder.tvScenario.setText("场景·"+name);
                String des = dataBean.getDes();
                normalHolder.tvScenarioDes.setText(des);
                normalHolder.btScenario.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra("posid",dataBean.getPosid());
                        intent.putExtra("typename",name);
                        intent.setClass(mContext, HomeListDestailActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                    }
                });


                String thumb1 = data.get(0).getThumb();
                Glide.with(mcontext)
                        .load(thumb1)
                        .into(normalHolder.ivScenario1);
                final String id0 = data.get(0).getId();
                final String title0 = data.get(0).getTitle();
                normalHolder.ivScenario1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra("id",id0);
                        Log.e(TAG, "onClick: "+id0 );
                        intent.putExtra("title",name);
                        intent.setClass(mContext, DestailActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                    }
                });
                normalHolder.tvScenarioTitle1.setText(title0);
                String per1 = data.get(0).getPer();
                //取整（四舍五入）
                double d1 = Double.valueOf(per1);
                double dd1=(double) (d1/24);
                int i1 = (int) Math.round(dd1);
                String price1 = String.valueOf(i1);
                normalHolder.tvScenarioPrice1.setText("¥"+price1+"元/月");

                String thumb2 = data.get(1).getThumb();
                Glide.with(mcontext)
                        .load(thumb2)
                        .into(normalHolder.ivScenario2);
                final String id1 = data.get(1).getId();
                final String title1 = data.get(1).getTitle();
                normalHolder.ivScenario2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra("id",id1);
                        intent.putExtra("title",name);
                        intent.setClass(mContext, DestailActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                    }
                });
                normalHolder.tvScenarioTitle2.setText(title1);
                String per2 = data.get(1).getPer();
                double d2 = Double.valueOf(per2);
                double dd2=(double) (d2/24);
                int i2 = (int) Math.round(dd2);
                String price2 = String.valueOf(i2);
                normalHolder.tvScenarioPrice2.setText("¥"+price2+"元/月");

                String thumb3 = data.get(2).getThumb();
                Glide.with(mcontext)
                        .load(thumb3)
                        .into(normalHolder.ivScenario3);
                final String id2 = data.get(2).getId();
                final String title2 = data.get(2).getTitle();
                normalHolder.ivScenario3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra("id",id2);
                        intent.putExtra("title",name);
                        intent.setClass(mContext, DestailActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                    }
                });
                normalHolder.tvScenarioTitle3.setText(title2);
                String per3 = data.get(2).getPer();
                double d3 = Double.valueOf(per3);
                double dd3=(double) (d3/24);
                int i3 = (int) Math.round(dd3);
                String price3 = String.valueOf(i3);
                normalHolder.tvScenarioPrice3.setText("¥"+price3+"元/月");


            }


        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && headView0 != null) {
            return HEADER0;
        }else if(position==1&&headView1!=null){
            return HEADER1;
        }
        else {
            return NORMAL;
        }
    }

    //有7条普通数据，但是要加上Header的总数
    @Override
    public int getItemCount() {
        return normalGoodsTitls.size()+HEADER_CONUNT;
    }

    /**
     * 添加顶部banner
     */
    public void  addHeadView0(View view) {
        this.headView0 = view;
    }
    /**添加10个子菜单*/
    public void addHeaderView1(View v) {
        this.headView1 = v;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){


        }
    }

    //顶部banner
    class BannerHolder extends RecyclerView.ViewHolder {

        public BannerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    //10个子菜单
    class GridMenuHolder extends RecyclerView.ViewHolder{

        public GridMenuHolder(View itemView) {
            super(itemView);
        }
    }

    //普通的Holder
    class NormalHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_scenario)
        TextView tvScenario;
        @BindView(R.id.iv_scenario)
        ImageView ivScenario;
        @BindView(R.id.tv_scenario_title)
        TextView tvScenarioTitle;
        @BindView(R.id.tv_scenario_des)
        TextView tvScenarioDes;
        @BindView(R.id.iv_scenario1)
        ImageView ivScenario1;
        @BindView(R.id.tv_scenario_title1)
        TextView tvScenarioTitle1;
        @BindView(R.id.tv_scenario_price1)
        TextView tvScenarioPrice1;
        @BindView(R.id.iv_scenario2)
        ImageView ivScenario2;
        @BindView(R.id.tv_scenario_title2)
        TextView tvScenarioTitle2;
        @BindView(R.id.tv_scenario_price2)
        TextView tvScenarioPrice2;
        @BindView(R.id.iv_scenario3)
        ImageView ivScenario3;
        @BindView(R.id.tv_scenario_title3)
        TextView tvScenarioTitle3;
        @BindView(R.id.tv_scenario_price3)
        TextView tvScenarioPrice3;
        @BindView(R.id.bt_scenario)
        Button btScenario;

        public NormalHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public interface OnItemClickListener{
        void onItemClick(View v, int postion);
        void onItemLongClick(View v, int postion);
    }
    /**自定义条目点击监听*/
    private OnItemClickListener mOnItemClickLitener;

    public void setmOnItemClickLitener(OnItemClickListener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
}
