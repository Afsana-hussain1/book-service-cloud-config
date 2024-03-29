package com.company.bookservice.viewmodels;

import java.util.Objects;

public class NoteViewModel {

    private int noteId;
    private int bookId;
    private String note;

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteViewModel note1 = (NoteViewModel) o;
        return getNoteId() == note1.getNoteId() &&
                getBookId() == note1.getBookId() &&
                Objects.equals(getNote(), note1.getNote());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNoteId(), getBookId(), getNote());
    }

    @Override
    public String toString() {
        return "Note{" +
                "noteId=" + noteId +
                ", bookId=" + bookId +
                ", Note='" + note + '\'' +
                '}';
    }
}
