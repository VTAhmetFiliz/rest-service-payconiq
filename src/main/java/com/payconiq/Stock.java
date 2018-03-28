package com.payconiq;

import java.util.Date;

public class Stock {

    private long id;
    private String name;
    private int currentPrice;
    private Date lastUpdate;

    public Stock(long id, String name, int currentPrice) {
        this.id = id;
        this.name = name;
        this.currentPrice = currentPrice;
        lastUpdate = new Date();
    }

    public Stock() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
