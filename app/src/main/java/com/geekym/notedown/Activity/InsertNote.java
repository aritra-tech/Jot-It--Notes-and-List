package com.geekym.notedown.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.geekym.notedown.MainActivity;
import com.geekym.notedown.Model.Notes;
import com.geekym.notedown.R;
import com.geekym.notedown.ViewModel.NotesViewModel;
import com.geekym.notedown.databinding.ActivityInsertNoteBinding;

import java.util.Date;

public class InsertNote extends AppCompatActivity {

    ActivityInsertNoteBinding binding;
    String title,notes;
    NotesViewModel notesViewModel;
    String color = "1";
    ImageView back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*back_btn.findViewById(R.id.back_btn);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InsertNote.this, MainActivity.class);
                startActivity(intent);
            }
        });*/

        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        binding.redColor.setOnClickListener(v -> {

            binding.redColor.setImageResource(R.drawable.done);
            binding.greenColor.setImageResource(0);
            binding.yellowColor.setImageResource(0);
            binding.deepblueColor.setImageResource(0);
            binding.lightblueColor.setImageResource(0);
            color = "1";
        });
        binding.greenColor.setOnClickListener(v -> {

            binding.redColor.setImageResource(0);
            binding.greenColor.setImageResource(R.drawable.done);
            binding.yellowColor.setImageResource(0);
            binding.deepblueColor.setImageResource(0);
            binding.lightblueColor.setImageResource(0);
            color = "2";
        });
        binding.yellowColor.setOnClickListener(v -> {

            binding.redColor.setImageResource(0);
            binding.greenColor.setImageResource(0);
            binding.yellowColor.setImageResource(R.drawable.done);
            binding.deepblueColor.setImageResource(0);
            binding.lightblueColor.setImageResource(0);
            color = "3";
        });
        binding.deepblueColor.setOnClickListener(v -> {

            binding.redColor.setImageResource(0);
            binding.greenColor.setImageResource(0);
            binding.yellowColor.setImageResource(0);
            binding.deepblueColor.setImageResource(R.drawable.done);
            binding.lightblueColor.setImageResource(0);
            color = "4";
        });
        binding.lightblueColor.setOnClickListener(v -> {

            binding.redColor.setImageResource(0);
            binding.greenColor.setImageResource(0);
            binding.yellowColor.setImageResource(0);
            binding.deepblueColor.setImageResource(0);
            binding.lightblueColor.setImageResource(R.drawable.done);
            color = "5";
        });

        binding.donebtn.setOnClickListener(v -> {

            title = binding.title.getText().toString();
            notes = binding.notesData.getText().toString();

            CreateNote(title,notes);
        });


    }

    private void CreateNote(String title, String notes) {

        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d,yyyy",date.getTime());

        Notes notes1 = new Notes();
        notes1.notesTitle = title;
        notes1.notes = notes;
        notes1.notescolor = color;
        notes1.notesDate = sequence.toString();
        notesViewModel.insertNote(notes1);

        Toast.makeText(this, "Notes Created", Toast.LENGTH_SHORT).show();
        finish();
    }
}