package com.geekym.notedown.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.geekym.notedown.Model.Notes;
import com.geekym.notedown.Repo.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    public NotesRepository repository;
    public LiveData<List<Notes>> getAllNotes;

    public NotesViewModel(Application application) {
        super(application);

         repository = new NotesRepository(application);
         getAllNotes = repository.getallNotes;
    }

    public void insertNote(Notes notes)
    {
        repository.insertNotes(notes);
    }
    public void deleteNote(int id)
    {
        repository.deleteNotes(id);
    }
    public void updateNode(Notes notes)
    {
        repository.updateNotes(notes);
    }
}
