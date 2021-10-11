package com.geekym.notedown;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
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

        notesViewModel.getAllNotes.observe(this, notes -> {
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));
            adapter = new NoteAdapter(MainActivity.this,notes);
            recyclerView.setAdapter(adapter);
        });
    }
}