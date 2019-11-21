package com.company.noteservice.controller;

import com.company.noteservice.dao.NoteDao;
import com.company.noteservice.model.Note;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
@WebMvcTest(NoteController.class)
public class NoteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    NoteDao noteDao;

    ObjectMapper Mapper = new ObjectMapper();

    Note note1 = new Note();
    Note note2 = new Note();

    @Before
    public void setUp(){
        note1.setNote("Note1");
        note1.setBookId(1);
        note1.setNoteId(1);

        note2.setNote("Note1");
        note2.setBookId(1);

        setUpDaoMocks();
    }

    public void setUpDaoMocks(){
        List<Note> noteList = new ArrayList<>();

        noteList.add(note1);

        doReturn(note1).when(noteDao).save(note2);
        doReturn(noteList).when(noteDao).findAll();
        doReturn(noteList).when(noteDao).getNoteByBookId(1);
        doReturn(note1).when(noteDao).getOne(1);

    }

    @Test
    public void shouldCreate() throws Exception {

        String input = Mapper.writeValueAsString(note2);

        String output = Mapper.writeValueAsString(note1);

        mockMvc.perform(post("/notes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(input)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(output)
                );

    }

    @Test
    public void shouldGetNoteByBookId() throws Exception {

        List<Note> noteList = new ArrayList<>();

        noteList.add(note1);

        String output = Mapper.writeValueAsString(noteList);

        mockMvc.perform(get("/notes/book/1")
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(output)
                );


    }

    @Test
    public void shouldGetNoteById() throws Exception {

        String output = Mapper.writeValueAsString(note1);

        mockMvc.perform(get("/notes/1")
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(output)
                );
    }

    @Test
    public void shouldGetAllNotes() throws Exception {

        List<Note> noteList = new ArrayList<>();

        noteList.add(note1);

        String output = Mapper.writeValueAsString(noteList);

        mockMvc.perform(get("/notes")
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(output)
                );

    }
}
