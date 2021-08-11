package com.zebin.test;

import com.zebin.pojo.Cart;
import com.zebin.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CartTest {

    @Test
    public void getTotalCount() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"wgj",1,new BigDecimal(10)));
        cart.addItem(new CartItem(2,"wgj",1,new BigDecimal(10)));
        cart.addItem(new CartItem(2,"wgj",1,new BigDecimal(10)));
        int totalCount = cart.getTotalCount();
        System.out.println(totalCount);
    }

    @Test
    public void getTotalPrice() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"wgj",1,new BigDecimal(10)));
        cart.addItem(new CartItem(2,"wgj",1,new BigDecimal(10)));
        cart.addItem(new CartItem(2,"wgj",1,new BigDecimal(10)));
        BigDecimal totalPrice = cart.getTotalPrice();
        System.out.println(totalPrice);
    }


    @Test
    public void deleteItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"wgj",1,new BigDecimal(10)));
        cart.addItem(new CartItem(2,"wgj",1,new BigDecimal(10)));
        cart.addItem(new CartItem(2,"wgj",1,new BigDecimal(10)));
        cart.deleteItem(1);
        cart.deleteItem(2);
        System.out.println(cart.getTotalCount());
    }

    @Test
    public void updateCount() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"wgj",1,new BigDecimal(10)));
        cart.addItem(new CartItem(2,"wgj",1,new BigDecimal(10)));
        cart.addItem(new CartItem(2,"wgj",1,new BigDecimal(10)));
        cart.updateCount(2,3);
        System.out.println(cart.getTotalCount());
    }

    @Test
    public void clear() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"wgj",1,new BigDecimal(10)));
        cart.addItem(new CartItem(2,"wgj",1,new BigDecimal(10)));
        cart.addItem(new CartItem(2,"wgj",1,new BigDecimal(10)));
        cart.clear();
        System.out.println(cart.getTotalCount());
    }
}