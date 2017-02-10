package com.sgu.jack.mypay.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.eftimoff.patternview.PatternView;
import com.sgu.jack.mypay.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xusha on 2016/8/25.
 */
public class PatternActivity extends AppCompatActivity {
    public final static String ACTION = "action";
    public final static int SET = 0;        //初次设置手势
    public final static int VERIFY = 1;     //验证手势
    public final static int MODIFY = 2;     //验证手势后重设手势
    public final static int CANCEL = 3;     //验证手势后取消手势
    public final static int ERROR = -1;     //验证手势失败

    private final static int SET_1_STAGE = 10;  //第一次手势密码状态
    private final static int SET_2_STAGE = 11;  //第二次手势密码状态
    private final static int CANCEL_1_STAGE = 20;   //取消手势状态
    private final static int MODIFY_1_STAGE = 30;   //修改手势状态
    private final static int MODIFY_2_STAGE = 31;
    private final static int MODIFY_3_STAGE = 32;
    private final static int VERIFY_1_STAGE = 40;   //验证手势状态


    private SharedPreferences mPrefs;
    @BindView(R.id.pattern)
    PatternView mPatternView;
    @BindView(R.id.pattern_hint)
    TextView mTvPatternHint;
    private int mCurrentStatus; //目前的状态
    private String mPattern;    //当前手势
    private String mPatternBak; //以前的手势


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern);

        ButterKnife.bind(this);

        mPrefs = getSharedPreferences("myPay", Context.MODE_PRIVATE);
        mPatternView.setOnPatternDetectedListener(new PatternDetected());

        Intent i = getIntent();
        int action = i.getIntExtra(ACTION, ERROR);
        switch (action) {
            case SET:
                setStageOne();
                break;
            case CANCEL:
                cancelStageOne();
                break;
            case MODIFY:
                modifyStageOne();
                break;
            case VERIFY:
                mTvPatternHint.setText(getString(R.string.input_pattern));
                mCurrentStatus = VERIFY_1_STAGE;
                break;
        }
    }

    //设置手势密码
    private void setStageOne() {
        mTvPatternHint.setText(getString(R.string.input_pattern));
        mCurrentStatus = SET_1_STAGE;
    }

    //验证手势密码
    private void setStageTwo() {
        mTvPatternHint.setText(getString(R.string.verify_pattern));
        mCurrentStatus = SET_2_STAGE;
        mPatternBak = mPattern;
    }

    //取消手势密码
    private void cancelStageOne() {
        mTvPatternHint.setText(getString(R.string.input_pattern));
        mCurrentStatus = CANCEL_1_STAGE;
    }

    //取消手势密码前，验证密码是否一致
    private void cancelFinished() {
        if (verifyPattern(mPattern, getSPPattern())) {
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putString("pattern", "");
            editor.apply();
            finish();
        } else {
            mTvPatternHint.setText(getString(R.string.input_pattern_error));
        }
    }

    //修改手势密码
    private void modifyStageOne() {
        mTvPatternHint.setText(getString(R.string.input_pattern));
        mCurrentStatus = MODIFY_1_STAGE;
    }

    //修改手势密码
    private void modifyStageTwo() {
        if (mCurrentStatus == MODIFY_1_STAGE) {
            //验证手势密码
            if (verifyPattern(mPattern, getSPPattern())) {
                mTvPatternHint.setText(getString(R.string.input_new_pattern));
                mCurrentStatus = MODIFY_2_STAGE;
            } else {
                mTvPatternHint.setText(getString(R.string.input_pattern_error));
            }
        } else if (mCurrentStatus == MODIFY_3_STAGE) {
            mTvPatternHint.setText(getString(R.string.input_new_pattern));
            mCurrentStatus = MODIFY_2_STAGE;
        }
    }

    private void modifyStageThree() {
        mTvPatternHint.setText(getString(R.string.verify_pattern));
        mCurrentStatus = MODIFY_3_STAGE;
        mPatternBak = mPattern;
    }

    private void setFinished() {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString("pattern", mPattern);
        editor.apply();
        finish();
    }

    //验证手势是否一致
    private boolean verifyPattern(String pattern1, String pattern2) {
        return (pattern1.equals(pattern2));
    }

    //获取储存的手势
    private String getSPPattern() {
        return mPrefs.getString("pattern", "");
    }

    private class PatternDetected implements PatternView.OnPatternDetectedListener {
        @Override
        public void onPatternDetected() {
            mPattern = mPatternView.getPatternString();
            mPatternView.clearPattern();
            if (mCurrentStatus == SET_1_STAGE) {
                setStageTwo();
            } else if (mCurrentStatus == SET_2_STAGE) {
                if (verifyPattern(mPattern, mPatternBak)) {
                    setFinished();
                } else {
                    setStageOne();
                }
            } else if (mCurrentStatus == CANCEL_1_STAGE) {
                cancelFinished();
            } else if (mCurrentStatus == MODIFY_1_STAGE) {
                modifyStageTwo();       // 密码验证成功，准备设置新密码
            } else if (mCurrentStatus == MODIFY_2_STAGE) {
                modifyStageThree();   // 输入新密码
            } else if (mCurrentStatus == MODIFY_3_STAGE) {
                if (verifyPattern(mPattern, mPatternBak)){  //验证新密码
                    setFinished();
                } else {
                    modifyStageTwo();
                }
            } else if (mCurrentStatus == VERIFY_1_STAGE){
                if (verifyPattern(mPattern, getSPPattern())){
                    Intent i = new Intent(PatternActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    mTvPatternHint.setText(getString(R.string.input_pattern_error));
                }
            }
        }
    }
}
