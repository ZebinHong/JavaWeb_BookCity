package com.zebin.web;

import com.sun.deploy.net.HttpRequest;
import com.zebin.pojo.Book;
import com.zebin.pojo.Page;
import com.zebin.service.impl.BookServiceImpl;
import com.zebin.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class BookServlet extends BaseServlet {
    BookServiceImpl bsi = new BookServiceImpl();

    /**
     * 列出图书列表内容
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bsi.queryBooks();
        req.setAttribute("books",books);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    /**
     * 添加图书
     * @param req
     * @param resp
     */
    public void add(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        pageNo+=1;
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        bsi.addBook(book);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+pageNo);
    }


    /**
     *删除图书
     * @param req
     * @param resp
     */
    public void delete(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        bsi.deleteBookById(WebUtils.parseInt(id,0));
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    /**
     * 修改图书
     * @param req
     * @param resp
     */
    public void update(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        bsi.updateBook(book);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }
    /**
     * 获取图书信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void getBook(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Book book = bsi.queryBookById(id);
        req.setAttribute("book",book);
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

    public void page(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        //为了转换的时候不抛异常，给值为null的置默认值
        String pageNo1 = (req.getParameter("pageNo")==null)?"1":req.getParameter("pageNo");
        String pageSize1 = (req.getParameter("pageSize")==null)?"4":req.getParameter("pageSize");
        //转换
        int pageNo = WebUtils.parseInt(pageNo1,1);
        int pageSize =WebUtils.parseInt(pageSize1,Page.PAGE_SIZE);
        //调用函数
        Page<Book> page = bsi.page(pageNo, pageSize);
        //设置请求条的地址
        String url = "manager/bookServlet?action=page";
        page.setUrl(url);
        //设置到request域中
        req.setAttribute("page",page);
        //请求转发
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}
