package com.zebin.web;

import com.google.gson.Gson;
import com.zebin.pojo.Book;
import com.zebin.pojo.Cart;
import com.zebin.pojo.CartItem;
import com.zebin.service.impl.BookServiceImpl;
import com.zebin.utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet{
    BookServiceImpl bsi = new BookServiceImpl();

    public void addItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Book book = bsi.queryBookById(id);
        Cart cart =(Cart) req.getSession().getAttribute("cart");
        if(cart==null){
            //若session域中没有cart，则创建一个
            cart = new Cart();
            //放到session域中
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(new CartItem(book.getId(),book.getName(),1,book.getPrice()));
        //将最近的名字置到session域中
        req.getSession().setAttribute("current",book.getName());
        //重新回到原来的页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    public void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Book book = bsi.queryBookById(id);
        Cart cart =(Cart) req.getSession().getAttribute("cart");
        if(cart==null){
            //若session域中没有cart，则创建一个
            cart = new Cart();
            //放到session域中
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(new CartItem(book.getId(),book.getName(),1,book.getPrice()));
        Map<String,Object> map = new HashMap<>();
        Gson gson = new Gson();
        map.put("lastName",book.getName());
        map.put("totalCount",cart.getTotalCount());
        String jsonStr = gson.toJson(map);
        resp.getWriter().write(jsonStr);
    }

    public void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if(cart!=null){
            cart.deleteItem(id);
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }

    public void clear(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if(cart!=null){
            cart.clear();
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }
    public void updateCount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 0);
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if(cart!=null){
            cart.updateCount(id,count);
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
