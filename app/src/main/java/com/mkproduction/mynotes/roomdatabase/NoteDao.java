package com.mkproduction.mynotes.roomdatabase;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.mkproduction.mynotes.pojo.Note;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM mynotes")
    LiveData<List<Note>> getAListLiveData();

    @Insert
    void insertNote(Note note);


    @Delete
    void deleteNote(Note note);

    @Query("SELECT * FROM mynotes where ID  is :noteID")
    LiveData<Note> getNoteByID(int noteID);


    @Query("update mynotes SET noteData = :data where ID = :noteID ")
    void updateNoteData(int noteID, String data);

    @Query("update mynotes SET noteTitle = :noteTitle where ID = :noteID ")
    void updateNoteTitle(int noteID, String noteTitle);


    @Query("update mynotes SET noteAddedDateString = :noteAddedDate where ID = :noteID ")
    void updateNoteAddedDate(int noteID, String noteAddedDate);

    @Query("update mynotes SET noteIsFav = :noteIsFavourite where ID = :noteID ")
    void updateNoteIsFavourite(int noteID, char noteIsFavourite);
}
