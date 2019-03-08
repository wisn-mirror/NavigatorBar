package com.wisn.navigator.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.wisn.navigator.R;
import com.wisn.navigator.bean.TabBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wisn on 2019/3/8 下午2:25.
 */
public class NetIconNavigator extends FrameLayout {


    private LinearLayout mTabLinearLayout;
    private Context mContext;
    private ArrayList<TabBean> mTabBeans = new ArrayList<>();
    private OnTabSelectListener mListener;
    private int mLastSelectPosition;
    private RequestOptions requestOptions;
    private boolean isDeault=true;
    private int[][] ints;

    public void setmListener(OnTabSelectListener mListener) {
        this.mListener = mListener;
    }

    public NetIconNavigator(Context context) {
        this(context, null, 0);
    }

    public NetIconNavigator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NetIconNavigator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        requestOptions = new RequestOptions();
        requestOptions
                .disallowHardwareConfig()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .format(DecodeFormat.PREFER_RGB_565)
                .fallback(R.mipmap.ic_launcher).diskCacheStrategy(DiskCacheStrategy.ALL);
        this.mContext = context;
        mTabLinearLayout = new LinearLayout(context);
        addView(mTabLinearLayout);
    }

    //添加导航栏数据
    public void setNetTabDate(List<TabBean> tabBeans, int select) {
        isDeault=false;
        if (tabBeans == null || tabBeans.size() == 0) {
            throw new IllegalStateException("TabEntitys can not be NULL or EMPTY !");
        }
        this.mTabBeans.clear();
        this.mTabBeans.addAll(tabBeans);
        notifyDataSetChanged(select);
        this.mLastSelectPosition = select;
    }

    public void setDefaultIcon(int[][] resid,int select){
        isDeault=true;
        ints =resid;
        notifyDataSetChanged(select);
        this.mLastSelectPosition = select;
    }


    //更新数据
    private void notifyDataSetChanged(int select) {
        mTabLinearLayout.removeAllViews();
        int   mTabCount =isDeault? ints.length:mTabBeans.size();
        for (int i = 0; i < mTabCount; i++) {
            View tabView = View.inflate(mContext, R.layout.layout_coutomizetab_top, null);
            tabView.setTag(i);
            upateSelectState(select == i, i, tabView);
            tabView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (Integer) v.getTag();
                    if (mLastSelectPosition != position) {
                        updateTabSelection(position);
                        if (mListener != null) {
                            mListener.onTabSelect(position);
                        }
                    } else {
                        if (mListener != null) {
                            mListener.onTabReselect(position);
                        }
                    }
                }
            });
            LinearLayout.LayoutParams lp_tab = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
            mTabLinearLayout.addView(tabView, i, lp_tab);
        }

    }

    private void upateSelectState(boolean isSelect, int position, View tabView) {

        TextView tv_tab_title = (TextView) tabView.findViewById(R.id.tv_tab_title);
        ImageView iv_tab_icon = (ImageView) tabView.findViewById(R.id.iv_tab_icon);
        if(isDeault){
            tv_tab_title.setText(getResources().getString(ints[position][2]));
            iv_tab_icon.setImageResource(isSelect?ints[position][1]:ints[position][0]);
        }else{
            TabBean tabBean = mTabBeans.get(position);
            tv_tab_title.setText(tabBean.text);
            String unSelectUrl = tabBean.src;
            String SelectUrl = tabBean.srcUsed;
            Glide.with(this)
                    .load(isSelect ? SelectUrl : unSelectUrl)
                    .apply(requestOptions)
                    .into(iv_tab_icon);
        }
        if (isSelect) {
            mLastSelectPosition = position;
        }

    }


    //更新tab
    public  void updateTabSelection(int position) {
        View tabViewOld = mTabLinearLayout.getChildAt(mLastSelectPosition);
        upateSelectState(false, mLastSelectPosition, tabViewOld);
        View tabViewNew = mTabLinearLayout.getChildAt(position);
        upateSelectState(true, position, tabViewNew);
    }


    public interface OnTabSelectListener {
        void onTabSelect(int position);

        void onTabReselect(int position);
    }


}
