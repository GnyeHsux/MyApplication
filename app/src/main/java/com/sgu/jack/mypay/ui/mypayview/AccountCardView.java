package com.sgu.jack.mypay.ui.mypayview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sgu.jack.mypay.R;

/**
 * 作者：xushane on 2016/9/21
 * 邮箱：xushaneone@gmail.com
 */
public class AccountCardView extends RelativeLayout implements View.OnClickListener {
    private TextView mTvAN, mTvAB, mTvTS;   //不变的标题，账户名称，账户余额，今日消费
    private TextView mTvAccountDesc, mTvAccountName, mTvAccountBalance, mTvTodaySpend;   //显示信息：账户描述，账户名称+备注，账户余额，今日消费
    private TextView mTvTransaction, mTvModifyAccount, mTvTransfer;
    private ImageView mImgAccount;

    private RelativeLayout mRelativeLayout;

    // 命名空间，在引用这个自定义组件的时候，需要用到
    private String namespace = "http://schemas.android.com/apk/res/com.sgu.jack.mypay.ui.mypayview/AccountCardView";

    private Drawable mAccountImgSrc;   //账户图片资源
    private float mAccountImgSize;  //账户图片大小
    private String mAccountDescText;    //账户描述文字
    private String mAccountNameText;    //账户名称文字
    private String mAccountBalanceText; //账户余额文字
    private String mTodaySpendText;  //今日消费文字
    private int mTextColor; //字体颜色
    private float mTextSize;    //字体大小
    private int mBackgroundColor;

    private BottomTextClickListener mListener;  //底部Text的监听器

    private LayoutParams mImgAccountParams, mTvAccountDescParams, mTvANParams, mTvAccountNameParams,
            mTvTransactionParams, mTvModifyAccountParams, mTvTransferParams;

    private final int mImageSize = 200;


    public AccountCardView(Context context) {
        super(context);
    }

    public AccountCardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView(context);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.AccountCard);

        mAccountDescText = ta.getString(R.styleable.AccountCard_accountDescText);
        mAccountNameText = ta.getString(R.styleable.AccountCard_accountNameText);
        mAccountBalanceText = ta.getString(R.styleable.AccountCard_accountBalanceText);
        mTodaySpendText = ta.getString(R.styleable.AccountCard_todaySpendText);
        mAccountImgSrc = ta.getDrawable(R.styleable.AccountCard_accountImg_src);
        mBackgroundColor = ta.getColor(R.styleable.AccountCard_backgroundColor, 0);

        if (mAccountImgSrc != null) {
            mImgAccount.setImageDrawable(mAccountImgSrc);
        }
        if (mAccountDescText != null) {
            mTvAccountDesc.setText(mAccountDescText);
        }
        if (mAccountNameText != null) {
            mTvAccountName.setText(mAccountNameText);
        }
        if (mAccountBalanceText != null) {
            mTvAccountBalance.setText(mAccountBalanceText);
        }
        if (mTodaySpendText != null) {
            mTvTodaySpend.setText(mTodaySpendText);
        }
        if (mBackgroundColor != 0) {
            mRelativeLayout.setBackgroundColor(mBackgroundColor);
        }


        ta.recycle();
    }

    private void initView(Context context) {

        View view = LayoutInflater.from(context).inflate(R.layout.my_pay_account_card, this, true);
        mRelativeLayout = (RelativeLayout) view.findViewById(R.id.rl);
        mImgAccount = (ImageView) view.findViewById(R.id.img_account);
        mTvAccountDesc = (TextView) view.findViewById(R.id.tv_account_desc);
        mTvAccountName = (TextView) view.findViewById(R.id.tv_account_name);
        mTvAccountBalance = (TextView) view.findViewById(R.id.tv_account_balance);
        mTvTodaySpend = (TextView) view.findViewById(R.id.tv_today_spend);
        mTvTransaction = (TextView) view.findViewById(R.id.tv_transaction);
        mTvModifyAccount = (TextView) view.findViewById(R.id.tv_modify);
        mTvTransfer = (TextView) view.findViewById(R.id.tv_transfer);

        mTvTransaction.setOnClickListener(this);
        mTvModifyAccount.setOnClickListener(this);
        mTvTransfer.setOnClickListener(this);
    }

    public void setAccountImg(int imgResourceId) {
        mImgAccount.setImageResource(imgResourceId);
    }

    public void setAccountDesc(String desc) {
        mTvAccountDesc.setText(desc);
    }

    public void setAccountName(String name, String remark) {
        mTvAccountName.setText(name + " (" + remark + ")");
    }

    public void setAccountBalance(String balance) {
        mTvAccountBalance.setText("￥ " + balance);
    }

    public void setTodaySpend(String spend) {
        mTvTodaySpend.setText("￥ " + spend);
    }

    public void setBackgroundColor(int color) {
        mRelativeLayout.setBackgroundColor(color);
    }


    public void setOnBottomTextClickListener(BottomTextClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_transaction:
                Log.i("myview", "transaction");
                mListener.onTransactionClick();
                break;
            case R.id.tv_modify:
                Log.i("myview", "modify");
                mListener.onModifyAccountClick();
                break;
            case R.id.tv_transfer:
                Log.i("myview", "transfer");
                mListener.onTransferClick();
                break;
        }
    }

    //底部3个Text的点击监听器
    public interface BottomTextClickListener {
        void onTransactionClick();  //交易流水点击事件

        void onModifyAccountClick();    //修改账户点击事件

        void onTransferClick();     //转账点击事件
    }
}
