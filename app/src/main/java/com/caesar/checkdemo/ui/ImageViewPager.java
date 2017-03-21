package com.caesar.checkdemo.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 解决ViewPager里面图片缩放Bug
 * Created by caesar on 2016/12/2.
 * 修订历史:
 */
public class ImageViewPager extends ViewPager {

    public ImageViewPager(Context context) {
        super(context);
    }

    public ImageViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean b = false;
        try {
            b = super.onInterceptTouchEvent(ev);
        } catch (Exception e) {

        }
        return b; //网上看的方法是直接返回false，但是会导致ViewPager翻页有BUG
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            super.onTouchEvent(ev);
        } catch (Exception e) {

        }
        return false;
    }

}
