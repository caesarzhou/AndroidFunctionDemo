package com.caesar.checkdemo.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.caesar.checkdemo.R;
import com.caesar.checkdemo.model.ProductionManageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表嵌套，添加头布局
 * Created by caesar on 2016/11/30.
 * 修订历史:
 */
public class NestedListAdapter1 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_HEADER = 1;  //说明是带有Footer的
    public static final int TYPE_FOOTER = 1;  //说明是带有Footer的
    public static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的
    //HeaderView, FooterView
    private View mHeaderView;
    private View mFooterView;
    private Context mContext;
    private List<ProductionManageInfo.DataEntity> mData = new ArrayList<>();

    public NestedListAdapter1(Context mContext, List<ProductionManageInfo.DataEntity> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public void ReMoveHeaderView() {
        mHeaderView = null;
    }

    public void setFooterView(View footerView) {
        mFooterView = footerView;
        notifyItemInserted(getItemCount() - 1);
    }

    public void ReMoveFooterView() {
        mFooterView = null;
        //notifyItemRemoved(getItemCount() - 1);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mFooterView != null && viewType == TYPE_FOOTER) {//根据viewType值来判断设置不同的布局
            return new FooterHolder(mFooterView);
        } else if(mHeaderView != null && viewType == TYPE_HEADER) {
            return new HeaderHolder(mHeaderView);
        } else {
            View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.nested_list_item, parent, false);
            return new NormalHolder(layout);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        //设置不同的viewholder
        if (viewHolder instanceof NormalHolder) {
            NestedListAdapter1.NormalHolder holder = (NestedListAdapter1.NormalHolder) viewHolder;
            holder.tv_test.setText("测试"+position);
            holder.rv.setLayoutManager(new GridLayoutManager(mContext, 4));
            holder.rv.setAdapter(new OtherAdapter(mContext, mData.get(position-1).getImage_list()));
        } else if (viewHolder instanceof FooterHolder) {
            ((NestedListAdapter1.FooterHolder) viewHolder).tv.setText("加载中...");
        } else if(viewHolder instanceof HeaderHolder) {
            ((NestedListAdapter1.HeaderHolder) viewHolder).tv.setText("添加头布局");
        }
    }

    @Override
    public int getItemCount() {
        if (mFooterView != null && mHeaderView != null) {
            return mData.size() + 2;
        } else if(mHeaderView == null && mFooterView == null) {
            return mData.size();
        } else {
            return mData.size()+1;
        }
    }

    /**
     * 重写这个方法，很重要，是加入Footer的关键，我们通过判断item的类型，从而绑定不同的view    *
     */
    @Override
    public int getItemViewType(int position) {
        if (mFooterView != null && position == getItemCount() - 1) {
            return TYPE_FOOTER;
        } else if(mHeaderView != null && position == 0) {
            return TYPE_HEADER;
        } else {
            return TYPE_NORMAL;
        }
    }

    // 正常的ViewHolder
    class NormalHolder extends RecyclerView.ViewHolder {
        public TextView tv_test;
        public RecyclerView rv;

        public NormalHolder(View v) {
            super(v);
            tv_test = (TextView) v.findViewById(R.id.tv_test);
            rv = (RecyclerView) v.findViewById(R.id.nrv);
        }
    }

    //脚布局的viewHolder
    class HeaderHolder extends RecyclerView.ViewHolder {
        public TextView tv;

        public HeaderHolder(View v) {
            super(v);
            tv = (TextView) v.findViewById(R.id.tv_head);
        }
    }


    //脚布局的viewHolder
    class FooterHolder extends RecyclerView.ViewHolder {
        public TextView tv;

        public FooterHolder(View v) {
            super(v);
            tv = (TextView) v.findViewById(R.id.tv_foot);
        }
    }


    /**
     * 子列表适配器
     */

    public class OtherAdapter extends RecyclerView.Adapter<OtherAdapter.ViewHolder> {

        private Context mContext;
        private List<ProductionManageInfo.DataEntity.ImageListEntity> mImages = new ArrayList<>();

        public OtherAdapter(Context mContext, List<ProductionManageInfo.DataEntity.ImageListEntity> mImages) {
            this.mContext = mContext;
            this.mImages = mImages;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            // 给ViewHolder设置布局文件
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.nested_other_item, viewGroup, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
            Glide.with(mContext)
                    .load(mImages.get(i).getImg())
                    .thumbnail(0.1f)//表示为原图的十分之一
                    .into(viewHolder.iv_nested_other);
        }

        @Override
        public int getItemCount() {
            // 返回数据总数
            return mImages == null ? 0 : mImages.size();
        }

        // 重写的自定义ViewHolder
        public class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView iv_nested_other;

            public ViewHolder(View v) {
                super(v);
                iv_nested_other = (ImageView) v.findViewById(R.id.iv_nested_other);
            }
        }
    }

}
