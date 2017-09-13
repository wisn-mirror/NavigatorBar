package com.wisn.navigator.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wisn.navigator.R;

/**
 * Created by wisn on 2017/9/11.
 */

public class GiftFragment extends BaseFragment {

    @Override
    public String getFragment() {
        return "GiftFragment";
    }


    @Override
    public View onCreateLazyView(LayoutInflater inflater,
                                 @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        Bundle bundle = this.getArguments();
//        String tag = bundle.getString("TAG");
        TextView textView = (TextView) view.findViewById(R.id.fragment_textView);
        textView.setText("GiftFragment");
        return view;
    }
}
