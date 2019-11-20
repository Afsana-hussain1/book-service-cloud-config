package com.company.bookservice.dao;

import com.company.bookservice.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class BookDaoTest {

    @Autowired
    BookDao bookDao;

    @Before
    public void setUp(){
        List<Book>bookList = bookDao.findAll();

        for (Book book:bookList) {
            bookDao.deleteById(book.getBookId());

        }

    }

    @Test
    public void  createGetGetAllDeleteBookTest(){
        Book book = new Book();
        book.setTitle("whatever");
        book.setAuthor("rl stine");

        bookDao.save(book);

        assertEquals(book, bookDao.getOne(book.getBookId()));
        bookDao.deleteById(book.getBookId());
        assertEquals(0,bookDao.findAll().size());
    }


    @Test
    public void updateBook(){
        Book book = new Book();
        book.setTitle("anything");
        book.setAuthor("a guy");

        bookDao.save(book);

        book.setTitle("new book");

        bookDao.save(book);

        assertEquals(book,bookDao.getOne(book.getBookId()));

    }






}