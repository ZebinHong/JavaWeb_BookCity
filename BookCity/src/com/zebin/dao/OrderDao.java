package com.zebin.dao;

import com.zebin.pojo.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao {
    /**
     *  保存订单
     * @param order
     * @return
     */
    public int  saveOrder(Order order);

    /**
     * 查询所有订单
     * @return
     */
    public List<Order> queryOrders();

    /**
     * 改变订单状态
     * @param orderId
     * @param status
     */
    public void changeOrderStatus(String orderId,int status);

    /**
     * 通过userId查询订单
     * @param userId
     * @return
     */
    public List<Order> queryOrderByUserId(int userId);
}
