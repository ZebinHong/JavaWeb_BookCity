package com.zebin.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    private Map<Integer,CartItem> items = new LinkedHashMap<>();

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    public Cart() {
    }

    /**
     * 获取购物车总数量
     * @return
     */
    public int getTotalCount() {
        int totalCount = 0;
        for(Map.Entry<Integer,CartItem> entry:items.entrySet()){
            totalCount+=entry.getValue().getCount();
        }
        return totalCount;
    }

    /**
     * 获取购物车总价格
     * @return
     */
    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for(Map.Entry<Integer,CartItem> entry:items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

    public Cart(Map<Integer, CartItem> items) {
        this.items = items;
    }

    /**
     * 增加购物车项
     * @param cartItem
     */
    public void addItem(CartItem cartItem){
        CartItem item = items.get(cartItem.getId());
        if(item==null){
            //说明之前没添加过该商品
            items.put(cartItem.getId(),cartItem);
        }else{
            //添加过该商品
            //设置新的商品数量和商品总价格
            item.setCount(item.getCount()+1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    /**
     * 删除购物车项
     * @param id
     */
    public void deleteItem(int id){
        items.remove(id);
    }

    /**
     * 增加购物车项
     * @param id
     * @param count
     */
    public void updateCount(int id,int count){
        CartItem item = items.get(id);
        item.setCount(count);
    }

    /**
     * 清空购物车
     */
    public void clear(){
        items.clear();
    }
}
