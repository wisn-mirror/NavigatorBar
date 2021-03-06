package com.wisn.navigator.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.wisn.navigator.R;
import com.wisn.navigator.bean.TabBean;
import com.wisn.navigator.helper.NavigatorIconService;
import com.wisn.navigator.view.NetIconNavigator;

import java.util.ArrayList;
import java.util.List;

public class NetIconNavigatorActivity extends AppCompatActivity implements View.OnClickListener {

    private List<TabBean> data = new ArrayList<>();
    private String[] mSelectUrls = {
            "https://static3.laiyifen.com/edu-files/cms/image/1547965147229_1478.png",
            "https://static4.laiyifen.com/edu-files/cms/image/1547965161892_6032.png",
            "https://static4.laiyifen.com/edu-files/cms/image/1547965176811_2340.png",
            "https://static1.laiyifen.com/edu-files/cms/image/1547965193293_8079.png",
            "https://static3.laiyifen.com/edu-files/cms/image/1547965147229_1478.png",
            "https://static4.laiyifen.com/edu-files/cms/image/1547965161892_6032.png"};
    private String[] mUnSelectUrls = {
            "https://static2.laiyifen.com/edu-files/cms/image/1547965142658_3937.png",
            "https://static1.laiyifen.com/edu-files/cms/image/1547965157913_4733.png",
            "https://static4.laiyifen.com/edu-files/cms/image/1547965172195_9263.png",
            "https://static1.laiyifen.com/edu-files/cms/image/1547965188684_2881.png",
            "https://static2.laiyifen.com/edu-files/cms/image/1547965142658_3937.png",
            "https://static1.laiyifen.com/edu-files/cms/image/1547965157913_4733.png"};
    private String[] Urls = {
            "http://lyf://mainPage",
            "http://lyf://DSContact",
            "http://lyf://DSMainItemView",
            "http://DSMainItemView",
            "http://lyf://userCenter",
            "http://lyf://adc",
            "http://lyf://shopcart"
    };
    public int[][] resicon = {
            {
                    R.drawable.gift_0,
                    R.drawable.gift_1, R.string.main_home},
            {
                    R.drawable.home_0,
                    R.drawable.home_1, R.string.main_classification},
            {
                    R.drawable.start_0,
                    R.drawable.start_1, R.string.main_community},
            {
                    R.drawable.watch_0,
                    R.drawable.watch_1, R.string.main_hapgasstation}};
    public int[][] resicon2 = {
            {
                    R.drawable.app_icon_home_normal,
                    R.drawable.app_icon_home_selected, R.string.main_home},
            {
                    R.drawable.app_icon_mall_normal,
                    R.drawable.app_icon_mall_selected, R.string.main_classification},
            {
                    R.drawable.app_icon_im_normal,
                    R.drawable.app_icon_im_selected, R.string.main_community},
            {
                    R.drawable.app_icon_mine_normal,
                    R.drawable.app_icon_mine_selected, R.string.main_hapgasstation}};
    private NetIconNavigator bottom_radiogroup;
    private TextView textview1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator_neticon_viewpage);

        textview1 = (TextView) findViewById(R.id.textview1);
        bottom_radiogroup = (NetIconNavigator) findViewById(R.id.bottom_radiogroup);
        bottom_radiogroup.setmListener(new NetIconNavigator.OnTabSelectListener() {
            @Override
            public void onTabSelect(int position, String url) {
                textview1.setText("选中 " + position + " " + url);

            }

            @Override
            public void onTabReselect(int position, String url) {
                textview1.setText("重新选中 " + position + " " + url);

            }

        });

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button1) {
            // 下载网络图片
            NavigatorIconService.getInstance().download(this, data);
        } else if (id == R.id.button2) {
            // 是否所有图片下载完成
            NavigatorIconService.getInstance().checkDownload(this, data, new NavigatorIconService.CheckDownloadCallBack() {
                @Override
                public void allDownloadSuccess() {
                    Log.d("NavigatorIconService", "  downloadcallback");
//                    textview1.setText("全部下载完成 ");

                }

                @Override
                public void error(String msg) {
                }
            });

        } else if (id == R.id.button3) {
            // 切换网络图怕模式
            data.clear();
            for (int i = 0; i < mSelectUrls.length; i++) {
                data.add(new TabBean("第" + i + "栏", mSelectUrls[i], mUnSelectUrls[i], Urls[i]));
            }
            bottom_radiogroup.setNetTabDate(data, 0);
        } else if (id == R.id.button4) {
            // 默认
            data.clear();
            for (int i = 0; i < resicon2.length; i++) {
                data.add(new TabBean("第" + i + "栏", resicon2[i][1], resicon2[i][0], Urls[i]));
            }
            bottom_radiogroup.setDefaultIcon(data, 0);

        } else if (id == R.id.button5) {
            // 选中0
            bottom_radiogroup.updateTabSelection(Urls[3]);
        } else if (id == R.id.button6) {
            // 选中1
            bottom_radiogroup.updateTabSelection(1);

        } else if (id == R.id.button7) {
            //清除缓存
            NavigatorIconService.getInstance().clearImageDiskCache(this);
            NavigatorIconService.getInstance().clearImageMemoryCache(this);
        } else if (id == R.id.button8) {
            bottom_radiogroup.setTipMsg(Urls[3], 1);

        } else if (id == R.id.button9) {
            bottom_radiogroup.setTipMsg(Urls[3], 99);

        } else if (id == R.id.button10) {
            bottom_radiogroup.setTip(Urls[3]);

        } else if (id == R.id.button11) {
            bottom_radiogroup.clearTipMessage(Urls[3]);
        } else if (id == R.id.button12) {
            bottom_radiogroup.setTipMsg(Urls[3], 999);
        } else if (id == R.id.button13) {
            TabBean tabBean = new TabBean("替换", R.drawable.app_icon_shopcart_selected, R.drawable.app_icon_shopcart_normal, "shopcart");
            tabBean.isReplace=true;
            bottom_radiogroup.replaceItem(2, tabBean);
        } else if (id == R.id.button14) {
            TabBean tabBean = new TabBean("替换", R.drawable.app_icon_shopcart_selected, R.drawable.app_icon_shopcart_normal, "shopcart");
            tabBean.isReplace=true;
            bottom_radiogroup.replaceItem(Urls[3], tabBean);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

    }


}
