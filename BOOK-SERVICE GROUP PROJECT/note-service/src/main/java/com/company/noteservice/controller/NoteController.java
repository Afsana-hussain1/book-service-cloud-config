package com.company.noteservice.controller;

import com.company.noteservice.NoteServiceApplication;
import com.company.noteservice.dao.NoteDao;
import com.company.noteservice.model.Note;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/notes")
public class  NoteController {

    @Autowired
    NoteDao noteDao;

    @GetMapping( "/{id}")
    public Note getNoteById(@PathVariable int id){
        return noteDao.getOne(id);
    }

    @GetMapping("/book/{bookId}")
    public List<Note> getNoteByBookId(@PathVariable int bookId){
        return noteDao.getNoteByBookId(bookId);
    }

    @GetMapping("")
    public List<Note> getAllNotes(){
        return noteDao.findAll();
    }

    @PostMapping("")
    @RabbitListener(queues = NoteServiceApplication.QUEUE_NAME_CREATE)
    public void createNote(Note note){
       noteDao.save(note);
    }

    @PutMapping("")
    @RabbitListener(queues = NoteServiceApplication.QUEUE_NAME_UPDATE)
    public void updateNote(Note note){
        noteDao.save(note);
    }

    @DeleteMapping("/{id}")
    @RabbitListener(queues = NoteServiceApplication.QUEUE_NAME_DELETE)
    public void deleteNote(int id){
        noteDao.deleteById(id);
    }

}
