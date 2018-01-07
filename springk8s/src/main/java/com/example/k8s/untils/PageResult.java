package com.example.k8s.untils;

import java.util.List;

/**
 * 分页数据返回
 * @param <T>
 */
public class PageResult<T> {

    private long recordsTotal;
    private List<T> items;

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "recordsTotal=" + recordsTotal +
                ", items=" + items +
                '}';
    }
}
