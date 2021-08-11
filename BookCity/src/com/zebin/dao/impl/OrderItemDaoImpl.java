package com.zebin.dao.impl;

import com.zebin.dao.OrderItemDao;
import com.zebin.pojo.OrderItem;

import java.sql.SQLException;
import java.util.List;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {

    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql="insert into t_order_item(id,name,count,price,total_price,order_id) values(?,?,?,?,?,?)";
        int update = update(sql, orderItem.getId(), orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrderId());
        return update;
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {
        String sql = "select id,name,count,price,total_price totalPrice,order_id orderId from t_order_item where order_id=?";
        return queryForList(OrderItem.class,sql,orderId);
    }
}
