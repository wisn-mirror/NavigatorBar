package com.wisn.navigator.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentTransaction;

import android.widget.RadioGroup;

import com.wisn.navigator.R;
import com.wisn.navigator.fragment.FragmentFactory;
import com.wisn.navigator.view.MyRadioButton;

/**
 * Created by wisn on 2017/9/12.
 */

public class RadioButtonNavigatorActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup mRadioButton;
    private MyRadioButton mRadiobutton_bg_home;
    private MyRadioButton mRadiobutton_bg_gift;
    private MyRadioButton mRadiobutton_bg_start;
    private MyRadioButton mRadiobutton_bg_watch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator_radiobutton);
        mRadioButton = (RadioGroup)findViewById(R.id.bottom_radiogroup);
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
        Fragment homeFragment = FragmentFactory.getFragment("HomeFragment");
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.radio_content,homeFragment).commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment=null;
        switch (checkedId){
            case R.id.radiobutton_bg_home:
                fragment= FragmentFactory.getFragment("HomeFragment");
                break;
            case R.id.radiobutton_bg_gift:
                fragment=  FragmentFactory.getFragment("GiftFragment");
                break;
            case R.id.radiobutton_bg_start:
                fragment=FragmentFactory.getFragment("StartFragment");
                break;
            case R.id.radiobutton_bg_watch:
                fragment=FragmentFactory.getFragment("WatchFragment");
                break;

        }
        fragmentTransaction.replace(R.id.radio_content, fragment).commit();
    }
}
