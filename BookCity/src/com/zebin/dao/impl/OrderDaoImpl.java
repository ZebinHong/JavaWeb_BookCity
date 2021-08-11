package com.zebin.dao.impl;

import com.zebin.dao.OrderDao;
import com.zebin.dao.OrderItemDao;
import com.zebin.pojo.Order;

import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {

    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(order_id,create_time,price,status,user_id) values(?,?,?,?,?)";
        int update = update(sql, order.getId(), order.getDateTime(), order.getPrice(), order.getStatus(), order.getUserId());
        return update;
    }

    @Override
    public List<Order> queryOrders() {
        String sql = "select order_id id,create_time dateTime,price,status,user_id userId from t_order";
        return queryForList(Order.class,sql);
    }

    @Override
    public void changeOrderStatus(String orderId, int status) {
        String sql = "update t_order set status=? where order_id =?";
        update(sql, status, orderId);
    }

    @Override
    public List<Order> queryOrderByUserId(int userId) {
        String sql = "select order_id id,create_time dateTime,price,status,user_id userId from t_order where user_id=?";
        return queryForList(Order.class,sql,userId);
    }
}
