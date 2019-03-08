package com.wisn.navigator;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.wisn.navigator.activity.NetIconNavigatorActivity;
import com.wisn.navigator.activity.RadioButtonNavigatorActivity;
import com.wisn.navigator.activity.TabLayoutNavigatorActivity;
import com.wisn.navigator.activity.TabLayoutCustomeViewNavigatorActivity;
import com.wisn.navigator.activity.TextViewNavigatorActivity;
import com.wisn.navigator.activity.TextViewViewPagerNavigatorActivity;
import com.wisn.navigator.activity.RadioButtonViewPagerNavigatorActivity;
import com.wisn.navigator.helper.NavigatorIconService;
import com.wisn.navigator.view.MyRadioButton;
import com.wisn.navigator.view.MyTextView;

public class FirstActivity extends AppCompatActivity {

    private MyRadioButton mRadioButton;
    private MyTextView MyTextView;
    private ImageView viewById;
    String url = "http://img6.16fan.com/attachments/wenzhang/201805/18/152660818127263ge.jpeg";
    private RequestOptions requestOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
//        mRadioButton = (MyRadioButton) findViewById(R.id.radiobutton_bg_watch);
//        MyTextView = (MyTextView) findViewById(R.id.MyTextView);
        viewById = (ImageView) findViewById(R.id.iv_target);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                NavigatorIconService.getInstance().download(FirstActivity.this, url);
            }
        });
        requestOptions = new RequestOptions();
        requestOptions
                .disallowHardwareConfig()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .format(DecodeFormat.PREFER_RGB_565)
                .fallback(R.mipmap.ic_launcher).diskCacheStrategy(DiskCacheStrategy.ALL);
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
            case R.id.NeticonrNavigatorActivity:
              /*  Glide.with(this)
                        .load(url)
                        .apply(requestOptions)
                        .into(viewById);*/
                startActivity(new Intent(this, NetIconNavigatorActivity.class));

                break;
           /* case R.id.clearText:
                mRadioButton.clearTip();
//                MyTextView.clearTip();
                break;
            case R.id.setText:
                mRadioButton.setTipText("99");
//                MyTextView.setTipText("99");
                break;
            case R.id.setTip:
                mRadioButton.setTip();
//                MyTextView.setTip();
                break;*/
        }
    }
}
