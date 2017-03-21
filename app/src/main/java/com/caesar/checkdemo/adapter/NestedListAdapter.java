package com.caesar.checkdemo.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.caesar.checkdemo.R;
import com.caesar.checkdemo.model.ProductionManageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表嵌套
 * Created by caesar on 2016/11/30.
 * 修订历史:
 */
public class NestedListAdapter extends RecyclerView.Adapter<NestedListAdapter.ViewHolder> {
    private Context mContext;

    public NestedListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // 给ViewHolder设置布局文件
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.nested_list_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.mTvContent.setText("测试"+i);
        viewHolder.rv.setLayoutManager(new LinearLayoutManager(mContext));
        viewHolder.rv.setAdapter(new OtherAdapter(mContext));
    }

    @Override
    public int getItemCount() {
        // 返回数据总数
        //return mData == null ? 0 : mData.size();
        return 8;
    }

    // 重写的自定义ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvContent;
        public RecyclerView rv;

        public ViewHolder(View v) {
            super(v);
            rv = (RecyclerView) v.findViewById(R.id.nrv);
            mTvContent = (TextView) v.findViewById(R.id.tv_test);
        }
    }



    /**
     * 子列表适配器
     */

    public  class OtherAdapter extends RecyclerView.Adapter<OtherAdapter.ViewHolder> {

        private Context mContext;
        private List<ProductionManageInfo.DataEntity.ImageListEntity> mImages = new ArrayList<>();

        public OtherAdapter(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            // 给ViewHolder设置布局文件
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.nested_other_item, viewGroup, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final ViewHolder viewHolder, final int i) {

        }

        @Override
        public int getItemCount() {
            // 返回数据总数
           // return mImages == null ? 0 : mImages.size();
            return 8;
        }

        // 重写的自定义ViewHolder
        public  class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView iv_nested_other;

            public ViewHolder(View v) {
                super(v);
                iv_nested_other = (ImageView) v.findViewById(R.id.iv_nested_other);
            }
        }
    }

}
