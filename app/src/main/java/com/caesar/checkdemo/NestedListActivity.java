package com.caesar.checkdemo;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.caesar.checkdemo.adapter.ALog;
import com.caesar.checkdemo.adapter.NestedListAdapter1;
import com.caesar.checkdemo.model.ProductionManageInfo;
import com.caesar.checkdemo.ui.FullyLinearLayoutManager;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表嵌套解决方案(重构服务器数据结构，通过RecyclerView能显示不同布局的特性)
 * Created by caesar on 2016/11/29.
 * 修订历史:
 */
public class NestedListActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    //private ListenedScrollView mScrollView;
    private RecyclerView mRecyclerView;
    //private TextView mButton;
    private SwipeRefreshLayout srl;//下拉刷新控件
    private List<ProductionManageInfo.DataEntity> mData;//网络请求的数据
    private List<ProductionManageInfo.DataEntity> mAllData = new ArrayList<>();//将分页的所有数据放到该集合里面
    private NestedListAdapter1 adapter1;
    private FullyLinearLayoutManager mLayoutManager;//自定义布局管理器，解决RecyclerView的隐藏Bug
    private int page = 1;//分页
    private int pages = 1;//总页数
    private boolean isRefresh = false;//是否刷新中

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nested_list_activity);
        //mScrollView = (ListenedScrollView) findViewById(R.id.sv);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_nested_list);
        //mButton = (TextView) findViewById(R.id.tv_hide_footer);
        srl = (SwipeRefreshLayout) findViewById(R.id.srl);
        mRecyclerView.setNestedScrollingEnabled(false);
        getPageInfo(page);//获取页面信息
        initEvent();//初始化控件事件
    }

    /**
     * 获取页面信息
     */
    private void getPageInfo(final int page) {
        String url = "http://www.meiyeplus.cn/index.php?controller=app_circle&action=photo_list";
        RequestParams params = new RequestParams();
        params.addBodyParameter("hid", "204787");
        params.addBodyParameter("page", page + "");
        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.POST, url, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                Gson gson = new Gson();
                ProductionManageInfo info = gson.fromJson(result, ProductionManageInfo.class);
                if (info.getStatus().equals("1")) {
                    mData = info.getData();
                    pages = Integer.parseInt(info.getPages());
                    if (page <= pages) {
                        setView(page);
                    } else {
                        adapter1.ReMoveFooterView();
                        adapter1.notifyDataSetChanged();
                        Toast.makeText(NestedListActivity.this, "就这么多了", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                ALog.i("请求失败");
            }
        });
    }

    /**
     * 设置布局
     */
    private void setView(int page1) {
        if (page1 == 1) {//第一次进入页面
            mAllData.addAll(mData);
            adapter1 = new NestedListAdapter1(NestedListActivity.this, mAllData);
            //布局管理器
            mLayoutManager = new FullyLinearLayoutManager(NestedListActivity.this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            View header = LayoutInflater.from(NestedListActivity.this).inflate(R.layout.header_view,mRecyclerView, false);
            adapter1.setHeaderView(header);
            mRecyclerView.setAdapter(adapter1);
        } else if (page1 == 0) {//页面刷新
            ALog.i("刷新完成");
            mAllData.clear();
            mAllData.addAll(mData);
            adapter1.notifyDataSetChanged();
            //显示或隐藏刷新进度条
            srl.setRefreshing(false);
            isRefresh = false;
        } else {//分页的时候不用重新设置适配器了
            mAllData.addAll(mData);
            adapter1.ReMoveFooterView();
            adapter1.notifyDataSetChanged();
        }
    }
    /**
     * 初始化控件事件
     */
    private void initEvent() {

       /* mScrollView.setOnScrollListener(new ListenedScrollView.OnScrollListener() {
            @Override
            public void onBottomArrived() {
                //滑倒底部了
                ALog.i("滑动到底部了");
                adapter1.setFooterView(LayoutInflater.from(NestedListActivity.this).inflate(R.layout.footer_view, mRecyclerView, false));
                page += 1;
                getPageInfo(page);
            }

            @Override
            public void onScrollStateChanged(ListenedScrollView view, int scrollState) {
                //滑动状态改变
            }

            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                //滑动位置改变
            }
        });*/

        //滑动监听
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            boolean isShowFooter = false;
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                ALog.i("滑动中");
                if (!mRecyclerView.canScrollVertically(1)) {
                    if (!isShowFooter) {
                        adapter1.ReMoveHeaderView();
                        adapter1.setFooterView(LayoutInflater.from(NestedListActivity.this).inflate(R.layout.footer_view, mRecyclerView, false));
                        page += 1;
                        getPageInfo(page);
                    }
                    isShowFooter = true;
                } else if(!mRecyclerView.canScrollVertically(-1)) {
                    View header = LayoutInflater.from(NestedListActivity.this).inflate(R.layout.header_view,mRecyclerView, false);
                    adapter1.setHeaderView(header);
                } else {
                    isShowFooter = false;
                }
            }
        });

        //下拉刷新
        //设置下拉刷新的监听
        srl.setOnRefreshListener(NestedListActivity.this);
    }

    /**
     * 是否显示脚布局
     */
    public static boolean isVisBottom(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        //屏幕中最后一个可见子项的position
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
        //当前屏幕所看到的子项个数
        int visibleItemCount = layoutManager.getChildCount();
        //当前RecyclerView的所有子项个数
        int totalItemCount = layoutManager.getItemCount();
        //RecyclerView的滑动状态
        int state = recyclerView.getScrollState();

        if (visibleItemCount > 0 && lastVisibleItemPosition == totalItemCount - 1 && state == recyclerView.SCROLL_STATE_IDLE) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onRefresh() {
        ALog.i("准备刷新");
        //检查是否处于刷新状态
        if (!isRefresh) {
            ALog.i("刷新中");
            isRefresh = true;
            getPageInfo(0);
        }
    }
}
