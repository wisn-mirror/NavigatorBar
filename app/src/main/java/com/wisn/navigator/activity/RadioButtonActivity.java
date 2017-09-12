package com.wisn.navigator.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wisn.navigator.R;
import com.wisn.navigator.fragment.FragmentFactory;

/**
 * Created by wisn on 2017/9/12.
 */

public class RadioButtonActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup mRadioButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator_radiobutton);
        mRadioButton = (RadioGroup)findViewById(R.id.radioGroup);
        mRadioButton.setOnCheckedChangeListener(this);
    }

    @Override
    protected void onStart() {
        setDefaultFragment();
        super.onStart();

    }

    private void setDefaultFragment() {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId){
            case R.id.radiobutton_bg_home:
                FragmentFactory.getFragment("HomeFragment");
                ;
            case R.id.radiobutton_bg_gift:
                FragmentFactory.getFragment("GiftFragment");
                ;
            case R.id.radiobutton_bg_start:
                FragmentFactory.getFragment("StartFragment");
                ;
            case R.id.radiobutton_bg_watch:
                FragmentFactory.getFragment("WatchFragment");
                ;

        }
    }
}
