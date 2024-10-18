package com.abc.agrios.market;

import java.util.List;

public class ApiResponse {
    private String title;
    private String desc;
    private int total;
    private int count;
    private List<com.abc.agrios.market.CommodityPrice> records;

    // Getters
    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public int getTotal() {
        return total;
    }

    public int getCount() {
        return count;
    }

    public List<CommodityPrice> getRecords() {
        return records;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setRecords(List<CommodityPrice> records) {
        this.records = records;
    }
}
