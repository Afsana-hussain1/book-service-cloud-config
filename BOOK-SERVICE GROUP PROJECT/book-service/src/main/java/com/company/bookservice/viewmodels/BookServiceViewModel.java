package com.company.bookservice.viewmodels;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class BookServiceViewModel {

    private int bookId;
    private String title;
    private String author;
    private List<NoteViewModel> noteVMList = new ArrayList<>();

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<NoteViewModel> getNoteVMList() {
        return noteVMList;
    }

    public void setNoteVMList(List<NoteViewModel> noteVMList) {
        this.noteVMList = noteVMList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookServiceViewModel)) return false;
        BookServiceViewModel that = (BookServiceViewModel) o;
        return bookId == that.bookId &&
                title.equals(that.title) &&
                author.equals(that.author) &&
                noteVMList.equals(that.noteVMList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, title, author, noteVMList);
    }

    @Override
    public String toString() {
        return "BookServiceViewModel{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", noteVMList=" + noteVMList +
                '}';
    }
}
