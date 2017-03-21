package com.caesar.checkdemo.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.caesar.checkdemo.R;

/**
 * 老顾客
 * Created by caesar on 2016/11/11.
 * 修订历史:
 */
public class RegularClientDialog extends BaseDialog {
    private TextView mTvConfirm;//验券平台
    private TextView mTvComeNumber;//来访次数
    private TextView mTvRecord;//消费记录

    //构造方法还是要的哈
    public RegularClientDialog(Context context) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.regular_client_dialog, null);
        mTvConfirm = (TextView) view.findViewById(R.id.tv_confirm);
        mTvComeNumber = (TextView) view.findViewById(R.id.tv_come_number);
        mTvRecord = (TextView) view.findViewById(R.id.tv_check_record);
        mTvRecord.setOnClickListener(new View.OnClickListener() {//查看消费记录
            @Override
            public void onClick(View v) {
                mOnDialogClickListener.onDialogClick();
            }
        });
        mTvConfirm.setOnClickListener(new View.OnClickListener() {//我知道了
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }

    /** 设置来访次数 */
    public void setComeNumber(String content) {
        mTvComeNumber.setText(content);
    }

    //接口回掉，实现Dialog点击事件
    public interface OnDialogClickListener {
        void onDialogClick();
    }
    private OnDialogClickListener mOnDialogClickListener;
    public void setRecordClickListener(OnDialogClickListener mOnDialogClickListener) {
        this.mOnDialogClickListener = mOnDialogClickListener;
    }
}
