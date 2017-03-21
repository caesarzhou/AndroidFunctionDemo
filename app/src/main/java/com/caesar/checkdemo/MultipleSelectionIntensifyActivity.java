package com.caesar.checkdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.caesar.checkdemo.adapter.MultipleSelectionIntensifyAdapter;

/**
 * 多张图片选择
 * Created by caesar on 2016/12/8.
 * 修订历史:
 */
public class MultipleSelectionIntensifyActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private TextView tvSelectNumber;
    private MultipleSelectionIntensifyAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiple_selection_intensify_activity);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_multiple_intensify);
        tvSelectNumber = (TextView) findViewById(R.id.tv_select_number);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MultipleSelectionIntensifyAdapter(this);
        mAdapter.setOnItemClickListener(new MultipleSelectionIntensifyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (MultipleSelectionIntensifyAdapter.selectMap.get(position)) {
                    MultipleSelectionIntensifyAdapter.selectMap.put(position, false);
                } else {
                    MultipleSelectionIntensifyAdapter.selectMap.put(position, true);
                }
                CheckBox mCheckBox = (CheckBox) view.findViewById(R.id.cb_multiple);
                mCheckBox.toggle();//改变选中状态(和setChecked有区别，setChecked改变状态会在滑动的时候)
            }
        });
        //设置适配器
        mRecyclerView.setAdapter(mAdapter);

        //选中个数
        tvSelectNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number =0;
                for(int i =0;i < MultipleSelectionIntensifyAdapter.selectMap.size();i++) {
                    if(MultipleSelectionIntensifyAdapter.selectMap.get(i)) {
                        number +=1;
                    }
                }
                Toast.makeText(MultipleSelectionIntensifyActivity.this,"选中了"+number+"个",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
