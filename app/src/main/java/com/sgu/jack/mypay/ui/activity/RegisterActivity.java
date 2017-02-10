package com.sgu.jack.mypay.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.sgu.jack.mypay.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：xushane on 2016/9/23
 * 邮箱：xushaneone@gmail.com
 */
public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.tv_has_account)
    TextView mTvHasAccount;
    @BindView(R.id.login_view)
    TextView mLoginView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        mTvHasAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
