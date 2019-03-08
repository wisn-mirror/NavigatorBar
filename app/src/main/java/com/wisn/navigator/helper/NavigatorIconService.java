package com.wisn.navigator.helper;

import android.content.Context;
import android.os.Looper;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.wisn.navigator.bean.TabBean;

import java.io.File;
import java.util.List;

/**
 * Created by Wisn on 2019/3/8 下午2:55.
 */
public class NavigatorIconService {
    public static NavigatorIconService navigatorIconService;

    public static NavigatorIconService getInstance() {

        if (navigatorIconService == null) {
            synchronized (NavigatorIconService.class) {
                if (navigatorIconService == null) {
                    navigatorIconService = new NavigatorIconService();
                }
            }
        }
        return navigatorIconService;
    }

    public void download(final Context context, final List<TabBean> data) {
        if (data == null) return;
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (TabBean tabBean : data) {
                    try {
                        FutureTarget<File> target = Glide.with(context.getApplicationContext())
                                .load(tabBean.srcUsed)
                                .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
                        File imageFile = target.get();
                        Log.d("NavigatorIconService", " tabBean.srcUsed 下载成功:" + imageFile.getAbsolutePath());
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("NavigatorIconService", " tabBean.srcUsed 下载成功:" + tabBean.srcUsed);

                    }
                    try {
                        FutureTarget<File> target2 = Glide.with(context.getApplicationContext())
                                .load(tabBean.src)
                                .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
                        File imageFile2 = target2.get();
                        Log.d("NavigatorIconService", " tabBean.src 下载成功:" + imageFile2.getAbsolutePath());
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("NavigatorIconService", " tabBean.src 下载失败:" + tabBean.src);

                    }
                }
            }
        }).start();

    }

    public void checkDownload(final Context context, final List<TabBean> data , final CheckDownloadCallBack checkDownloadCallBack) {
        //todo  应该在thread中去做 callback的方式
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (data == null) {
                   if(checkDownloadCallBack!=null ) checkDownloadCallBack.error(" check data is null ");
                }
                RequestOptions requestOptions = new RequestOptions().onlyRetrieveFromCache(true);

                for (TabBean tabBean : data) {
                    try {
                        File selectUrl = Glide.with(context).downloadOnly().load(tabBean.srcUsed).apply(requestOptions).submit().get();
                        if (selectUrl == null || !selectUrl.exists()) {
                            Log.d("NavigatorIconService", " tabBean.srcUsed 下载不成功:" + tabBean.srcUsed);
//                    return false;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("NavigatorIconService", " tabBean.srcUsed 下载不成功:" + tabBean.srcUsed);
//                return false;
                    }
                    try {
                        File unSelectUrl = Glide.with(context).downloadOnly().load(tabBean.src).apply(requestOptions).submit().get();
                        if (unSelectUrl == null || !unSelectUrl.exists()) {
                            Log.d("NavigatorIconService", " tabBean.src 下载不成功:" + tabBean.src);
//                    return false;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("NavigatorIconService", " tabBean.src 下载不成功:" + tabBean.src);
//                return false;
                    }
                }
                Log.d("NavigatorIconService", " 全部下载成功");
                if(checkDownloadCallBack!=null ) checkDownloadCallBack.allDownloadSuccess();

            }
        }).start();

    }


    /**
     * 清除图片磁盘缓存
     */
    public void clearImageDiskCache(final Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(context).clearDiskCache();
                    }
                }).start();
            } else {
                Glide.get(context).clearDiskCache();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清除图片内存缓存
     */
    public void clearImageMemoryCache(Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
                Glide.get(context).clearMemory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public interface CheckDownloadCallBack{
        void allDownloadSuccess();
        void error(String msg);
    }


}
