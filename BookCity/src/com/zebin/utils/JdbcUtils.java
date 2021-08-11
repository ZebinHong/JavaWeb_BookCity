package com.zebin.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    private static DruidDataSource druidDataSource = null;
    private static ThreadLocal<Connection> conns= new ThreadLocal<Connection>();
    static{
        try {
            Properties properties = new Properties();
            InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(is);
            druidDataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        Connection conn = conns.get();
        if(conn==null){
            //说明未设置连接
            try {
                conn = druidDataSource.getConnection();
                conns.set(conn);
                //设置手动提交事务
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
    public static void closeAndCommit(){
        Connection conn = conns.get();
        if(conn!=null){
            try {
                //提交事务
                conn.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //记得移除conns
        conns.remove();
    }
    public static void closeAndRollback(){
        Connection conn = conns.get();
        if(conn!=null){
            try {
                //提交事务
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //记得移除conns
        conns.remove();
    }
    /**
     * 关闭连接，放回数据库连接池
     * @param conn
    public static void close(Connection conn){
    if (conn != null) {
    try {
    conn.close();
    } catch (SQLException e) {
    e.printStackTrace();
    }
    }
    } */
}
