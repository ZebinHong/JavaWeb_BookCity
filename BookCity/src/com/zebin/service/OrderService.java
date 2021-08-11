package com.zebin.service;

import com.zebin.pojo.Cart;
import com.zebin.pojo.Order;
import com.zebin.pojo.OrderItem;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {
    /**
     * 创建订单
     * @param cart
     * @param userId
     * @return
     */
    public String createOrder(Cart cart,int userId);

    /**
     * 展示所有的订单
     */
    public List<Order> showAllOrders();

    /**
     * 发货
     * @param orderId
     */
    public void sendOrder(String orderId);

    /**
     * 展示订单详情
     * @param orderId
     */
    public  List<OrderItem> showOrderDetail(String orderId);

    /**
     * 展示我的订单
     * @param userId
     * @return
     */
    public List<Order> showMyOrder(int userId);

    /**
     * 收货
     * @param orderId
     */
    public void receiveOrder(String orderId);
}
