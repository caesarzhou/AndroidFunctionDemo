package com.caesar.checkdemo.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.caesar.checkdemo.R;

import java.util.HashMap;
import java.util.List;

/**
 * 多选加强版
 * Created by caesar on 2016/12/8.
 * 修订历史:
 */
public class MultipleSelectionIntensifyAdapter extends RecyclerView.Adapter<MultipleSelectionIntensifyAdapter.MyViewHolder> {
    private Context mContext;
    /**
     * checkbox选中状态
     */
    public static HashMap<Integer, Boolean> selectMap = new HashMap<>();
    private List<String> mData;

    public MultipleSelectionIntensifyAdapter(Context context) {
        this.mContext = context;
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
       /* //初始化集合，确保每一个位置都有选中状态，否则会报空指针
        for (int i = 0; i < mData.size(); i++) {
            selectMap.put(i, true);
        }*/
        for (int i = 0; i < 90; i++) {
            selectMap.put(i, false);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder viewHolder = new MyViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.multiple_intensify_item, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) holder.rl.getLayoutParams();
        params.height = getScreenWidth(mContext) / 4;
        params.width = getScreenWidth(mContext) / 4;

        //如果设置了回调，则设置点击事件
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }
        //选中状态切换监听(切换背景)
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {//选中条目背景变换
                    holder.mCheckBox.setVisibility(View.VISIBLE);
                } else {
                    holder.mCheckBox.setVisibility(View.INVISIBLE);
                }
            }
        });
        holder.mCheckBox.setChecked(selectMap.get(position));
    }

    @Override
    public int getItemCount() {
        // 返回数据总数
        return 90;
    }

    // 重写的自定义ViewHolder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CheckBox mCheckBox;
        public ImageView mImageView;
        private RelativeLayout rl;

        public MyViewHolder(View v) {
            super(v);
            mCheckBox = (CheckBox) v.findViewById(R.id.cb_multiple);
            mImageView = (ImageView) v.findViewById(R.id.iv_multiple);
            rl = (RelativeLayout) v.findViewById(R.id.rl_multiple);
        }
    }

    //接口回掉，实现Recycler点击事件
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickLitener) {
        this.mOnItemClickListener = mOnItemClickLitener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    /**
     * 获取取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }
}
