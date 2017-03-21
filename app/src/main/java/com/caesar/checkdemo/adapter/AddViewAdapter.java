package com.caesar.checkdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.caesar.checkdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 动态布局的列表适配器
 * Created by caesar on 2016/10/31.
 * 修订历史:
 */
public class AddViewAdapter extends RecyclerView.Adapter<AddViewAdapter.ViewHolder> {
    private Context mContext;
    private List<String> mData = new ArrayList<>();

    public  AddViewAdapter(Context context) {
        this.mContext = context;
        for(int i = 0;i < 25;i++) {
            mData.add(i,"条目"+i);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // 给ViewHolder设置布局文件
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.tv4.setText(mData.get(i));
        viewHolder.tv5.setText(mData.get(i));
        viewHolder.tv5.setText(mData.get(i));

        //如果设置了回调，则设置点击事件
        if (mOnItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(viewHolder.itemView, i);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        // 返回数据总数
        return mData == null ? 0 : mData.size();
    }

    // 重写的自定义ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv4;
        public TextView tv5;
        public TextView tv6;

        public ViewHolder(View v) {
            super(v);
            tv4 = (TextView) v.findViewById(R.id.tv4);
            tv5 = (TextView) v.findViewById(R.id.tv5);
            tv6 = (TextView) v.findViewById(R.id.tv6);
        }
    }


    //接口回掉，实现Recycler点击事件
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickLitener(OnItemClickListener mOnItemClickLitener) {
        this.mOnItemClickListener = mOnItemClickLitener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


}
