package com.xxw.pageutil;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class Page<E> implements Serializable {

    private int pageShow = 2; // 每页显示条数
    private int totalPage;    // 总页数
    private int totalCount;   // 总条数
    private int start;        // 开始条数
    private int nowPage;      // 当前页数
    private List<E> result = Collections.emptyList();  // 最终返回数据集合


    public int getStart() {
        start = (getNowPage()-1)*getPageShow();
        if(start < 0){
            start = 0;
        }
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }
    public int getPageShow() {
        return pageShow;
    }

    public void setPageShow(int pageShow) {
        this.pageShow = pageShow;
    }

    public int getTotalPage() {
        return (int)Math.ceil(totalCount*1.0/pageShow);
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getNowPage() {
        if(nowPage <= 0){
            nowPage = 1;
        }
        if (nowPage > getTotalPage()){
            nowPage = getTotalPage();
        }
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public List<E> getResult() {
        return result;
    }

    public void setResult(List<E> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageShow=" + pageShow +
                ", totalPage=" + totalPage +
                ", totalCount=" + totalCount +
                ", start=" + start +
                ", nowPage=" + nowPage +
                ", result=" + result +
                '}';
    }
}
