package com.caesar.checkdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.caesar.checkdemo.adapter.AddViewAdapter;
import com.caesar.checkdemo.ui.RegularClientDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.bt_single)
    Button mBtSingle;
    @Bind(R.id.bt_more)
    Button mBtMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_single, R.id.bt_more, R.id.bt_more2,R.id.ll_add_view, R.id.bt_keyboard,R.id.bt_dialog,R.id.bt_calendar,
            R.id.bt_large_photo,R.id.bt_select_photo,R.id.bt_nested_list,R.id.bt_recycler_scroll,R.id.bt_handler,
            R.id.bt_pop
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_pop://弹出对话框页面
                startActivity(new Intent(MainActivity.this,PopupWindowActivity.class));
                break;

            case R.id.bt_single://单选列表
                startActivity(new Intent(MainActivity.this, SingleSelectionActivity.class));
                break;
            case R.id.bt_more://多选列表
                startActivity(new Intent(MainActivity.this, MultipleSelectionActivity.class));
                break;

            case R.id.bt_more2://多选列表加强版
                startActivity(new Intent(MainActivity.this, MultipleSelectionIntensifyActivity.class));
                break;

            case R.id.bt_keyboard://自定义输入键盘
                startActivity(new Intent(MainActivity.this, KeyBoardActivity.class));
                break;

            case R.id.bt_calendar://日历
                startActivity(new Intent(MainActivity.this, CalendarActivity.class));
                break;

            case R.id.bt_large_photo://大图预览
                startActivity(new Intent(MainActivity.this, LargePhotoViewActivity.class));
                break;

            case R.id.bt_select_photo://相册图片选择
                startActivity(new Intent(MainActivity.this, PhotoSelectActivity.class));
                break;

            case R.id.bt_recycler_scroll://TwinklingRefreshLayout实现下拉刷新
                startActivity(new Intent(MainActivity.this, RefreshAndRecyclerActivity.class));
                break;

            case R.id.bt_nested_list://列表嵌套解决方案(重构服务器数据结构，通过RecyclerView能显示不同布局的特性)
                startActivity(new Intent(MainActivity.this, NestedListActivity.class));
                break;

            case R.id.bt_dialog://自定义对话框
                RegularClientDialog dialog = new RegularClientDialog(MainActivity.this);
                dialog.setCancelable(false).show();
                break;

            case R.id.ll_add_view://动态添加布局
                Toast.makeText(MainActivity.this, "添加布局", Toast.LENGTH_SHORT).show();
                for (int i = 0; i < 4; i++) {
                    LinearLayout ll = (LinearLayout) findViewById(R.id.ll_add_view);
                    assert ll != null;
                    View view1 = LayoutInflater.from(this).inflate(R.layout.add_view_item, ll, false);
                    TextView tv1 = (TextView) view1.findViewById(R.id.tv1);
                    tv1.setText("柯宁");
                    TextView tv2 = (TextView) view1.findViewById(R.id.tv2);
                    tv2.setText("西瓜");
                    //列表
                    RecyclerView rv = (RecyclerView) view1.findViewById(R.id.rv);
                    ViewGroup.LayoutParams params = rv.getLayoutParams();
                    params.height = 25 * 100;
                    rv.setLayoutParams(params);
                    rv.setNestedScrollingEnabled(false);
                    rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    AddViewAdapter mAdapter = new AddViewAdapter(MainActivity.this);
                    mAdapter.setOnItemClickLitener(new AddViewAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Toast.makeText(MainActivity.this, "条目" + position, Toast.LENGTH_SHORT).show();
                        }
                    });
                    rv.setAdapter(mAdapter);
                    ll.addView(view1);
                }
                break;
            case R.id.bt_handler://handler使用详解
                startActivity(new Intent(MainActivity.this, HandlerDemoActivity.class));
                break;
        }
    }


}
