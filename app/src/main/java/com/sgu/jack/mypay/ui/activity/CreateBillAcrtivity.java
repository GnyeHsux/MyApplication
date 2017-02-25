package com.sgu.jack.mypay.ui.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sgu.jack.mypay.R;
import com.sgu.jack.mypay.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by azd on 2017/2/16.
 */

public class CreateBillAcrtivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bill);
        ButterKnife.bind(this);
    }
}
