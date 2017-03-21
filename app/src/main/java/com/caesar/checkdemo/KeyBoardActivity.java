package com.caesar.checkdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description:
 * Function:
 * Created by caesar on 2016/11/10.
 * 修订历史:
 */
public class KeyBoardActivity extends AppCompatActivity {
    @Bind(R.id.et_ticket_input)
    EditText mEtTicketInput;
    @Bind(R.id.iv_delete)
    ImageView mIvDelete;
    @Bind(R.id.bt1)
    Button mBt1;
    @Bind(R.id.bt2)
    Button mBt2;
    @Bind(R.id.bt3)
    Button mBt3;
    @Bind(R.id.bt4)
    Button mBt4;
    @Bind(R.id.bt5)
    Button mBt5;
    @Bind(R.id.bt6)
    Button mBt6;
    @Bind(R.id.bt7)
    Button mBt7;
    @Bind(R.id.bt8)
    Button mBt8;
    @Bind(R.id.bt9)
    Button mBt9;
    @Bind(R.id.bt0)
    Button mBt0;
    @Bind(R.id.bt_point)
    Button mBtPoint;
    @Bind(R.id.bt_hx)
    Button mBtHx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.keyboard_activity);
        ButterKnife.bind(KeyBoardActivity.this);
        hideKeyboard(this);//隐藏键盘
        initView();
        initData();
    }

    /**
     * 初始化布局
     */
    private void initView() {

    }

    /**
     * 初始化数据
     */
    private void initData() {
       initHxEditText();//初始化核销券号输入框
    }

    /** 初始化券号输入框 */
    private void initHxEditText() {
        //没有内容时,隐藏删除按钮
        if(mEtTicketInput.getText().toString().length() > 0) {
            mIvDelete.setVisibility(View.VISIBLE);
        } else {
            mIvDelete.setVisibility(View.GONE);
        }
        //设置hint文本尺寸
        setHintSize("请输入券号");
        //文本监视器
        mEtTicketInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //每隔4位加空格
                int length = s.length();
                if (((length - 4) % 5) == 0) {
                    mEtTicketInput.setText(s + " ");
                    mEtTicketInput.setSelection(mEtTicketInput.getText().toString().length());
                }
                //如果输入框没内容，隐藏删除按钮
                if (mEtTicketInput.getText().toString().length() > 0) {
                    mIvDelete.setVisibility(View.VISIBLE);
                } else {
                    mIvDelete.setVisibility(View.GONE);
                }
            }
        });
    }

    @OnClick({R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4, R.id.bt5, R.id.bt6,
            R.id.bt7, R.id.bt8, R.id.bt9, R.id.bt0, R.id.bt_point, R.id.bt_hx,R.id.iv_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_hx://核销
                String str = mEtTicketInput.getText().toString().replace(" ","");
                Toast.makeText(KeyBoardActivity.this, str, Toast.LENGTH_SHORT).show();
                if(str.equals("123")) {
                    Log.i("test", str);
                    mEtTicketInput.setText("");
                    setHintSize("请输入手机号");
                } else {
                    Log.i("test",str);
                }
                break;
            case R.id.iv_delete://删除
                cutTicketContent();
                break;
            case R.id.bt1:
                setTicketContent("1");
                break;
            case R.id.bt2:
                setTicketContent("2");
                break;
            case R.id.bt3:
                setTicketContent("3");
                break;
            case R.id.bt4:
                setTicketContent("4");
                break;
            case R.id.bt5:
                setTicketContent("5");
                break;
            case R.id.bt6:
                setTicketContent("6");
                break;
            case R.id.bt7:
                setTicketContent("7");
                break;
            case R.id.bt8:
                setTicketContent("8");
                break;
            case R.id.bt9:
                setTicketContent("9");
                break;
            case R.id.bt0:
                setTicketContent("0");
                break;
            case R.id.bt_point:
                setTicketContent(".");
                break;
        }
    }

    /**
     * 设置券号文本
     */
    private void setTicketContent(String content) {
        String str = mEtTicketInput.getText().toString();
        str += content;
        mEtTicketInput.setText(str);
        mEtTicketInput.setSelection(str.length());
    }

    /** 截掉券号字符串的最后一位 */
    private void cutTicketContent() {
        String str = mEtTicketInput.getText().toString().trim();
        if(str.length() > 0) {
            String newStr = str.substring(0,str.length() - 1);
            mEtTicketInput.setText(newStr);
            mEtTicketInput.setSelection(newStr.length());
        }
    }

    /** 设置EditText的hint文本的尺寸 */
    private void setHintSize(String content) {
        //设置hint字体的尺寸
        SpannableString ss = new SpannableString(content);
        // 新建一个属性对象,设置文字的大小
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(25,true);
        // 附加属性到文本
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 设置hint
        mEtTicketInput.setHint(new SpannedString(ss)); // 一定要进行转换,否则属性会消失
    }

    /**
     * 隐藏软键盘
     *
     * @param activity
     */
    private void hideKeyboard(Activity activity) {
        if (activity != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive()) {
                imm.hideSoftInputFromWindow(mEtTicketInput.getWindowToken(), 0);
            }
        }
    }
}
