package com.caesar.checkdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.caesar.checkdemo.adapter.PreviewPhotoAdapter;
import com.caesar.checkdemo.ui.ImageViewPager;

import java.util.ArrayList;

/**
 * 大图查看
 * Created by caesar on 2016/11/25.
 * 修订历史:
 */
public class LargePhotoViewActivity extends AppCompatActivity {
    private ImageViewPager mViewPager;
    private ArrayList<String> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Glide.with(LargePhotoViewActivity.this).load(url).into(pv1);
        setContentView(R.layout.preview_photo_activity);
        mViewPager = (ImageViewPager) findViewById(R.id.vp_preview);
        initData();
        PreviewPhotoAdapter mAdapter = new PreviewPhotoAdapter(LargePhotoViewActivity.this, images);
        mViewPager.setAdapter(mAdapter);

    }

    private void initData() {
        images.add("http://imgsrc.baidu.com/forum/pic/item/98dd3a87e950352a8f3d65855343fbf2b3118b89.jpg");
        images.add("http://dingyue.nosdn.127.net/ZbuC5q1OTbEWqttSEBOtJyN4shkTnEulOydyOD0ZQjeWu1446634682607.jpg");
        images.add("http://cdn.duitang.com/uploads/item/201505/03/20150503130550_RmWSx.thumb.700_0.jpeg");
        images.add("http://i.epetbar.com/thumb/2015-03/22/ec518667234e894b44ebc337b36af444.jpg-650-90000-d.jpg");
        images.add("http://7xjro5.com1.z0.glb.clouddn.com/upload/2016/12/02/20161202115419742.jpg");
    }


}
