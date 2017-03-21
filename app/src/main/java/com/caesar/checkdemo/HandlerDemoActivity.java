package com.caesar.checkdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


/**
 * handler使用详解
 * Created by caesar on 2017/2/16.
 * 修订历史:
 */
public class HandlerDemoActivity extends AppCompatActivity {
    private Handler mHandler;
    private TextView mTextView;
    private static final int MSG_UPDATE_TEXT = 0x2001; // 更新文本  方式一用的常量
    private static final int MSG_UPDATE_WAY_TWO = 0x2002;  // 更新文本 方式二用的常量
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler_demo_activity);
        mTextView = (TextView) findViewById(R.id.tv_handler);
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MSG_UPDATE_TEXT:
                        mTextView.setText("已经更改文本内容");
                        break;
                    default:
                        break;
                }
            }
        };
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = Message.obtain();
                msg.what = MSG_UPDATE_TEXT;
                mHandler.sendMessage(msg);
            }
        });
    }
}
