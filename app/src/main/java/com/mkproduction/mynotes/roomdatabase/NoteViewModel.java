package com.mkproduction.mynotes.roomdatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mkproduction.mynotes.pojo.Note;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private final NoteRepository noteRepository;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
    }

    public void insertNote(Note note) {
        noteRepository.insertNote(note);
    }

    public void deleteNote(Note note) {
        noteRepository.deleteNote(note);
    }

    public LiveData<List<Note>> getAllNotesNote() {
        return noteRepository.getAllNotesData();
    }

    public LiveData<Note> getNoteByID(int noteID) {
        return noteRepository.getNoteByID(noteID);
    }

    public void updateNote(Note note) {
        noteRepository.updateNote(note);
    }
}
