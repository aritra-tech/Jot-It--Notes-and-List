package com.geekym.notedown.Repo;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.geekym.notedown.Dao.NotesDao;
import com.geekym.notedown.Database.NotesDatabse;
import com.geekym.notedown.Model.Notes;

import java.util.List;

public class NotesRepository {

    public NotesDao notesDao;
    public LiveData<List<Notes>> getallNotes;


    public NotesRepository(Application application)
    {
        NotesDatabse database = NotesDatabse.getDatabaseInstance(application);
        notesDao = database.notesDao();
        getallNotes = notesDao.getallNotes();
    }

    public void insertNotes(Notes notes)
    {
        notesDao.insertNotes(notes);
    }
    public void deleteNotes(int id)
    {
        notesDao.deleteNotes(id);
    }
    public void updateNotes(Notes notes)
    {
        notesDao.updateNotes(notes);
    }
}
