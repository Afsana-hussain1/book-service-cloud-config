package com.company.bookservice.controller;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/book")
public class bookController {

    @Autowired
    NoteDao noteDao;

    @GetMapping( "/{id}")
    public Note getNoteById(@PathVariable int id){
        return null;
    }

    @GetMapping("/book/{bookId}")
    public List<Note> getNoteByBookId(@PathVariable int bookId){
        return null;
    }

    @GetMapping("")
    public List<Note> getAllNotes(){
        return null;
    }

    @PostMapping("")
    @RabbitListener(queues = NoteServiceApplication.QUEUE_NAME_CREATE)
    public Note createNote(@RequestBody Note note){
        return null;
    }

    @PutMapping("/{id}")
    @RabbitListener(queues = NoteServiceApplication.QUEUE_NAME_UPDATE)
    public void updateNote(@RequestBody Note note){

        System.out.println(note.toString());

        noteDao.save(note);
    }

    @DeleteMapping("/{id}")
    @RabbitListener(queues = NoteServiceApplication.QUEUE_NAME_DELETE)
    public void deleteNote(@PathVariable int id){
        noteDao.deleteById(id);
    }
}
