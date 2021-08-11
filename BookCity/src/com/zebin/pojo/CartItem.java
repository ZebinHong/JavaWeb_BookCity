package com.zebin.pojo;

import java.math.BigDecimal;

public class CartItem {
    private int id; //商品id
    private String name;//商品名称
    private int count;  //商品数量
    private BigDecimal price;   //单件价格
    private BigDecimal totalPrice;  //该商品总价格=单件价格*商品数量

    public CartItem() {
    }

    public CartItem(int id, String name, int count, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.totalPrice = this.price.multiply(new BigDecimal(this.count));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        this.totalPrice = this.price.multiply(new BigDecimal(this.count));
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
