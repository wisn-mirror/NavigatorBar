package com.wisn.navigator.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wisn.navigator.R;
import com.wisn.navigator.fragment.FragmentFactory;

import java.util.List;

/**
 * Created by wisn on 2017/9/12.
 */

public class TabLayoutFragmentAdapter extends FragmentPagerAdapter {
    List<String> data=null;
    List<String> tableName=null;
    int[] imgIds=null;
    public TabLayoutFragmentAdapter(FragmentManager fm, List<String> data,int[] imgIds,List<String> tableName) {
        super(fm);
        this.data=data;
        this.imgIds=imgIds;
        this.tableName=tableName;
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.getFragment(data.get(position));
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
//        return super.getPageTitle(position);
        return data.get(position);
    }

}
