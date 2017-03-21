package com.caesar.checkdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.caesar.checkdemo.R;

import java.util.HashMap;

/**
 * 单选列表适配器：(利用自定义HashMap来存储选中状态)
 * Created by caesar on 2016/9/29.
 * 修订历史:
 */
public class MultipleSelectAdapter extends RecyclerView.Adapter<MultipleSelectAdapter.MyViewHolder> {
    private Context mContext;
    /** checkbox选中状态 */
    public static HashMap<Integer, Boolean> selectMap = new HashMap<>();

    public MultipleSelectAdapter(Context context) {
        this.mContext = context;
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        //初始化集合，确保每一个位置都有选中状态，否则会报空指针
        for(int i=0;i<=60;i++) {
            selectMap.put(i,false);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder viewHolder = new MyViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.multiple_selection_item, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv_item_single.setText("" + position);
        //如果设置了回调，则设置点击事件
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder.itemView,position);
                }
            });
        }
        holder.mCheckBox.setChecked(selectMap.get(position));
    }

    @Override
    public int getItemCount() {
        return 60;
    }

    // 重写的自定义ViewHolder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CheckBox mCheckBox;
        public TextView tv_item_single;

        public MyViewHolder(View v) {
            super(v);
            mCheckBox = (CheckBox) v.findViewById(R.id.cb_single);
            tv_item_single = (TextView) v.findViewById(R.id.tv_item_single);
        }
    }

    //接口回掉，实现Recycler点击事件
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickLitener(OnItemClickListener mOnItemClickLitener)
    {
        this.mOnItemClickListener = mOnItemClickLitener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view,int position);
    }

}


