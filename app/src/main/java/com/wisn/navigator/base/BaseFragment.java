package com.wisn.navigator.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wisn on 2017/9/13.
 */

public abstract class BaseFragment extends Fragment {

    private View mRootView;
    private String TAG = getFragment();
    public abstract String getFragment();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e(TAG,
              "setUserVisibleHint isVisibleToUser:" +
              isVisibleToUser +
              " getUserVisibleHint" +
              getUserVisibleHint());
    }

    @Override
    public boolean getUserVisibleHint() {
        Log.e(TAG, "getUserVisibleHint " + super.getUserVisibleHint());
        return super.getUserVisibleHint();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG, "onAttach ");

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate ");

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView ");
        if(mRootView==null){
            mRootView = onCreateLazyView(inflater, container, savedInstanceState);
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG, "onViewCreated ");
    }

    /**
     * onCreateLazyView
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     *
     * @return
     */
    public abstract View onCreateLazyView(LayoutInflater inflater,
                                          @Nullable ViewGroup container,
                                          @Nullable Bundle savedInstanceState);

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "onActivityCreated ");

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "onStart ");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume ");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause ");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, "onStop ");

    }

    /**
     * fragment return to the layout from the back stack
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, "onDestroyView ");
        if (mRootView != null) {
            ((ViewGroup) mRootView.getParent()).removeView(mRootView);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy ");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG, "onDetach ");

    }
}
