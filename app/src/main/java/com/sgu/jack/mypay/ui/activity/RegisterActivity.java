package com.sgu.jack.mypay.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.utils.SnackbarUtils;
import com.sgu.jack.mypay.R;
import com.sgu.jack.mypay.utils.StringUtil;
import com.sgu.jack.mypay.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：xushane on 2016/9/23
 * 邮箱：xushaneone@gmail.com
 */
public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.tv_has_account)
    TextView mTvHasAccount;
    @BindView(R.id.Register_view)
    TextView mRegisterBtn;
    @BindView(R.id.user_name)
    EditText mTvUserName;
    @BindView(R.id.mobile)
    EditText mTvMobile;
    @BindView(R.id.password1)
    EditText mTvPassword1;
    @BindView(R.id.password2)
    EditText mTvPassword2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        //已有账号点击关闭当前页
        mTvHasAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //注册按钮点击事件
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = mTvUserName.getText().toString().trim();
                String mobile = mTvMobile.getText().toString().trim();
                String pwd1 = mTvPassword1.getText().toString().trim();
                String pwd2 = mTvPassword2.getText().toString().trim();
                if (StringUtil.isEmpty(userName)) {
                    SnackbarUtils.showShortSnackbar(view, "用户名不能为空", Color.WHITE, Color.BLUE);
                    ToastUtils.makeShortText("用户名不能为空", view.getContext());
                }
                if (StringUtil.isEmpty(mobile)) {
                    Snackbar.make(view,"手机号不能为空",Snackbar.LENGTH_SHORT);
                    ToastUtils.makeShortText("手机号不能为空", view.getContext());
                }
                if (StringUtil.isEmpty(pwd1)) {
                    ToastUtils.makeShortText("密码不能为空", view.getContext());
                }
                if (StringUtil.isEmpty(pwd2)) {
                    ToastUtils.makeShortText("验证密码不能为空", view.getContext());
                }

            }
        });
    }
}
