package com.zebin.pojo;

import java.util.List;

public class Page<T> {
    public final static int PAGE_SIZE =4; //页面大小
    private int pageNo; //当前页码
    private int pageSize = PAGE_SIZE;
    private int pageTotalDataCount;  //总记录数
    private int pageTotal;  //总页码
    private List<T> items; //当前页面数据
    private String url;//分页条的请求地址

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public Page() {
    }

    public Page(int pageNo, int pageSize, int pageTotalDataCount, int pageTotal, List<T> items) {
        /* 数据边界的有效检查*/
        if (pageNo < 1) {
            pageNo = 1;
        }
        if (pageNo > pageTotal) {
            pageNo = pageTotal;
        }
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.pageTotalDataCount = pageTotalDataCount;
        this.pageTotal = pageTotal;
        this.items = items;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        /* 数据边界的有效检查*/
        if (pageNo < 1) {
            pageNo = 1;
        }
        if (pageNo > this.pageTotal) {
            pageNo = this.pageTotal;
        }
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageTotalDataCount() {
        return pageTotalDataCount;
    }

    public void setPageTotalDataCount(int pageTotalDataCount) {
        this.pageTotalDataCount = pageTotalDataCount;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", pageTotalDataCount=" + pageTotalDataCount +
                ", pageTotal=" + pageTotal +
                ", items=" + items +
                '}';
    }
}
