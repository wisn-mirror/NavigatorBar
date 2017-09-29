package com.wisn.navigator;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wisn.navigator.activity.RadioButtonNavigatorActivity;
import com.wisn.navigator.activity.TabLayoutNavigatorActivity;
import com.wisn.navigator.activity.TabLayoutCustomeViewNavigatorActivity;
import com.wisn.navigator.activity.TextViewNavigatorActivity;
import com.wisn.navigator.activity.TextViewViewPagerNavigatorActivity;
import com.wisn.navigator.activity.RadioButtonViewPagerNavigatorActivity;
import com.wisn.navigator.view.MyRadioButton;
import com.wisn.navigator.view.MyTextView;

public class FirstActivity extends AppCompatActivity {

    private MyRadioButton mRadioButton;
    private MyTextView MyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        mRadioButton = (MyRadioButton) findViewById(R.id.radiobutton_bg_watch);
        MyTextView = (MyTextView) findViewById(R.id.MyTextView);
    }

    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId) {
            case R.id.radioButton:
                startActivity(new Intent(this, RadioButtonNavigatorActivity.class));
                break;
            case R.id.viewPagerButton:
                startActivity(new Intent(this, RadioButtonViewPagerNavigatorActivity.class));
                break;
            case R.id.TagLayoutButton:
                startActivity(new Intent(this, TabLayoutNavigatorActivity.class));
                break;
            case R.id.TabLayoutCustomeViewActivity:
                startActivity(new Intent(this, TabLayoutCustomeViewNavigatorActivity.class));
                break;
            case R.id.TextViewNavigatorActivity:
                startActivity(new Intent(this, TextViewNavigatorActivity.class));
                break;
            case R.id.TextViewViewPagerNavigatorActivity:
                startActivity(new Intent(this, TextViewViewPagerNavigatorActivity.class));
                break;
            case R.id.clearText:
                mRadioButton.clearTip();
                MyTextView.clearTip();
                break;
            case R.id.setText:
                mRadioButton.setTipText("99");
                MyTextView.setTipText("99");
                break;
            case R.id.setTip:
                mRadioButton.setTip();
                MyTextView.setTip();
                break;
        }
    }
}
