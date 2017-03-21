package com.caesar.checkdemo.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.caesar.checkdemo.R;

/**
 * 验券初始提示框
 * Created by caesar on 2016/11/11.
 * 修订历史:
 */
public class CheckAlertDialog extends BaseDialog {
    private TextView mTvConfirm;

    //构造方法还是要的哈
    public CheckAlertDialog(Context context) {
        super(context);
    }

    //设置对话框的样式
    @Override
    protected int getDialogStyleId() {
        return R.style.common_dialog_style;
    }

    //继承于BaseDialog的方法，设置布局用的，这样对话框张啥样久随心所欲啦
    @Override
    protected View getView() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(R.layout.check_alert_dialog, null);
        mTvConfirm = (TextView) view.findViewById(R.id.tv_confirm);
        mTvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }
}

