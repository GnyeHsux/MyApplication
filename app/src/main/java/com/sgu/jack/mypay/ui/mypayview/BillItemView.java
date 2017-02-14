package com.sgu.jack.mypay.ui.mypayview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
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
public class BillItemView extends RelativeLayout {
    private TextView mTvName, mTvRemark, mTvCount, mTvSource, mTvDate;   //消费命，消费备注，消费额，消费源
    private ImageView mImg;

    // 命名空间，在引用这个自定义组件的时候，需要用到
    private String namespace = "http://schemas.android.com/apk/res/com.sgu.jack.mypay.ui.mypayview/BillItemView";

//    private Drawable mImgSrc;   //账单图片资源
//    private String mNameText;    //账户描述文字
//    private String mRemarkText;    //账户名称文字
//    private String mCountText; //账户余额文字
//    private String mSourceText;  //今日消费文字

    private final int mImageSize = 200;


    public BillItemView(Context context) {
        super(context);
    }

    public BillItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView(context);

    }

    private void initView(Context context) {

        View view = LayoutInflater.from(context).inflate(R.layout.my_pay_bill_item, this, true);
        mImg = (ImageView) view.findViewById(R.id.bill_pic);
        mTvName = (TextView) view.findViewById(R.id.bill_name);
        mTvRemark = (TextView) view.findViewById(R.id.bill_remark);
        mTvCount = (TextView) view.findViewById(R.id.bill_count);
        mTvSource = (TextView) view.findViewById(R.id.bill_source);
        mTvDate = (TextView) view.findViewById(R.id.bill_date);

        this.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Snackbar.make(view,"test",Snackbar.LENGTH_SHORT);
                return false;
            }
        });
    }

    public void setImg(int imgResourceId) {
        mImg.setImageResource(imgResourceId);
    }

    public void setName(String name) {
        mTvName.setText(name);
    }

    public void setRemark(String remark) {
        mTvRemark.setVisibility(VISIBLE);
        mTvRemark.setText(remark);
    }

    public void setCount(String count) {
        mTvCount.setText("￥ " + count);
    }

    public void setSource(String source) {
        mTvSource.setVisibility(VISIBLE);
        mTvSource.setText(source);
    }

    public void setDate(String date){
        mTvDate.setVisibility(VISIBLE);
        mTvDate.setText(date);
    }

}
