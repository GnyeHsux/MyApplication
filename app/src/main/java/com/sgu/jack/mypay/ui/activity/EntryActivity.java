package com.sgu.jack.mypay.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.sgu.jack.mypay.R;

/**
 * Created by xusha on 2016/8/25.
 */
public class EntryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        new StartTask().execute();
    }

    private class StartTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            // 手势解锁
            SharedPreferences sp = getSharedPreferences("myPay", Context.MODE_PRIVATE);
            String pattern = sp.getString("pattern", "");
            if (pattern.isEmpty()) {
                final Intent i = new Intent(EntryActivity.this, MainActivity.class);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            startActivity(i);
                            finish();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } else {
                Intent i = new Intent(EntryActivity.this, PatternActivity.class);
                i.putExtra(PatternActivity.ACTION, PatternActivity.VERIFY);
                startActivity(i);
                finish();
            }
        }
    }
}
