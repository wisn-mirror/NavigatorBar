package com.wisn.navigator.bean;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wisn.navigator.R;

/**
 * Created by wisn on 2017/9/13.
 */

public class TabLayoutCustomeHolder {

    public  final ImageView mImageView;
    public  final TextView mTextview;

    public TabLayoutCustomeHolder(View view){
        mImageView = (ImageView) view.findViewById(R.id.imageView);
        mTextview = (TextView) view.findViewById(R.id.textView);
    }

}
