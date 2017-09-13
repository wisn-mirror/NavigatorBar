package com.wisn.navigator.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wisn.navigator.R;
import com.wisn.navigator.adapter.FragmentAdapter;
import com.wisn.navigator.view.MyTextView;

import java.util.ArrayList;
import java.util.List;

public class TextViewViewPagerNavigatorActivity extends AppCompatActivity implements View.OnClickListener,
                                                                                     ViewPager.OnPageChangeListener {

    private List<String> data = new ArrayList<String>();
    private MyTextView mWatch;
    private MyTextView mStart;
    private MyTextView mGift;
    private MyTextView mHome;
    private int selectIndex;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator_textview_viewpage);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mHome = (MyTextView) findViewById(R.id.home);
        mGift = (MyTextView) findViewById(R.id.gift);
        mStart = (MyTextView) findViewById(R.id.start);
        mWatch = (MyTextView) findViewById(R.id.watch);
        mHome.setOnClickListener(this);
        mGift.setOnClickListener(this);
        mStart.setOnClickListener(this);
        mWatch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mHome) {
            selectView(0, true);
        } else if (v == mGift) {
            selectView(1, true);
        } else if (v == mStart) {
            selectView(2, true);
        } else if (v == mWatch) {
            selectView(3, true);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        data.add("HomeFragment");
        data.add("GiftFragment");
        data.add("StartFragment");
        data.add("WatchFragment");
        selectView(0, true);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), data);
        mViewPager.setAdapter(fragmentAdapter);
        mViewPager.setCurrentItem(selectIndex);
        mViewPager.addOnPageChangeListener(this);
    }

    public void resetView(int oldPosition) {
        switch (oldPosition) {
            case 0:
                updateView(mHome, R.color.unSelectColor, R.drawable.home_0);
                break;
            case 1:
                updateView(mGift, R.color.unSelectColor, R.drawable.gift_0);
                break;
            case 2:
                updateView(mStart, R.color.unSelectColor, R.drawable.start_0);
                break;
            case 3:
                updateView(mWatch, R.color.unSelectColor, R.drawable.watch_0);
                break;
        }
    }

    public void selectView(int position, boolean isClick) {
        resetView(selectIndex);
        switch (position) {
            case 0:
                updateView(mHome, R.color.selectColor, R.drawable.home_1);
                break;
            case 1:
                updateView(mGift, R.color.selectColor, R.drawable.gift_1);
                break;
            case 2:
                updateView(mStart, R.color.selectColor, R.drawable.start_1);
                break;
            case 3:
                updateView(mWatch, R.color.selectColor, R.drawable.watch_1);
                break;
        }
        selectIndex = position;
        if (isClick)
            mViewPager.setCurrentItem(selectIndex);
    }

    public void updateView(MyTextView view, int colorId, int drawableId) {
        view.setTextColor(ContextCompat.getColor(this, colorId));
        view.setTopDrawable(ContextCompat.getDrawable(this, drawableId));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        selectView(position, false);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
