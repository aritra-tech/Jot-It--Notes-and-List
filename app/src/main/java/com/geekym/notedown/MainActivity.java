package com.geekym.notedown;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.SearchView;

import com.geekym.notedown.Activity.InsertNote;
import com.geekym.notedown.Adapter.NoteAdapter;
import com.geekym.notedown.Model.Notes;
import com.geekym.notedown.ViewModel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton newnote;
    NotesViewModel notesViewModel;
    RecyclerView recyclerView;
    NoteAdapter adapter;

    List<Notes> filternotes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        newnote = findViewById(R.id.newnote);
        recyclerView = findViewById(R.id.noterecyclerview);

        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        newnote.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, InsertNote.class));
        });

        notesViewModel.getAllNotes.observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
                setAdapter(notes);
                filternotes = notes;
            }
        });
    }

    public void setAdapter(List<Notes> notes)
    {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        adapter = new NoteAdapter(MainActivity.this,notes);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Your Notes....");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                NotesFilter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void NotesFilter(String newText) {

        ArrayList<Notes> FilterName = new ArrayList<>();
        for (Notes notes : this.filternotes){

            if (notes.notesTitle.contains(newText))
            {
                FilterName.add(notes);
            }
        }
        this.adapter.searchNotes(FilterName);
    }
}