package com.sgu.jack.mypay.utils;

import android.content.Context;

/**
 * 作者：xushane on 2016/9/23
 * 邮箱：xushaneone@gmail.com
 */
public class DensityUtil {

    private DensityUtil(){}

    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
