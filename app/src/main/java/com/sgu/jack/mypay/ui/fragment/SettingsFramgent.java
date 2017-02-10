package com.sgu.jack.mypay.ui.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AlertDialog;

import com.sgu.jack.mypay.R;
import com.sgu.jack.mypay.ui.activity.PatternActivity;

/**
 * 设置页面
 * Created by xusha on 2016/8/25.
 */
public class SettingsFramgent extends PreferenceFragment implements Preference.OnPreferenceClickListener {

    private final static String Weibo_Url = "http://weibo.com/shane511";
    private final static String Author = "韶关学院 - 徐绍殷";

    private Preference mThemePref;      //主题设置
    private Preference mPatternPref;    //手势设置
    private Preference mVersionPref;    //版本
    private Preference mWeiboPref;      //微博
    private Preference mAuthorPref;     //作者


    private SharedPreferences mPrefs;

    private String mPattern;    //记录手势

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);

        mThemePref = findPreference("change_theme");
        mPatternPref = findPreference("pattern");
        mVersionPref = findPreference("version");
        mWeiboPref = findPreference("weibo");
        mAuthorPref = findPreference("author");

        //获取app版本
        String version = "Unknown";
        try {
            version = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName;
            version += " (" + getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionCode + ")";
        } catch (Exception e) {
            e.printStackTrace();
        }

        mPrefs = getActivity().getSharedPreferences("myPay", Context.MODE_PRIVATE);

        mVersionPref.setSummary(version);
        mAuthorPref.setSummary(Author);
        mWeiboPref.setOnPreferenceClickListener(this);
        mWeiboPref.setSummary(Weibo_Url);
        mThemePref.setOnPreferenceClickListener(this);
        mPatternPref.setOnPreferenceClickListener(this);
        initPattern();


    }

    //初始化手势设置选项
    private void initPattern() {
        mPattern = mPrefs.getString("pattern", "");
        if (mPattern.isEmpty()) {
            mPatternPref.setSummary(R.string.pattern_empty);
        } else {
            mPatternPref.setSummary(R.string.pattern_setted);
        }
    }

    private void onPatternClick() {
        if (mPattern.isEmpty()) {
            Intent i = new Intent();
            i.setClass(getActivity(), PatternActivity.class);
            i.putExtra(PatternActivity.ACTION, PatternActivity.SET);
            startActivity(i);
        } else {
            AlertDialog.Builder builder =
                    new AlertDialog.Builder(getActivity(), R.style.AppTheme_Dialog);
            builder.setTitle(R.string.choice);
            builder.setItems(
                    new String[]{
                            getString(R.string.setting_pattern_cancel),
                            getString(R.string.setting_pattern_change)
                    },
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == 0) {
                                Intent i = new Intent(getActivity(), PatternActivity.class);
                                i.putExtra(PatternActivity.ACTION, PatternActivity.CANCEL);
                                startActivity(i);
                                dialog.dismiss();
                            } else if (which == 1) {
                                Intent i = new Intent(getActivity(), PatternActivity.class);
                                i.putExtra(PatternActivity.ACTION, PatternActivity.MODIFY);
                                startActivity(i);
                                dialog.dismiss();
                            }
                        }
                    }
            );
            builder.show();
        }
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        if (preference == mWeiboPref){
            Uri uri = Uri.parse(Weibo_Url);
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
            return true;
        }
        if (preference == mPatternPref){
            onPatternClick();
            return true;
        }
        if (preference == mThemePref){
            return true;
        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        initPattern();
    }
}
