package com.wisn.navigator;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wisn.navigator.activity.RadioButtonActivity;
import com.wisn.navigator.activity.TabLayoutActivity;
import com.wisn.navigator.activity.TabLayoutCustomeViewActivity;
import com.wisn.navigator.activity.ViewPagerRadioButtonActivity;

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
                startActivity(new Intent(this, RadioButtonActivity.class));
                break;
            case R.id.viewPagerButton:
                startActivity(new Intent(this, ViewPagerRadioButtonActivity.class));
                break;
            case R.id.TagLayoutButton:
                startActivity(new Intent(this, TabLayoutActivity.class));
                break;
            case R.id.TabLayoutCustomeViewActivity:
                startActivity(new Intent(this, TabLayoutCustomeViewActivity.class));
                break;
        }
    }
}
