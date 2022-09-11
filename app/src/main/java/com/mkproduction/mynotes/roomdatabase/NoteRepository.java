package com.mkproduction.mynotes.roomdatabase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mkproduction.mynotes.pojo.Note;

import java.util.List;

public class NoteRepository {
    private final NoteDao noteDao;

    public NoteRepository(Application application) {
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        noteDao = noteDatabase.noteDao();
    }

    public void insertNote(Note note) {
        AsyncTask.execute(() -> noteDao.insertNote(note));
    }

    public void deleteNote(Note note) {
        AsyncTask.execute(() -> noteDao.deleteNote(note));
    }

    public LiveData<List<Note>> getAllNotesData() {
        return noteDao.getAListLiveData();
    }

    public LiveData<Note> getNoteByID(int noteID) {
        return noteDao.getNoteByID(noteID);
    }

    public void updateNote(Note note) {
        AsyncTask.execute(() -> {
            noteDao.updateNoteTitle(note.getID(), note.getNoteTitle());
            noteDao.updateNoteData(note.getID(), note.getNoteData());
            noteDao.updateNoteAddedDate(note.getID(), note.getNoteAddedDate());
            noteDao.updateNoteIsFavourite(note.getID(), note.getNoteIsFavourite());
        });
    }
}
