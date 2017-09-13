package com.wisn.navigator.fragment;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wisn.navigator.R;
import com.wisn.navigator.base.BaseFragment;
import com.wisn.navigator.base.BaseLazyFragment;

/**
 * Created by wisn on 2017/9/11.
 */

public class WatchFragment extends BaseLazyFragment {


    @Override
    public String getFragment() {
        return "WatchFragment";
    }

    @Override
    public View onCreateLazyView(LayoutInflater inflater,
                                 @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        Bundle bundle = this.getArguments();
//        String tag = bundle.getString("TAG");
        TextView textView = (TextView) view.findViewById(R.id.fragment_textView);
        textView.setText("WatchFragment");
        return view;
    }

    @Override
    public void firstVisible() {
        super.firstVisible();
        Log.e(TAG,"firstVisible ");
    }

    @Override
    public void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        Log.e(TAG,"onFragmentVisibleChange isVisible:"+isVisible);
    }
}
