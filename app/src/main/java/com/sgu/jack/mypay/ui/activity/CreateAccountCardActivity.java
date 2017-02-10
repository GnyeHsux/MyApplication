package com.sgu.jack.mypay.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sgu.jack.mypay.R;
import com.sgu.jack.mypay.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：xushane on 2016/9/23
 * 邮箱：xushaneone@gmail.com
 */
public class CreateAccountCardActivity extends BaseActivity implements View.OnClickListener {

    private final static String IS_INDEX = "is_index";
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.ll_edit_account_name)
    LinearLayout mLlEditAccountName;
    @BindView(R.id.ll_choose_account_type)
    LinearLayout mLlChooseAccountType;
    @BindView(R.id.ll_edit_account_balance)
    LinearLayout mLlEditAccountBalance;
    @BindView(R.id.ll_edit_account_remark)
    LinearLayout mLlEditAccountRemark;
    @BindView(R.id.tv_account_name)
    TextView mTvAccountName;
    @BindView(R.id.tv_account_type)
    TextView mTvAccountType;
    @BindView(R.id.tv_account_balance)
    TextView mTvAccountBalance;
    @BindView(R.id.tv_account_remark)
    TextView mTvAccountRemark;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_card);
        ButterKnife.bind(this);
        setToolBar(mToolbar, "创建账户");
        //String is_index = getIntent().getExtras().getString(IS_INDEX);

        initClickListener();

    }

    private void initClickListener() {
        mLlChooseAccountType.setOnClickListener(this);
        mLlEditAccountBalance.setOnClickListener(this);
        mLlEditAccountName.setOnClickListener(this);
        mLlEditAccountRemark.setOnClickListener(this);
    }

    /**
     * 创建intent
     * is_index 是否是首页跳转过来的
     */
    public static Intent newIndexIntent(Context context, String message) {
        Intent newIntent = new Intent(context, CreateAccountCardActivity.class);
        newIntent.putExtra(IS_INDEX, message);
        return newIntent;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.ll_edit_account_name:
                showEditDialog(mTvAccountName, "账户名称");
                break;
            case R.id.ll_edit_account_balance:
                showEditDialog(mTvAccountBalance, "余额");
                break;
            case R.id.ll_edit_account_remark:
                showEditDialog(mTvAccountRemark, "备注");
                break;
            case R.id.ll_choose_account_type:
                String[] types = getResources().getStringArray(R.array.account_card_type);
                showOptionDialog(mTvAccountType,types,"选择类型");
                break;
            default:
                break;
        }
    }

}
