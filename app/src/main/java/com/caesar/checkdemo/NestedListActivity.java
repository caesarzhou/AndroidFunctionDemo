package com.caesar.checkdemo;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.caesar.checkdemo.adapter.NestedListAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 列表嵌套解决方案(重构服务器数据结构，通过RecyclerView能显示不同布局的特性)
 * Created by caesar on 2016/11/29.
 * 修订历史:
 */
public class NestedListActivity extends AppCompatActivity {

    @Bind(R.id.rv_nested_list)
    RecyclerView mRvNestedList;
    @Bind(R.id.sv)
    NestedScrollView mSv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nested_list_activity);
        ButterKnife.bind(this);
        setView();
    }

    /**
     * 设置布局
     */
    private void setView() {
        mSv.smoothScrollTo(0, 0);
        mRvNestedList.setNestedScrollingEnabled(false);//使用NestScrollView替换ScrollView,实现滑动父布局嵌套列表
        final NestedListAdapter mAdapter = new NestedListAdapter(NestedListActivity.this);
        mRvNestedList.setLayoutManager(new LinearLayoutManager(NestedListActivity.this));
        mRvNestedList.setAdapter(mAdapter);
    }

}
