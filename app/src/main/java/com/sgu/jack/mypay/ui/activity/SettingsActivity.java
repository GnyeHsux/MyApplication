package com.sgu.jack.mypay.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.sgu.jack.mypay.R;
import com.sgu.jack.mypay.base.BaseActivity;
import com.sgu.jack.mypay.ui.fragment.SettingsFramgent;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xusha on 2016/8/25.
 */
public class SettingsActivity extends BaseActivity{
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        setToolBar(mToolbar,"设置");

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.containar, new SettingsFramgent())
                .commit();


    }
}
