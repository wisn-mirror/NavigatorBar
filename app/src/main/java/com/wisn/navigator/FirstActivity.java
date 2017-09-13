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

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

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
        }
    }
}
