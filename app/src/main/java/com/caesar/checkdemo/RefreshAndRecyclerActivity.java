package com.caesar.checkdemo;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.caesar.checkdemo.adapter.BaseRecyclerAdapter;
import com.caesar.checkdemo.adapter.CommonHolder;
import com.caesar.checkdemo.beans.Card;
import com.caesar.checkdemo.ui.FullyLinearLayoutManager;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.bezierlayout.BezierLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * recyclerView和下拉刷新、加载更多
 * Created by caesar on 2016/12/5.
 * 修订历史:
 */
public class RefreshAndRecyclerActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private CardAdapter cardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_scroll_activity);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_refresh);
        initData();
    }

    /** 初始化数据*/
    private void initData() {
        mRecyclerView.setLayoutManager(new FullyLinearLayoutManager(this));
        cardAdapter = new CardAdapter();
        mRecyclerView.setAdapter(cardAdapter);
        //下拉刷新加载
        TwinklingRefreshLayout refreshLayout = (TwinklingRefreshLayout) findViewById(R.id.refresh);
        BezierLayout headerView = new BezierLayout(this);
        assert refreshLayout != null;
        refreshLayout.setHeaderView(headerView);
        refreshLayout.setWaveHeight(200);
        refreshLayout.setHeaderHeight(150);
        refreshCard();//填充数据
        //添加头布局
        HeaderHolder holder = new HeaderHolder(RefreshAndRecyclerActivity.this,mRecyclerView,"我爱你");
        cardAdapter.setHeadHolder(holder);
        //下拉刷新和加载更多
        refreshLayout.setOnRefreshListener(new TwinklingRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshCard();
                        refreshLayout.finishRefreshing();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadMoreCard();
                        refreshLayout.finishLoadmore();
                    }
                }, 2000);
            }
        });
    }

    void refreshCard(){
        List<Card> cards = new ArrayList<>();
        cards.add(new Card("God of Light", "点亮世界之光",R.drawable.card_cover1));
        cards.add(new Card("我的手机与众不同", "专题",R.drawable.card_cover2));
        cards.add(new Card("BlackLight", "做最纯粹的微博客户端", R.drawable.card_cover3));
        cards.add(new Card("BuzzFeed", "最好玩的新闻在这里", R.drawable.card_cover4));
        cards.add(new Card("Nester", "专治各种熊孩子",R.drawable.card_cover5));
        cardAdapter.setDataList(cards);
    }

    void loadMoreCard(){
        List<Card> cards = new ArrayList<>();
        cards.add(new Card("二次元专题", "啊喂，别总想去四维空间啦",R.drawable.card_cover6));
        cards.add(new Card("Music Player", "闻其名，余音绕梁", R.drawable.card_cover7));
        cards.add(new Card("el", "剪纸人の唯美旅程",R.drawable.card_cover8));
        cards.add(new Card("God of Light", "点亮世界之光",R.drawable.card_cover1));
        cards.add(new Card("BlackLight", "做最纯粹的微博客户端",R.drawable.card_cover3));
        cardAdapter.addItems(cards);
    }



    class HeaderHolder extends CommonHolder<Void> {
        @Bind(R.id.tv4)
        TextView tv_title;
        private String s;
        public HeaderHolder(Context context, ViewGroup root, String s) {
            super(context, root, R.layout.list_item);
            this.s = s;
        }

        @Override
        public void bindData(Void aVoid) {

        }

        @Override
        public void bindHeadData() {
            tv_title.setText(s);
        }
    }

    public class CardAdapter extends BaseRecyclerAdapter<Card> {
        @Override
        public CommonHolder<Card> setViewHolder(ViewGroup parent) {
            return new CardHolder(parent.getContext(), parent);
        }

        class CardHolder extends CommonHolder<Card> {

            @Bind(R.id.tv_title)
            TextView tv_title;

            @Bind(R.id.tv_subtitle)
            TextView tv_subtitle;

            @Bind(R.id.iv_cover)
            ImageView iv_cover;

            public CardHolder(Context context, ViewGroup root) {
                super(context, root, R.layout.list_item1);
            }

            @Override
            public void bindData(Card card) {
                tv_title.setText(card.title);
                tv_subtitle.setText(card.info);
                iv_cover.setImageResource(card.imageSrc);
            }
        }
    }





























  /*  public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private Context mContext;

        public MyAdapter(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            // 给ViewHolder设置布局文件
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_selection_item, viewGroup, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
            viewHolder.mTextView.setText("万达"+i);
        }

        @Override
        public int getItemCount() {
            // 返回数据总数
            return 60;
        }

        // 重写的自定义ViewHolder
        public static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;

            public ViewHolder(View v) {
                super(v);
                mTextView = (TextView) v.findViewById(R.id.tv);
            }
        }
    }*/
}
