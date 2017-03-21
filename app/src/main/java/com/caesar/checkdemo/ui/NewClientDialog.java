package com.caesar.checkdemo.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.caesar.checkdemo.R;

/**
 * 新顾客提示框
 * Created by caesar on 2016/11/11.
 * 修订历史:
 */
public class NewClientDialog extends BaseDialog {
    private TextView mTvConfirm;//验券平台
    private TextView mStoreName;//门店名

    //构造方法还是要的哈
    public NewClientDialog(Context context) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.new_client_dialog, null);
        mTvConfirm = (TextView) view.findViewById(R.id.tv_confirm);
        mStoreName = (TextView) view.findViewById(R.id.tv_store_name);
        mTvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }

    /** 设置门店名称 */
    public void setStoreName(String content) {
        mStoreName.setText(content);
    }
}
