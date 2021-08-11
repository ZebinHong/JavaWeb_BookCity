package com.zebin.web;

import com.zebin.pojo.Cart;
import com.zebin.pojo.Order;
import com.zebin.pojo.OrderItem;
import com.zebin.pojo.User;
import com.zebin.service.impl.OrderServiceImpl;
import com.zebin.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class OrderServlet extends BaseServlet {
    OrderServiceImpl osi = new OrderServiceImpl();

    /**
     * 创建订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        if(user==null){
            System.out.println("用户未登录");
            resp.sendRedirect(req.getContextPath()+"/pages/user/login.jsp");
            return;
        }
        int userId = user.getId();
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        String orderId = osi.createOrder(cart, userId);
        req.getSession().setAttribute("orderId",orderId);
        //重定向
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }
    /**
     * 查看我的订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showMyOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        if(user==null){
            System.out.println("用户未登录");
            resp.sendRedirect(req.getContextPath()+"/pages/user/login.jsp");
            return;
        }
        List<Order> orders = osi.showMyOrder(user.getId());
        req.setAttribute("orders",orders);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
    }

    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        List<OrderItem> orderItems = osi.showOrderDetail(orderId);
        int totalCount = 0;
        BigDecimal totalPrice = new BigDecimal(0);
        for(OrderItem item:orderItems){
            totalCount+=item.getCount();
            totalPrice= totalPrice.add(item.getTotalPrice());
        }
        req.setAttribute("orderItems",orderItems);
        System.out.println(orderItems);
        req.getRequestDispatcher("/pages/order/order_item.jsp?totalCount="+totalCount+"&totalPrice="+totalPrice).forward(req,resp);
    }

    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        List<Order> orders = osi.showAllOrders();
        req.setAttribute("orders",orders);
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);
    }
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String orderId = req.getParameter("orderId");
        osi.sendOrder(orderId);
        //重定向
//        resp.sendRedirect(req.getContextPath()+"/orderServlet?action=showAllOrders");这个也可以
        resp.sendRedirect(req.getHeader("Referer"));
    }
    protected void receiveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        osi.receiveOrder(orderId);
        //重定向
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
