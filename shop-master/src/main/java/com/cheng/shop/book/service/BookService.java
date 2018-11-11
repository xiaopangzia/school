package com.cheng.shop.book.service;

import com.cheng.shop.book.dao.BookDao;
import com.cheng.shop.pager.PageBean;
import com.cheng.shop.book.domain.Book;

import java.sql.SQLException;


public class BookService {
    private BookDao bookDao=new BookDao();

    //按分类查询
    public PageBean<Book> findByCategory(String cid, int pc){
        try {
            return bookDao.findByCategory(cid,pc);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //按作者模糊查询
    public PageBean<Book> findByAuthor(String author, int pc){
        try {
            return bookDao.findByAuthor(author,pc);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //按书名模糊查询
    public PageBean<Book> findByBname(String bname, int pc){
        try {
            return bookDao.findByBname(bname,pc);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //按出版社模糊查询
    public PageBean<Book> findByPress(String press, int pc){
        try {
            return bookDao.findByPress(press,pc);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //按多条件组合查询
    public PageBean<Book> findByCombination(Book criteria, int pc){
        try {
            return bookDao.findByCombination(criteria,pc);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //使用bid查询书籍详情信息
    public Book load(String bid) {
        try {
            return bookDao.findByBid(bid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 添加图书
     * @param book
     */
    public void add(Book book) {
        try {
            bookDao.add(book);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改图书信息
     * @param book
     */
    public void edit(Book book) {
        try {
            bookDao.edit(book);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除图书
     * @param bid
     */
    public void delete(String bid) {
        try {
            bookDao.delete(bid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
