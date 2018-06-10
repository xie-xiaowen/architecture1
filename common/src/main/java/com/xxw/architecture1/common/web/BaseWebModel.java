package com.xxw.architecture1.common.web;

public class BaseWebModel {

    private String queryJsonStr = ""; // 查询JSON 字符串
    private int nowPage = 1;  // 当前页数
    private int pageShow = 0; // 每页显示条数

    public String getQueryJsonStr() {
        return queryJsonStr;
    }

    public void setQueryJsonStr(String queryJsonStr) {
        this.queryJsonStr = queryJsonStr;
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public int getPageShow() {
        return pageShow;
    }

    public void setPageShow(int pageShow) {
        this.pageShow = pageShow;
    }

    @Override
    public String toString() {
        return "BaseWebModel{" +
                "queryJsonStr='" + queryJsonStr + '\'' +
                ", nowPage=" + nowPage +
                ", pageShow=" + pageShow +
                '}';
    }
}
