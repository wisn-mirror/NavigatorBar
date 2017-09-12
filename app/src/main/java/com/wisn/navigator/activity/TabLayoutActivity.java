package com.wisn.navigator.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

import com.wisn.navigator.R;
import com.wisn.navigator.adapter.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTablelayout;
    private List<String> data=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTablelayout = (TabLayout) findViewById(R.id.tablelayout);
    }

    @Override
    protected void onStart() {
        super.onStart();
        data.add("HomeFragment");
        data.add("GiftFragment");
        data.add("StartFragment");
        data.add("WatchFragment");
        FragmentAdapter fragmentPagerAdapter=new FragmentAdapter(getSupportFragmentManager(), data) ;
        mViewPager.setAdapter(fragmentPagerAdapter);
        mViewPager.setCurrentItem(0);
        mTablelayout.setupWithViewPager(mViewPager);
        mTablelayout.setTabMode(TabLayout.MODE_FIXED);


    }
}
