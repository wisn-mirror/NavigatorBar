package com.wisn.navigator;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wisn.navigator.activity.RadioButtonActivity;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        startActivity(new Intent(this, RadioButtonActivity.class));

    }
    public void onClick(View view){
        int viewId=view.getId();
        switch (viewId){
            case R.id.radioButton:
                startActivity(new Intent(this, RadioButtonActivity.class));
            case R.id.viewPagerButton:
                startActivity(new Intent(this, RadioButtonActivity.class));
        }
    }
}
