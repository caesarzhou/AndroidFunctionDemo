package com.caesar.checkdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.caesar.checkdemo.adapter.SingleSelectAdapter;
import com.caesar.checkdemo.ui.DividerGridItemDecoration;

/**
 * 单选列表
 * Created by caesar on 2016/9/29.
 * 修订历史:
 */
public class SingleSelectionActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private TextView mTextView2;
    private SingleSelectAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_activity);
        initView();
        initData();
    }

    /** 初始化布局*/
    private void initView() {
        mTextView2 = (TextView) findViewById(R.id.tv2);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mTextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextView2.setText("选中了"+SingleSelectAdapter.selectPosition);
            }
        });
    }

    /** 初始化数据 */
    private void initData() {
        //列表
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        //适配器
        mAdapter = new SingleSelectAdapter(SingleSelectionActivity.this);
        mAdapter.setOnItemClickLitener(new SingleSelectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                SingleSelectAdapter.selectPosition = position;
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
    }
}
