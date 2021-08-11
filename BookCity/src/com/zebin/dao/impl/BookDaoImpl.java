package com.zebin.dao.impl;

import com.zebin.dao.BookDao;
import com.zebin.pojo.Book;
import com.zebin.pojo.Page;
import com.zebin.utils.WebUtils;

import java.sql.SQLException;
import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(`name`,author,price,sales,stock,img_path) values(?,?,?,?,?,?)";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());
    }

    @Override
    public int deleteBookById(int id) {
        String sql = "delete from t_book where id=?";
        return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set name=?,price=?,author=?,sales=?,stock=?,img_path=? where id=?";
        return update(sql,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
    }

    @Override
    public Book queryBookById(int id) {
        String sql = "select * from t_book where id=?";
        return queryForOne(Book.class, sql, id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select * from t_book ";
        return queryForList(Book.class,sql);
    }

    @Override
    public int queryForPageTotalDataCount() {
        String sql = "select count(*) from t_book";
        Number count = (Number)queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Book> queryForItem(int begin, int pageSize) {
        String sql = "select * from t_book limit ?,?";
        return queryForList(Book.class,sql,begin,pageSize);
    }

    @Override
    public int queryTotalDataCountByPrice(int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Number count = (Number)queryForSingleValue(sql, min, max);
        return count.intValue();
    }

    @Override
    public List<Book> queryForItemByPrice(int pageNo, int pageSize, int min, int max) {
        String sql = "select * from t_book where price between ? and ? limit ?,?";
        return queryForList(Book.class, sql, min, max, pageNo, pageSize);
    }


}
