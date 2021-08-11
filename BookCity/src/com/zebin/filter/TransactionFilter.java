package com.zebin.filter;

import com.zebin.dao.impl.BaseDao;
import com.zebin.utils.JdbcUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,ServletResponse  servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
            JdbcUtils.closeAndCommit();//提交事务
        } catch (Exception e) {
            JdbcUtils.closeAndRollback();//回滚事务
            e.printStackTrace();
            //注意，要抛出异常，否则跳转不到error500等异常页面
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
