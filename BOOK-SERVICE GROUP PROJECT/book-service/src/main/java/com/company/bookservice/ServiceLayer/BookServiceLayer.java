package com.company.bookservice.ServiceLayer;

import com.company.bookservice.dao.BookDao;
import com.company.bookservice.feign.NoteServiceFeign;
import com.company.bookservice.model.Book;
import com.company.bookservice.model.Note;
import com.company.bookservice.viewmodels.BookServiceViewModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookServiceLayer {

    @Autowired
    BookDao bookDao;

    @Autowired
    private final NoteServiceFeign noteServiceFeign;

    ObjectMapper mapper = new ObjectMapper();

    public static final String EXCHANGE = "note-exchange";
    public static final String ROUTING_KEY_CREATE = "note.queue.create";
    public static final String ROUTING_KEY_UPDATE = "note.queue.update";
    public static final String ROUTING_KEY_DELETE = "note.queue.delete";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public BookServiceLayer(NoteServiceFeign noteServiceFeign, RabbitTemplate rabbitTemplate){
        this.noteServiceFeign = noteServiceFeign;
        this.rabbitTemplate = rabbitTemplate;}


    public Note getNoteById(int id) throws IOException {
        Note note = mapper.readValue(noteServiceFeign.getNoteById(id), Note.class);
        return note;
    }

    public List<Note> getNotesByBookId(int id) throws IOException {
        List<Note> noteList = mapper.readValue(noteServiceFeign.getNotesByBookId(id),  new TypeReference<List<Note>>(){});
        return noteList;
    }

    public List<Note> getAllNotes() throws IOException {
        List<Note> noteList = mapper.readValue(noteServiceFeign.getAllNotes(),  new TypeReference<List<Note>>(){});
        System.out.println(noteList);
        return noteList;
    }

    public void createNote(Note note){
        rabbitTemplate.convertAndSend(EXCHANGE,ROUTING_KEY_CREATE,note);
    }

    public void updateNote(Note note){
        rabbitTemplate.convertAndSend(EXCHANGE,ROUTING_KEY_UPDATE,note);
    }

    public void deleteNote(int id){
        rabbitTemplate.convertAndSend(EXCHANGE,ROUTING_KEY_DELETE,id);
    }

    public BookServiceViewModel createBook(BookServiceViewModel bvm) throws IOException {
            Book book = new Book();
            book.setAuthor(bvm.getAuthor());
            book.setTitle(bvm.getTitle());
            bookDao.save(book);
            return createViewModel(book);
    }

    public BookServiceViewModel getBook(int id) throws IOException {
        return createViewModel(bookDao.getOne(id));
    }

    public List<BookServiceViewModel> getAllBooks() throws IOException {
        List<Book> bookList = bookDao.findAll();
        List<BookServiceViewModel> bvmList = new ArrayList<>();
        for (Book book:
             bookList) {
            bvmList.add(createViewModel(book));
        }
        return bvmList;
    }

    public void updateBook(BookServiceViewModel bvm){
        Book book = new Book();
        book.setBookId(bvm.getBookId());
        book.setTitle(bvm.getTitle());
        book.setAuthor(bvm.getAuthor());
        bookDao.save(book);
    }

    public void deleteBook(int id){
        bookDao.deleteById(id);
    }


    public BookServiceViewModel createViewModel(Book book) throws IOException {
        BookServiceViewModel bvm = new BookServiceViewModel();
        bvm.setBookId(book.getBookId());
        bvm.setAuthor(book.getAuthor());
        bvm.setTitle(book.getTitle());
        bvm.setNoteList(getNotesByBookId(book.getBookId()));
        return bvm;
    }
}
