package com.geekym.notedown.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.geekym.notedown.Model.Notes;
import com.geekym.notedown.Dao.NotesDao;

@Database(entities = {Notes.class},version = 1)
public abstract class NotesDatabse extends RoomDatabase {

    public abstract NotesDao notesDao();
    public static NotesDatabse INSTANCE;

    public static NotesDatabse getDatabaseInstance(Context context)
    {
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),NotesDatabse.class,"Notes_Database").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
