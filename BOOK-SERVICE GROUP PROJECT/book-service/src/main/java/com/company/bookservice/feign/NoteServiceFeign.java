package com.company.bookservice.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "note-service")
public interface NoteServiceFeign {

    @GetMapping("/notes/{id}")
    String getNoteById(@PathVariable int id);

    @GetMapping("/notes")
    String getAllNotes();

    @GetMapping("/notes/book/{bookId}")
    String getNotesByBookId(@PathVariable int bookId);

}
