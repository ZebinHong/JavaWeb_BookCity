package com.zebin.test;

import com.zebin.pojo.Cart;
import com.zebin.pojo.CartItem;
import com.zebin.pojo.Order;
import com.zebin.pojo.OrderItem;
import com.zebin.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class OrderServiceImplTest {
    OrderServiceImpl osi = new OrderServiceImpl();
//    @Test
//    public void createOrder() {
//        Cart cart = new Cart();
//        cart.addItem(new CartItem(1,"wgj",1,new BigDecimal(10)));
//        cart.addItem(new CartItem(2,"wgj",1,new BigDecimal(10)));
//        cart.addItem(new CartItem(2,"wgj",1,new BigDecimal(10)));
//        String orderId = osi.createOrder(cart, 1);
//        System.out.println(orderId);
//    }
//    @Test
//    public void TestAll(){
//        List<Order> orders = osi.showAllOrders();
//        System.out.println(orders);
//        List<OrderItem> orderItems = osi.showOrderDetail("16281576419981");
//        System.out.println(orderItems);
//    }
}