package com.wisn.navigator.activity;

import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.wisn.navigator.R;
import com.wisn.navigator.adapter.FragmentAdapter;
import com.wisn.navigator.view.MyRadioButton;

import java.util.ArrayList;
import java.util.List;

public class RadioButtonViewPagerNavigatorActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,
                                                                                        ViewPager.OnPageChangeListener {

    private RadioGroup mRadioButton;
    private MyRadioButton mRadiobutton_bg_home;
    private MyRadioButton mRadiobutton_bg_gift;
    private MyRadioButton mRadiobutton_bg_start;
    private MyRadioButton mRadiobutton_bg_watch;
    private ViewPager mViewpager;

    private List<String> data=new ArrayList<String>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator_viewpager);
        mRadioButton = (RadioGroup)findViewById(R.id.bottom_radiogroup);
        mViewpager = (ViewPager)findViewById(R.id.viewpager);
        mRadiobutton_bg_home = (MyRadioButton)findViewById(R.id.radiobutton_bg_home);
        mRadiobutton_bg_gift = (MyRadioButton)findViewById(R.id.radiobutton_bg_gift);
        mRadiobutton_bg_start = (MyRadioButton)findViewById(R.id.radiobutton_bg_start);
        mRadiobutton_bg_watch = (MyRadioButton)findViewById(R.id.radiobutton_bg_watch);
        mRadioButton.setOnCheckedChangeListener(this);
        setDefaultFragment();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void setDefaultFragment() {
        mRadiobutton_bg_home.setChecked(true);
        data.add("HomeFragment");
        data.add("GiftFragment");
        data.add("StartFragment");
        data.add("WatchFragment");
        FragmentAdapter fragmentAdapter=new FragmentAdapter(getSupportFragmentManager(),data);
        mViewpager.setAdapter(fragmentAdapter);
        mViewpager.addOnPageChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        int  index=0;
        switch (checkedId){
            case R.id.radiobutton_bg_home:
                index=0;
                break;
            case R.id.radiobutton_bg_gift:
                index=1;
                break;
            case R.id.radiobutton_bg_start:
                index=2;
                break;
            case R.id.radiobutton_bg_watch:
                index=3;
                break;
        }
        mViewpager.setCurrentItem(index);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mRadioButton.setOnCheckedChangeListener(null);
        switch (position){
            case 0:
                mRadiobutton_bg_home.setChecked(true);
                break;
            case 1:
                mRadiobutton_bg_gift.setChecked(true);
                break;
            case 2:
                mRadiobutton_bg_start.setChecked(true);
                break;
            case 3:
                mRadiobutton_bg_watch.setChecked(true);
                break;
        }
        mRadioButton.setOnCheckedChangeListener(this);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
