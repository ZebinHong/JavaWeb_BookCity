package com.zebin.service.impl;

import com.zebin.dao.impl.BookDaoImpl;
import com.zebin.pojo.Book;
import com.zebin.pojo.Page;
import com.zebin.service.BookService;

import java.sql.SQLException;
import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDaoImpl bdi= new BookDaoImpl();
    @Override
    public void addBook(Book book) {
        bdi.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bdi.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bdi.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bdi.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bdi.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();
        //设置总记录数
        int pageTotalDataCount = bdi.queryForPageTotalDataCount();
        page.setPageTotalDataCount(pageTotalDataCount);
        //设置总页面数
        int pageTotal = pageTotalDataCount/pageSize;
        if(pageTotalDataCount%pageSize>0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);
        //设置当前页码
        page.setPageNo(pageNo);
        //当前页面起始索引
        int begin = (page.getPageNo()-1)*pageSize;
        //设置当前页面数据
        List<Book> list = bdi.queryForItem(begin,pageSize);
        page.setItems(list);
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<>();
        //设置总记录数
        int pageTotalDataCount = bdi.queryTotalDataCountByPrice(min,max);
        page.setPageTotalDataCount(pageTotalDataCount);
        //设置总页面数
        int pageTotal = pageTotalDataCount/pageSize;
        if(pageTotalDataCount%pageSize>0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);
        //设置当前页码
        page.setPageNo(pageNo);
        //当前页面起始索引
        int begin = (page.getPageNo()-1)*pageSize;
        //设置当前页面数据
        List<Book> list = bdi.queryForItemByPrice(begin,pageSize,min,max);
        page.setItems(list);
        return page;
    }
}
