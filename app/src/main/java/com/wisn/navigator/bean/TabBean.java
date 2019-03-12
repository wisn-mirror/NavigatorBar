package com.wisn.navigator.bean;

/**
 * Created by Wisn on 2019/3/8 下午2:33.
 */
public class TabBean {
    /**
     * text : 首页
     * srcUsed : https://static3.laiyifen.com/edu-files/cms/image/1547965147229_1478.png
     * link : {"status":true,"data":"http://lyf://mainPage","appData":"http://lyf://mainPage","type":"自定义|http://lyf://mainPage"}
     * src : https://static2.laiyifen.com/edu-files/cms/image/1547965142658_3937.png
     * url : http://lyf://mainPage
     */

    public String text;
    public String srcUsed;
    public LinkBean link;
    public String src;
    public String url;
    public int[] resid;
    public int __position;
    public boolean isReplace;

    public TabBean(String text, String srcUsed, String src, String url) {
        this.text = text;
        this.srcUsed = srcUsed;
        this.src = src;
        this.url = url;
    }

    /**
     * @param text
     * @param srcUsed 选中id
     * @param src     未选中id
     * @param url
     */
    public TabBean(String text, int srcUsed, int src, String url) {
        this.text = text;
        resid = new int[2];
        resid[0] = src;
        resid[1] = srcUsed;
        this.url = url;
    }

    public static class LinkBean {
        /**
         * status : true
         * data : http://lyf://mainPage
         * appData : http://lyf://mainPage
         * type : 自定义|http://lyf://mainPage
         */

        public boolean status;
        public String data;
        public String appData;
        public String type;
    }
}
