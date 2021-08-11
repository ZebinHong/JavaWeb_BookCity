package com.zebin.dao;

import com.zebin.pojo.OrderItem;

import java.sql.SQLException;
import java.util.List;

public interface OrderItemDao {
    /**
     * 保存订单项
     * @param orderItem
     * @return
     */
    public int saveOrderItem(OrderItem orderItem);

    /**
     * 通过订单号查找订单项
     * @param orderId
     * @return
     */
    public List<OrderItem> queryOrderItemsByOrderId(String orderId);
}
