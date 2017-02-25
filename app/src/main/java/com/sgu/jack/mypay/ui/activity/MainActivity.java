package com.sgu.jack.mypay.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sgu.jack.mypay.R;
import com.sgu.jack.mypay.support.CrashHandler;
import com.sgu.jack.mypay.ui.fragment.AlipayListFragment;
import com.sgu.jack.mypay.ui.fragment.CardListFragment;
import com.sgu.jack.mypay.ui.fragment.StatisticsFragment;
import com.sgu.jack.mypay.ui.fragment.WechatListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;

    private ViewPagerAdapter mViewPagerAdapter;
    private ArrayList<String> mSearchHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        CrashHandler.register(this);


        setSupportActionBar(mToolbar);
        setTitle("");

        final ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(mViewPager);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
                setTitle(mViewPagerAdapter.getFragmentTitle(tab.getPosition()));
                tab.setContentDescription(
                        mViewPagerAdapter.getPageIcon(
                                tab.getPosition(),
                                getThemeAccentColor(MainActivity.this)
                        )
                );
                if (tab.getPosition() == 1 || tab.getPosition() == 2) {
                    mFab.setVisibility(View.VISIBLE);
                    mFab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            Snackbar.make(v, "other", Snackbar.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this, CreateBillAcrtivity.class));
                        }
                    });
                } else if (tab.getPosition() == 3) {
                    mFab.setVisibility(View.GONE);
                } else {
                    mFab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mFab.setVisibility(View.VISIBLE);
                            startActivity(new Intent(MainActivity.this, CreateAccountCardActivity.class));
                        }
                    });
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setContentDescription(
                        mViewPagerAdapter.getPageIcon(
                                tab.getPosition(),
                                ContextCompat.getColor(getApplicationContext(), R.color.tab_white_trans)
                        )
                );
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //init TabLayout
        setTitle(mViewPagerAdapter.getFragmentTitle(0));
        mTabLayout.getTabAt(0).setContentDescription(
                mViewPagerAdapter.getPageIcon(
                        0,
                        getThemeAccentColor(this)
                )
        );

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Snackbar.make(v, "Fab", Snackbar.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, CreateAccountCardActivity.class));
            }
        });
    }


    private static int getThemeAccentColor(final Context context) {
        final TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorAccent, value, true);
        return value.data;
    }


    //配置viewPager
    private void setupViewPager(ViewPager viewPager) {
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerAdapter.addFrag(getString(R.string.pay_list), R.drawable.ic_action_reorder);
        mViewPagerAdapter.addFrag(getString(R.string.alipay_list), R.drawable.ic_action_alipay);
        mViewPagerAdapter.addFrag(getString(R.string.wechat_list), R.drawable.ic_action_wechat);
        mViewPagerAdapter.addFrag(getString(R.string.statistics), R.drawable.ic_action_trending_up);
        viewPager.setAdapter(mViewPagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_login) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }

        if (id == R.id.action_settings) {
            Intent i = new Intent();
            i.setAction(Intent.ACTION_MAIN);
            i.setClass(MainActivity.this, SettingsActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<String> mFragmentTitleList = new ArrayList<>();
        private final List<Integer> mFragmentIconList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) return new CardListFragment();
            if (position == 1) return new AlipayListFragment();
            if (position == 2) return new WechatListFragment();
            if (position == 3) return new StatisticsFragment();
            return null;
        }

        @Override
        public int getCount() {
            return mFragmentTitleList.size();
        }

        public void addFrag(String title, @DrawableRes int id) {
            mFragmentTitleList.add(title);
            mFragmentIconList.add(id);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getPageIcon(position, ContextCompat.getColor(getApplicationContext(), R.color.tab_white_trans));
        }

        public CharSequence getPageIcon(int position, int filterColor) {
            Drawable image = ResourcesCompat.getDrawable(getResources(), mFragmentIconList.get(position), null);
            image.setColorFilter(filterColor, PorterDuff.Mode.MULTIPLY);
            image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
            SpannableString sb = new SpannableString(" ");
            ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
            sb.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return sb;
        }

        public String getFragmentTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
