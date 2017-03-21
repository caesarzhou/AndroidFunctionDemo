package com.caesar.checkdemo;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.caesar.checkdemo.utils.CountDownTimerUtil;

/**
 * 弹出对话框popupWindow
 * Created by caesar on 2017/3/10.
 * 修订历史:
 */
public class PopupWindowActivity extends AppCompatActivity {
    private Button mButton;
    private PopupWindow popupWindow;
    private CountDownTimerUtil timeCount;//计时器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
        mButton = (Button) findViewById(R.id.bt);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPopupWindow();
                popupWindow.showAtLocation(v, Gravity.CENTER,0,0);
                //构造CountDownTimer对象 倒计时1分钟
                timeCount = new TimeCount(1000, 1000);
                timeCount.start();
            }
        });
    }


    /**
     * 创建PopupWindow
     */
    protected void initPopupWindow() {
        // TODO: 15/10/9
        //获取自定义布局文件activity_pop_left.xml 布局文件
        final View popupWindow_view = getLayoutInflater().inflate(R.layout.activity_pop_left, null, false);
        //创建popupWindow 实例，200，LayoutParams.MATCH_PARENT 分别是宽高
        popupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        //设置动画效果
        popupWindow.setAnimationStyle(R.style.AnimationFade);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
    }

    /**
     * 获取PopipWinsow实例
     */
    private void getPopupWindow() {
        if (null != popupWindow) {
            popupWindow.dismiss();
            return;
        } else {
            initPopupWindow();
        }
    }

    /**
     * 验证码倒计时功能
     */
    class TimeCount extends CountDownTimerUtil {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {// 计时完毕时触发
            if (!PopupWindowActivity.this.isFinishing()) {
                popupWindow.dismiss();
            } else {
                cancel();
            }
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            if (!PopupWindowActivity.this.isFinishing()) {

            } else {
                cancel();
            }
        }
    }
}
