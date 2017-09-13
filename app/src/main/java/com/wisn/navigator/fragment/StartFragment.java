package com.wisn.navigator.fragment;

import android.support.v4.app.Fragment;
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

public class StartFragment extends BaseFragment {


    @Override
    public String getFragment() {
        return "StartFragment";
    }


    @Override
    public View onCreateLazyView(LayoutInflater inflater,
                                 @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        Bundle bundle = this.getArguments();
//        String tag = bundle.getString("TAG");
        TextView textView = (TextView) view.findViewById(R.id.fragment_textView);
        textView.setText("StartFragment");
        return view;
    }

}
