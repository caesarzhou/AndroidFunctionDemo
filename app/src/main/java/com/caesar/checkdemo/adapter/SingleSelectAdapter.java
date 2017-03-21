package com.caesar.checkdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.caesar.checkdemo.R;

/**
 * Description:
 * Function:
 * Created by caesar on 2016/9/29.
 * 修订历史:
 */
public class SingleSelectAdapter extends RecyclerView.Adapter<SingleSelectAdapter.MyViewHolder>{
    public static int selectPosition;//选中的位置
    private Context mContext;

    public SingleSelectAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder viewHolder = new MyViewHolder( LayoutInflater.from(mContext).
                inflate(R.layout.single_selection_item, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.mTextViewContent.setText(""+position);
        //如果设置了回调，则设置点击事件
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder.itemView,position);
                    notifyDataSetChanged();
                }
            });
        }
        holder.mRadioButton.setChecked(position == selectPosition);
    }

    @Override
    public int getItemCount() {
        return 60;
    }

    //ViewHolder
    public  static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewContent;
        public RadioButton mRadioButton;
        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewContent = (TextView) itemView.findViewById(R.id.tv);
            mRadioButton = (RadioButton) itemView.findViewById(R.id.rb);
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
