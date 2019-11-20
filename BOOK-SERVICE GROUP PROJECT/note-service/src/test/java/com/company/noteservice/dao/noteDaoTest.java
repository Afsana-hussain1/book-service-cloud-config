package com.company.noteservice.dao;

import com.company.noteservice.model.Note;
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
public class noteDaoTest {

    @Autowired
    NoteDao noteDao;

    @Before
    public void setUp(){
        List<Note> noteList = noteDao.findAll();

        for (Note note:noteList) {
            noteDao.deleteById(note.getBookId());

        }

    }
    @Test
    public void  createGetGetAllDeleteNoteTest(){
        Note note = new Note();
        note.setNote("note");
        note.setBookId(2);

        noteDao.save(note);

        assertEquals(note, noteDao.getOne(note.getNoteId()));
        noteDao.deleteById(note.getNoteId());
        assertEquals(0,noteDao.findAll().size());
    }


    @Test
    public void updateNote(){
        Note note = new Note();
        note.setNote("note");
        note.setBookId(2);

        noteDao.save(note);

        note.setBookId(1);

        noteDao.save(note);

        assertEquals(note, noteDao.getOne(note.getNoteId()));
    }


    @Test
    public void getNoteByBookId(){
        Note note = new Note();
        note.setNote("note");
        note.setBookId(1);

        noteDao.save(note);

        assertEquals(note, noteDao.getOne(note.getBookId()));


    }






}