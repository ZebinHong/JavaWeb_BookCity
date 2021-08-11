package com.zebin.service.impl;

import com.zebin.dao.impl.OrderDaoImpl;
import com.zebin.dao.impl.OrderItemDaoImpl;
import com.zebin.pojo.Cart;
import com.zebin.pojo.CartItem;
import com.zebin.pojo.Order;
import com.zebin.pojo.OrderItem;
import com.zebin.service.OrderService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public class OrderServiceImpl implements OrderService {
    OrderDaoImpl idi = new OrderDaoImpl();
    OrderItemDaoImpl oidi = new OrderItemDaoImpl();
    @Override
    public String createOrder(Cart cart, int userId) {
        //保存订单
        String id = System.currentTimeMillis()+""+userId;
        idi.saveOrder(new Order(id,new Date(System.currentTimeMillis()) , cart.getTotalPrice(), 0, userId));
        //测试
        //保存订单项
        Map<Integer, CartItem> items = cart.getItems();
        for(Map.Entry<Integer,CartItem> entry:items.entrySet()){
            CartItem value = entry.getValue();
            oidi.saveOrderItem(new OrderItem(value.getName(),value.getCount(),value.getPrice(),value.getTotalPrice(),id));
        }
        //清空购物车
        cart.clear();
        return id;
    }

    @Override
    public List<Order> showAllOrders() {
        return idi.queryOrders();
    }

    @Override
    public void sendOrder(String orderId) {
        idi.changeOrderStatus(orderId,1);
    }

    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        List<OrderItem> orderItems = oidi.queryOrderItemsByOrderId(orderId);
        return orderItems;
    }

    @Override
    public List<Order> showMyOrder(int userId) {
        List<Order> orders = idi.queryOrderByUserId(userId);
        return orders;
    }

    @Override
    public void receiveOrder(String orderId) {
        idi.changeOrderStatus(orderId,2);
    }
}
