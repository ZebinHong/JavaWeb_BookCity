package com.zebin.dao;

import com.zebin.pojo.Book;
import com.zebin.pojo.Page;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {
    public int addBook(Book book);
    public int deleteBookById(int id);
    public int updateBook(Book book);
    public Book queryBookById(int id);
    public List<Book> queryBooks();
    public int queryForPageTotalDataCount();
    public List<Book> queryForItem(int pageNo,int pageSize);
    public int queryTotalDataCountByPrice(int min,int max);
    public List<Book> queryForItemByPrice(int pageNo,int pageSize,int min,int max);
}
