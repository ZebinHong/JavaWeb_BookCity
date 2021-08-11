package com.zebin.web;

import com.zebin.pojo.Book;
import com.zebin.pojo.Page;
import com.zebin.service.impl.BookServiceImpl;
import com.zebin.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ClientBookServlet extends BaseServlet{
    private BookServiceImpl bsi = new BookServiceImpl();

    /**
     * 获取页面数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //为了转换的时候不抛异常，给值为null的置默认值
        String pageNo1 = (req.getParameter("pageNo")==null)?"1":req.getParameter("pageNo");
        String pageSize1 = (req.getParameter("pageSize")==null)?"4":req.getParameter("pageSize");
        //转换
        int pageNo = WebUtils.parseInt(pageNo1,1);
        int pageSize =WebUtils.parseInt(pageSize1, Page.PAGE_SIZE);
        //调用函数
        Page<Book> page = bsi.page(pageNo, pageSize);
        //设置请求条的地址
        String url = "client/bookServlet?action=page";
        page.setUrl(url);
        //设置到request域中
        req.setAttribute("page",page);
        //请求转发
        req.getRequestDispatcher("/pages/client/book_clientmanager.jsp").forward(req,resp);
    }

    /**
     * 通过价格筛选获取页面数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //为了转换的时候不抛异常，给值为null的置默认值
        String pageNo1 = (req.getParameter("pageNo")==null)?"1":req.getParameter("pageNo");
        String pageSize1 = (req.getParameter("pageSize")==null)?"4":req.getParameter("pageSize");
        //转换
        int pageNo = WebUtils.parseInt(pageNo1,1);
        int pageSize =WebUtils.parseInt(pageSize1, Page.PAGE_SIZE);

        //获取min，max参数
        int min = WebUtils.parseInt(req.getParameter("min"),0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);

        //调用函数
        Page<Book> page = bsi.pageByPrice(pageNo, pageSize,min,max);

        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        if(req.getParameter("min")!=null){
            sb.append("&min=").append(req.getParameter("min"));
        }
        if(req.getParameter("max")!=null){
            sb.append("&max=").append(req.getParameter("max"));
        }
        //设置请求条的地址
        page.setUrl(sb.toString());
        //设置到request域中
        req.setAttribute("page",page);
        //请求转发
        req.getRequestDispatcher("/pages/client/book_clientmanager.jsp").forward(req,resp);
    }

    /**
     * 注销账户
     * @param req
     * @param resp
     */
    public void loginOut(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        //注销session
        req.getSession().invalidate();
        //重定向
        resp.sendRedirect(req.getContextPath()+"/index.jsp");
    }
}
