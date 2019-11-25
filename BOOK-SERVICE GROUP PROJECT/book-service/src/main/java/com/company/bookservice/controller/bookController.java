package com.company.bookservice.controller;


import com.company.bookservice.ServiceLayer.BookServiceLayer;
import com.company.bookservice.model.Book;
import com.company.bookservice.model.Note;
import com.company.bookservice.viewmodels.BookServiceViewModel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RefreshScope
public class bookController {

    @Autowired
    BookServiceLayer bookServiceLayer;


    public bookController(BookServiceLayer bookServiceLayer){
        this.bookServiceLayer = bookServiceLayer;
    }

    @GetMapping( "/notes/{id}")
    public Note getNoteById(@PathVariable int id) throws IOException {
        return bookServiceLayer.getNoteById(id);
    }

    @GetMapping("/notes/book/{bookId}")
    public List<Note> getNoteByBookId(@PathVariable int bookId) throws IOException {
        return bookServiceLayer.getNotesByBookId(bookId);
    }

    @GetMapping("/notes")
    public List<Note> getAllNotes() throws IOException {
        return bookServiceLayer.getAllNotes();
    }

    @PostMapping("/notes")
    public void createNote(@RequestBody Note note){
        bookServiceLayer.createNote(note);
    }

    @PutMapping("/notes")
    public void updateNote(@RequestBody Note note){
        bookServiceLayer.updateNote(note);
    }

    @DeleteMapping("/notes/{id}")
    public void deleteNote(@PathVariable int id){
        bookServiceLayer.deleteNote(id);
    }


    @GetMapping( "/books/{id}")
    public BookServiceViewModel getBookById(@PathVariable int id) throws IOException {
        return bookServiceLayer.getBook(id);
    }

    @GetMapping("/books")
    public List<BookServiceViewModel> getAllBooks() throws IOException {
        return bookServiceLayer.getAllBooks();
    }

    @PostMapping("/books")
    public BookServiceViewModel createBook(@RequestBody BookServiceViewModel bookServiceViewModel) throws IOException {
        return bookServiceLayer.createBook(bookServiceViewModel);
    }

    @PutMapping("/books")
    public void updateBook(@RequestBody BookServiceViewModel bookServiceViewModel) throws IOException {
            bookServiceLayer.updateBook(bookServiceViewModel);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable int id){
        bookServiceLayer.deleteBook(id);
    }
}
