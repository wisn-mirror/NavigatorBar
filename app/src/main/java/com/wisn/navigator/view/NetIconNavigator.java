package com.wisn.navigator.view;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
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
import java.util.HashMap;
import java.util.List;

/**
 * Created by Wisn on 2019/3/8 下午2:25.
 */
public class NetIconNavigator extends FrameLayout {


    private LinearLayout mTabLinearLayout;
    private Context mContext;
    private ArrayList<TabBean> mTabBeans = new ArrayList<>();
    private HashMap<String, TabBean> urlMap = new HashMap<>();
    private OnTabSelectListener mListener;
    private int mLastSelectPosition;
    private RequestOptions requestOptions;
    private boolean isDeault = true;

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
        isDeault = false;
        if (tabBeans == null || tabBeans.size() == 0) {
            throw new IllegalStateException("TabEntitys can not be NULL or EMPTY !");
        }
        urlMap.clear();
        this.mTabBeans.clear();
        this.mTabBeans.addAll(tabBeans);
        notifyDataSetChanged(select);
        this.mLastSelectPosition = select;
    }


    public void setDefaultIcon(List<TabBean> resid, int select) {
        isDeault = true;
        urlMap.clear();
        this.mTabBeans.clear();
        this.mTabBeans.addAll(resid);
        notifyDataSetChanged(select);
        this.mLastSelectPosition = select;
    }


    //更新数据
    private void notifyDataSetChanged(int select) {
        mTabLinearLayout.removeAllViews();
        int mTabCount = mTabBeans.size();
        for (int i = 0; i < mTabCount; i++) {
            View tabView = View.inflate(mContext, R.layout.lib_layout_coutomizetab_top, null);
            NavigatorViewHolder navigatorViewHolder = new NavigatorViewHolder(tabView);
            TabBean tabBean = mTabBeans.get(i);
            tabBean.__position = i;
            navigatorViewHolder.update(i, tabBean.url);
            urlMap.put(tabBean.url, tabBean);
            tabView.setTag(navigatorViewHolder);
            upateSelectState(select == i, i, tabView, tabBean);
            tabView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        NavigatorViewHolder navigatorViewHolder = (NavigatorViewHolder) v.getTag();
                        if (mLastSelectPosition != navigatorViewHolder.position) {
                            updateTabSelection(navigatorViewHolder.position);
                            if (mListener != null) {
                                mListener.onTabSelect(navigatorViewHolder.position, navigatorViewHolder.url);
                            }
                        } else {
                            if (mListener != null) {
                                mListener.onTabReselect(navigatorViewHolder.position, navigatorViewHolder.url);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            LinearLayout.LayoutParams lp_tab = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
            mTabLinearLayout.addView(tabView, i, lp_tab);
        }

    }

    private void upateSelectState(boolean isSelect, int position, View tabView, TabBean tabBean) {
        try {
            NavigatorViewHolder navigatorViewHolder = getNavigatorViewHolder(position, tabView, tabBean);
            if (isDeault) {
                navigatorViewHolder.tv_tab_title.setText(tabBean.text);
                navigatorViewHolder.iv_tab_icon.setImageResource(isSelect ? tabBean.resid[1] : tabBean.resid[0]);
            } else {
                if(tabBean.isReplace){
                    navigatorViewHolder.tv_tab_title.setText(tabBean.text);
                    navigatorViewHolder.iv_tab_icon.setImageResource(isSelect ? tabBean.resid[1] : tabBean.resid[0]);
                }else{
                    navigatorViewHolder.tv_tab_title.setText(tabBean.text);
                    String unSelectUrl = tabBean.src;
                    String SelectUrl = tabBean.srcUsed;
                    Glide.with(this)
                            .load(isSelect ? SelectUrl : unSelectUrl)
                            .apply(requestOptions)
                            .into(navigatorViewHolder.iv_tab_icon);
                }

            }
            if (isSelect) {
                mLastSelectPosition = position;
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    private NavigatorViewHolder getNavigatorViewHolder(int position, View tabView) {
        return getNavigatorViewHolder(position, tabView, null);
    }

    private NavigatorViewHolder getNavigatorViewHolder(int position, View tabView, TabBean tabBean) {
        NavigatorViewHolder navigatorViewHolder = null;
        try {
            Object tag = tabView.getTag();
            if (tag != null && tag instanceof NavigatorViewHolder) {
                navigatorViewHolder = (NavigatorViewHolder) tag;
            } else {
                navigatorViewHolder = new NavigatorViewHolder(tabView);
            }
            if (tabBean != null) {
                navigatorViewHolder.url = tabBean.url;
            }
            navigatorViewHolder.position = position;
            tabView.setTag(navigatorViewHolder);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return navigatorViewHolder;
    }

    //更新tab
    public void updateTabSelection(int position) {
        try {
            if (mTabLinearLayout == null) return;
            View tabViewOld = mTabLinearLayout.getChildAt(mLastSelectPosition);
            upateSelectState(false, mLastSelectPosition, tabViewOld, mTabBeans.get(mLastSelectPosition));
            View tabViewNew = mTabLinearLayout.getChildAt(position);
            upateSelectState(true, position, tabViewNew, mTabBeans.get(position));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //更新tab
    public void updateTabSelection(String url) {
        try {
            if (mTabLinearLayout == null) return;
            TabBean tabBeannew = urlMap.get(url);
            if (tabBeannew != null) {
                View tabViewOld = mTabLinearLayout.getChildAt(mLastSelectPosition);
                upateSelectState(false, mLastSelectPosition, tabViewOld, mTabBeans.get(mLastSelectPosition));
                View tabViewNew = mTabLinearLayout.getChildAt(tabBeannew.__position);
                upateSelectState(true, tabBeannew.__position, tabViewNew, tabBeannew);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void replaceItem(String url, TabBean tabBean) {
        TabBean tabBeannew = urlMap.get(url);
        if (tabBeannew != null) {
            replaceItem(tabBeannew.__position, tabBean);
        }
    }

    public void replaceItem(int position, TabBean tabBean) {
        try {
            if (mTabLinearLayout == null) return;
            TabBean tabBean1 = mTabBeans.get(position);
            tabBean.__position = position;
            urlMap.remove(tabBean1.url);
            mTabBeans.set(position, tabBean);
            urlMap.put(tabBean1.url, tabBean);
            View tabViewOld = mTabLinearLayout.getChildAt(position);
            upateSelectState(position == mLastSelectPosition, position, tabViewOld, tabBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTip(String url) {
        TabBean tabBeannew = urlMap.get(url);
        if (tabBeannew != null) {
            setTip(tabBeannew.__position);
        }
    }

    public void setTip(int position) {
        try {
            if (mTabLinearLayout == null) return;
            View tabView = mTabLinearLayout.getChildAt(position);
            NavigatorViewHolder navigatorViewHolder = getNavigatorViewHolder(position, tabView);
            navigatorViewHolder.tv_tab_msg_tip.setVisibility(View.VISIBLE);
            navigatorViewHolder.tv_tab_msg.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTipMsg(String url, String msg) {
        TabBean tabBeannew = urlMap.get(url);
        if (tabBeannew != null) {
            setTipMsg(tabBeannew.__position, msg);
        }
    }

    public void setTipMsg(int position, String msg) {
        if (mTabLinearLayout == null) return;

        try {
            if (TextUtils.isEmpty(msg)) {
                clearTipMessage(position);
            } else {
                View tabView = mTabLinearLayout.getChildAt(position);
                NavigatorViewHolder navigatorViewHolder = getNavigatorViewHolder(position, tabView);
                navigatorViewHolder.tv_tab_msg_tip.setVisibility(View.GONE);
                navigatorViewHolder.tv_tab_msg.setVisibility(View.VISIBLE);
                navigatorViewHolder.tv_tab_msg.setText(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTipMsg(String url, int msg) {
        TabBean tabBeannew = urlMap.get(url);
        if (tabBeannew != null) {
            setTipMsg(tabBeannew.__position, msg);
        }
    }

    public void setTipMsg(int position, int msg) {
        if (msg == 0) {
            clearTipMessage(position);
        } else {
            setTipMsg(position, String.valueOf(msg));
        }
    }

    public void clearTipMessage(String url) {
        TabBean tabBeannew = urlMap.get(url);
        if (tabBeannew != null) {
            clearTipMessage(tabBeannew.__position);
        }
    }

    public void clearTipMessage(int position) {
        if (mTabLinearLayout == null) return;

        try {
            View tabView = mTabLinearLayout.getChildAt(position);
            NavigatorViewHolder navigatorViewHolder = getNavigatorViewHolder(position, tabView);
            navigatorViewHolder.tv_tab_msg_tip.setVisibility(View.GONE);
            navigatorViewHolder.tv_tab_msg.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public interface OnTabSelectListener {
        void onTabSelect(int position, String url);

        void onTabReselect(int position, String url);
    }

    public class NavigatorViewHolder {
        TextView tv_tab_title;
        ImageView iv_tab_icon;
        ImageView tv_tab_msg_tip;
        TextView tv_tab_msg;
        public int position;
        public String url;

        public NavigatorViewHolder(View tabView) {
            tv_tab_title = (TextView) tabView.findViewById(R.id.tv_tab_title);
            iv_tab_icon = (ImageView) tabView.findViewById(R.id.iv_tab_icon);
            tv_tab_msg_tip = (ImageView) tabView.findViewById(R.id.tv_tab_msg_tip);
            tv_tab_msg = (TextView) tabView.findViewById(R.id.tv_tab_msg);
        }

        public void update(int position, String url) {
            this.position = position;
            this.url = url;
        }
    }

}
