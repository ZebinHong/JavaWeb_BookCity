package com.zebin.dao.impl;

import com.zebin.utils.JdbcUtils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {
    private QueryRunner qr = new QueryRunner();

    /**
     * 更新数据库
     * @param sql
     * @param args
     * @return 返回的数字表示查询的行数；0表示查询不到或者出错
     */
    public int update(String sql,Object ...args) {
        Connection conn = null;
        try {
            conn = JdbcUtils.getConnection();
            return qr.update(conn, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询一条记录
     * @param type
     * @param sql
     * @param args
     * @param <T>
     * @return 查询到返回对象，查询不到返回null，
     */
    public <T> T queryForOne(Class<T> type,String sql,Object ...args) {
        Connection conn = null;
        try {
            conn = JdbcUtils.getConnection();
            return qr.query(conn,sql, new BeanHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询多条记录
     * @param type
     * @param sql
     * @param <T>
     * @return 查询到返回对象列表，查询不到返回null，
     */
    public <T> List<T> queryForList(Class<T> type,String sql,Object...args) {
        Connection conn = null;
        try {
            conn = JdbcUtils.getConnection();
            return qr.query(conn,sql, new BeanListHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public Object queryForSingleValue(String sql,Object ...args) {
        Connection conn = null;
        try {
            conn = JdbcUtils.getConnection();
            return qr.query(conn,sql, new ScalarHandler(), args);
        } catch (SQLException e) {
            e.printStackTrace();
            //这里为什么抛出RuntimeException呢？原因是想让JVM处理该异常。
            //Exception和RuntimeException的区别在于：后者不需要处理，直接抛给JVM去处理也可以的,同时抛出的时候不同声明。而前者不同。需要try-catch处理。
            throw new RuntimeException(e); // 把异常抛给Filter 过滤器
        }
    }
}
