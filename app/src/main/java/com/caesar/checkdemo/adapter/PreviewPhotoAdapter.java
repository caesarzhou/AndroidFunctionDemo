package com.caesar.checkdemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.caesar.checkdemo.R;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 大图预览
 * Created by caesar on 2016/11/25.
 * 修订历史:
 */
public class PreviewPhotoAdapter extends PagerAdapter {
    private Context mContext;
    private ArrayList<String> images = new ArrayList<>();

    public PreviewPhotoAdapter(Context mContext,ArrayList<String> images){
        this.mContext = mContext;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images == null ? 0 : images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.preview_photo_item,container,false);
        PhotoView imageView = (PhotoView) view.findViewById(R.id.pv1);
        Button btDelete = (Button) view.findViewById(R.id.bt_delete);
        final PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(imageView);
        photoViewAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                Activity activity = (Activity) mContext;
                activity.finish();
            }
        });
        Glide.with(mContext).load(images.get(position)).into(imageView);

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ALog.i("正在删除");
                images.remove(position);
                notifyDataSetChanged();
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public int getItemPosition(Object object) {
        // 最简单解决 notifyDataSetChanged() 页面不刷新问题的方法
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    //接口回掉，实现Recycler点击事件
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick( int position);
    }
}
