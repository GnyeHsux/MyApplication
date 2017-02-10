package com.sgu.jack.mypay.base;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.DigitsKeyListener;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * 作者：xushane on 2016/9/25
 * 邮箱：xushaneone@gmail.com
 */
public class BaseActivity extends AppCompatActivity {

    //设置Toolbar
    public void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //显示EditDialog
    public void showEditDialog(final TextView textView, final String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //ThemeUtil.getCurrentDialogTheme(this));
        builder.setTitle(title);
        final EditText input = new EditText(this);
        if (title.equals("余额")) {
            String digits = "1234567890.";
            input.setKeyListener(DigitsKeyListener.getInstance(digits));
        }
        FrameLayout container = new FrameLayout(this);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(40, 40, 40, 40);
        container.addView(input, params);
        builder.setView(container);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String lable = input.getText().toString().trim();
                if (lable.isEmpty()) {
                    return;
                }
                //ToastUtils.showShort(BaseActivity.this, lable);
                if (title.equals("余额")) {
                    lable = "￥ " + lable;
                    textView.setText(lable);
                } else {
                    textView.setText(lable);
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    public void showOptionDialog(final TextView textView, final String[] datas, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setItems(datas, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textView.setText(datas[which]);
            }
        });
        builder.create().show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
