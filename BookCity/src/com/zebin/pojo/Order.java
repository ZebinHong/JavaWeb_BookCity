package com.zebin.pojo;

import java.math.BigDecimal;
import java.sql.Date;

public class Order {
    private String id;
    private Date dateTime;
    private BigDecimal price;
    private int status;// 0 未发货，1 已发货，2 表示已签收
    private int userId;

    public Order() {
    }

    public Order(String id, Date dateTime, BigDecimal price, int status, int userId) {
        this.id = id;
        this.dateTime = dateTime;
        this.price = price;
        this.status = status;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", dateTime=" + dateTime +
                ", price=" + price +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }
}
