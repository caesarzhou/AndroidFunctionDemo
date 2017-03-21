package com.caesar.checkdemo.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.caesar.checkdemo.R;

/**
 * 验多张券
 * Created by caesar on 2016/11/11.
 * 修订历史:
 */
public class CheckMostTicketDialog extends BaseDialog {
    private TextView mTvPlatform;//验券平台
    private TextView mTvProject;//套餐
    private TextView mTvMoney;//金额
    private TextView mTvPhone;//客户电话
    private TextView mTvStore;//服务门店
    private TextView mTvHairstylist;//服务发型师:店长姓名
    private TextView mTvTotalTicket;//该套餐总共券数
    private ImageView mIvReduce;//减
    private ImageView mIvAdd;//加
    private TextView mTvTicketNumber;//券数
    private TextView mTvConfirm;//确认
    private TextView mTvCancel;//取消
    private LinearLayout mllMoreTicket;

    //构造方法还是要的哈
    public CheckMostTicketDialog(Context context) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.check_most_ticket_dialog, null);
        //初始化数据
        initView(view);
        //初始化点击事件
        initClick();
        return view;
    }

    /** 初始化布局 */
    private void initView(View view) {
        mTvPlatform = (TextView) view.findViewById(R.id.tv_platform);
        mTvProject = (TextView) view.findViewById(R.id.tv_project);
        mTvMoney = (TextView) view.findViewById(R.id.tv_money);
        mTvPhone = (TextView) view.findViewById(R.id.tv_phone);
        mTvStore = (TextView) view.findViewById(R.id.tv_store);
        mTvHairstylist = (TextView) view.findViewById(R.id.tv_hairstylist);
        mTvTotalTicket = (TextView) view.findViewById(R.id.tv_total_ticket);
        mIvReduce = (ImageView) view.findViewById(R.id.iv_reduce);
        mIvAdd = (ImageView) view.findViewById(R.id.iv_add);
        mTvTicketNumber = (TextView) view.findViewById(R.id.tv_ticket_number);
        mTvConfirm = (TextView) view.findViewById(R.id.tv_confirm);
        mTvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        mllMoreTicket = (LinearLayout) view.findViewById(R.id.ll_more_ticket);
    }

    /** Dialog的点击事件 */
    private void initClick(){
        mIvReduce.setOnClickListener(new View.OnClickListener() {//减
            @Override
            public void onClick(View v) {
                mOnDialogClickListener1.onDialogClick();
            }
        });
        mIvAdd.setOnClickListener(new View.OnClickListener() {//加
            @Override
            public void onClick(View v) {
                mOnDialogClickListener2.onDialogClick();
            }
        });
        mTvConfirm.setOnClickListener(new View.OnClickListener() {//确认核销
            @Override
            public void onClick(View v) {
                mOnDialogClickListener3.onDialogClick();
            }
        });
        mTvCancel.setOnClickListener(new View.OnClickListener() {//取消核销
            @Override
            public void onClick(View v) {
                mOnDialogClickListener4.onDialogClick();
            }
        });
    }

    /** 是否为验多张券 */
    public void setIsMoreTicket() {
        mllMoreTicket.setVisibility(View.VISIBLE);
    }
    /** 验券平台*/
    public void setTvPlatform(String content) {
        mTvPlatform.setText(content);
    }
    /** 套餐 */
    public void setTvProject(String content) {
        mTvProject.setText(content);
    }
    /** 金额 */
    public void setTvMoney(String content) {
        mTvMoney.setText(content);
    }
    /** 客户电话 */
    public void setTvPhone(String content) {
        mTvPhone.setText(content);
    }
    /** 服务门店 */
    public void setTvStore(String content) {
        mTvStore.setText(content);
    }
    /** 发型师 */
    public void setTvHairstylist(String content) {
        mTvHairstylist.setText(content);
    }
    /** 总券数 */
    public void setTvTotalTicket(String content) {
        mTvTotalTicket.setText(content);
    }
    /** 选择券数 */
    public void setTvTicketNumber(String content) {
        mTvTicketNumber.setText(content);
    }
    /** 获取选择的券数 */
    public String getTvTicketNumber() {
        return mTvTicketNumber.getText().toString();
    }

    //接口回掉，实现Dialog点击事件
    public interface OnDialogClickListener {
        void onDialogClick();
    }
    //减
    private OnDialogClickListener mOnDialogClickListener1;
    public void setReduceClickListener(OnDialogClickListener mOnDialogClickListener) {
        this.mOnDialogClickListener1 = mOnDialogClickListener;
    }
    //加
    private OnDialogClickListener mOnDialogClickListener2;
    public void setAddClickListener(OnDialogClickListener mOnDialogClickListener) {
        this.mOnDialogClickListener2 = mOnDialogClickListener;
    }
    //确认核销
    private OnDialogClickListener mOnDialogClickListener3;
    public void setConfirmClickListener(OnDialogClickListener mOnDialogClickListener) {
        this.mOnDialogClickListener3 = mOnDialogClickListener;
    }
    //取消核销
    private OnDialogClickListener mOnDialogClickListener4;
    public void setCancleClickListener(OnDialogClickListener mOnDialogClickListener) {
        this.mOnDialogClickListener4 = mOnDialogClickListener;
    }

}
