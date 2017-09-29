package com.wisn.navigator.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.wisn.navigator.R;
import com.wisn.navigator.adapter.TabLayoutFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutNavigatorActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private ViewPager mViewPager;
    private TabLayout mTablelayout;
    private List<String> data = new ArrayList<String>();
    private int[] images = {R.drawable.home_0,
                            R.drawable.gift_0,
                            R.drawable.start_0,
                            R.drawable.watch_0
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator_tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTablelayout = (TabLayout) findViewById(R.id.tablelayout);
        data.add("HomeFragment");
        data.add("GiftFragment");
        data.add("StartFragment");
        data.add("WatchFragment");
        TabLayoutFragmentAdapter
                fragmentPagerAdapter =
                new TabLayoutFragmentAdapter(getSupportFragmentManager(), data, images, data);
        mViewPager.setAdapter(fragmentPagerAdapter);
        mViewPager.setCurrentItem(0);
        mTablelayout.setupWithViewPager(mViewPager);
        mTablelayout.setTabMode(TabLayout.MODE_FIXED);
        mTablelayout.getTabAt(0).setIcon(R.drawable.home_1).setText("HomeFragment");
        mTablelayout.getTabAt(1).setIcon(R.drawable.gift_0).setText("GiftFragment");
        mTablelayout.getTabAt(2).setIcon(R.drawable.start_0).setText("StartFragment");
        mTablelayout.getTabAt(3).setIcon(R.drawable.watch_0).setText("WatchFragment");
        mTablelayout.addOnTabSelectedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        CharSequence text = tab.getText();
        if (text.equals("HomeFragment")) {
            tab.setIcon(R.drawable.home_1);
        } else if (text.equals("GiftFragment")) {
            tab.setIcon(R.drawable.gift_1);
        } else if (text.equals("StartFragment")) {
            tab.setIcon(R.drawable.start_1);
        } else if (text.equals("WatchFragment")) {
            tab.setIcon(R.drawable.watch_1);
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        CharSequence text = tab.getText();
        if (text.equals("HomeFragment")) {
            tab.setIcon(R.drawable.home_0);
        } else if (text.equals("GiftFragment")) {
            tab.setIcon(R.drawable.gift_0);
        } else if (text.equals("StartFragment")) {
            tab.setIcon(R.drawable.start_0);
        } else if (text.equals("WatchFragment")) {
            tab.setIcon(R.drawable.watch_0);
        }
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
