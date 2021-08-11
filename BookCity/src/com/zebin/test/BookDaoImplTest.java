package com.zebin.test;

import com.zebin.dao.impl.BookDaoImpl;
import com.zebin.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoImplTest {
    private BookDaoImpl bdi = new BookDaoImpl();
//    @Test
//    public void addBook() {
//        int i = bdi.addBook(new Book(1, "学java从入门到入土", "hzb", new BigDecimal(900.1), 100, 200, null));
//        System.out.println(i);
//    }
//
//    @Test
//    public void deleteBookById() {
//        int i = bdi.deleteBookById(19);
//        System.out.println(i);
//
//    }
//
//    @Test
//    public void updateBook() {
//        int i = bdi.updateBook(new Book(21, "学java从入门到入土", "hhh", new BigDecimal(900.1), 100, 200, null));
//        System.out.println(i);
//    }
//
//    @Test
//    public void queryBookById() {
//        Book book = bdi.queryBookById(21);
//        System.out.println(book);
//    }
//
//    @Test
//    public void queryBooks() {
//        List<Book> books = bdi.queryBooks();
//        for(Book b:books){
//            System.out.println(b);
//        }
//    }
}