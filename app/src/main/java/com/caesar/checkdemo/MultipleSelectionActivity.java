package com.caesar.checkdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.caesar.checkdemo.adapter.MultipleSelectAdapter;

/**
 * 多选列表
 * Created by caesar on 2016/9/28.
 * 修订历史:
 */
public class MultipleSelectionActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private TextView mTextView2;
    private MultipleSelectAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiple_activity);
        initView();
        initData();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        mTextView2 = (TextView) findViewById(R.id.tv2);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);

        mTextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String all = "";
                for (int i = 0; i < MultipleSelectAdapter.selectMap.size(); i++) {
                    if (MultipleSelectAdapter.selectMap.get(i)) {
                        String str = i + "被选中;";
                        all += str;
                    }
                }
                mTextView2.setText(all);
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        //列表
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //适配器
        mAdapter = new MultipleSelectAdapter(MultipleSelectionActivity.this);
        //自定义条目点击事件
        mAdapter.setOnItemClickLitener(new MultipleSelectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (MultipleSelectAdapter.selectMap.get(position)) {
                    MultipleSelectAdapter.selectMap.put(position, false);
                } else {
                    MultipleSelectAdapter.selectMap.put(position, true);
                }
                CheckBox mCheckBox = (CheckBox) view.findViewById(R.id.cb_single);
                mCheckBox.toggle();//改变选中状态(和setChecked有区别，setChecked改变状态会在滑动的时候)
            }
        });
        //设置适配器
        mRecyclerView.setAdapter(mAdapter);
    }

}
