package com.wisn.navigator;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wisn.navigator.activity.RadioButtonActivity;
import com.wisn.navigator.activity.TabLayoutActivity;
import com.wisn.navigator.activity.ViewPagerActivity;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

    }
    public void onClick(View view){
        int viewId=view.getId();
        switch (viewId){
            case R.id.radioButton:
                startActivity(new Intent(this, RadioButtonActivity.class));
                break;
            case R.id.viewPagerButton:
                startActivity(new Intent(this, ViewPagerActivity.class));
                break;
            case R.id.TagLayoutButton:
                startActivity(new Intent(this, TabLayoutActivity.class));
                break;
        }
    }
}
