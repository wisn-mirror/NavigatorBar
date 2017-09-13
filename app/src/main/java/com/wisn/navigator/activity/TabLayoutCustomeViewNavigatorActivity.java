package com.wisn.navigator.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wisn.navigator.R;
import com.wisn.navigator.adapter.TabLayoutCustomViewFragmentAdapter;
import com.wisn.navigator.bean.TabLayoutCustomeHolder;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutCustomeViewNavigatorActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private ViewPager mViewPager;
    private TabLayout mTablelayout;
    private List<String> data = new ArrayList<String>();
    private int[] images = {R.drawable.home_0,
                            R.drawable.gift_0,
                            R.drawable.start_0,
                            R.drawable.watch_0
    };
    private int[] images2 = {R.drawable.home_1,
                            R.drawable.gift_1,
                            R.drawable.start_1,
                            R.drawable.watch_1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator_tab_layout);
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
        TabLayoutCustomViewFragmentAdapter
                fragmentPagerAdapter =
                new TabLayoutCustomViewFragmentAdapter(getSupportFragmentManager(), data, images, data);
        mViewPager.setAdapter(fragmentPagerAdapter);
        mViewPager.setCurrentItem(0);
        mTablelayout.setupWithViewPager(mViewPager);
        mTablelayout.setTabMode(TabLayout.MODE_FIXED);
        for(int i=0;i<mTablelayout.getTabCount();i++){
            mTablelayout.getTabAt(i).setCustomView(fragmentPagerAdapter.getView(this,i));
        }
        mTablelayout.addOnTabSelectedListener(this);
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        View inflate = tab.getCustomView();
        int  position = tab.getPosition();
        TabLayoutCustomeHolder tabLayoutCustomeHolder = (TabLayoutCustomeHolder) inflate.getTag();
        if(tabLayoutCustomeHolder!=null){
            tabLayoutCustomeHolder.mImageView.setImageResource(images2[position]);
            tabLayoutCustomeHolder.mTextview.setText(data.get(position));
            tabLayoutCustomeHolder.mTextview.setTextColor(ContextCompat.getColor(this,R.color.selectColor));
        }else{
            ImageView imageView= (ImageView) inflate.findViewById(R.id.imageView);
            TextView textview= (TextView) inflate.findViewById(R.id.textView);
            imageView.setImageResource(images2[position]);
            textview.setText(data.get(position));
            textview.setTextColor(ContextCompat.getColor(this,R.color.selectColor));
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        View inflate = tab.getCustomView();
        int  position = tab.getPosition();
        TabLayoutCustomeHolder tabLayoutCustomeHolder = (TabLayoutCustomeHolder) inflate.getTag();
        if(tabLayoutCustomeHolder!=null){
            tabLayoutCustomeHolder.mImageView.setImageResource(images[position]);
            tabLayoutCustomeHolder.mTextview.setText(data.get(position));
            tabLayoutCustomeHolder.mTextview.setTextColor(ContextCompat.getColor(this,R.color.unSelectColor));
        }else{
            ImageView imageView= (ImageView) inflate.findViewById(R.id.imageView);
            TextView textview= (TextView) inflate.findViewById(R.id.textView);
            imageView.setImageResource(images[position]);
            textview.setText(data.get(position));
            textview.setTextColor(ContextCompat.getColor(this,R.color.unSelectColor));
        }
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
