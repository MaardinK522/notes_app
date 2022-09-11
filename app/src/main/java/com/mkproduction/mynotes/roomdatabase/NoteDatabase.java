package com.mkproduction.mynotes.roomdatabase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.mkproduction.mynotes.pojo.Note;

import java.util.Date;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase instance;

    public abstract NoteDao noteDao();

    public static synchronized NoteDatabase getInstance(Context context) {
        if (instance == null)
            instance = Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class, "MyNotesDatabase").fallbackToDestructiveMigration().addCallback(roomCallback).build();
        return instance;
    }

    private static final RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            AsyncTask.execute(() -> instance.noteDao().insertNote(new Note("Demo note Title", "Demo note data is here", new Date(System.currentTimeMillis()).toString(),'n')));
        }
    };
}
