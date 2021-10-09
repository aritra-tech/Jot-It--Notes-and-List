package com.geekym.notedown.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.geekym.notedown.Model.Notes;
import com.geekym.notedown.R;
import com.geekym.notedown.ViewModel.NotesViewModel;
import com.geekym.notedown.databinding.ActivityUpdateNoteBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.w3c.dom.Text;

import java.util.Date;

public class UpdateNote extends AppCompatActivity {

    ActivityUpdateNoteBinding binding;
    String color = "1";
    String utitle,unotes,ucolor;
    NotesViewModel notesViewModel;
    int uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        uid = getIntent().getIntExtra("id",0);
        utitle = getIntent().getStringExtra("title");
        ucolor = getIntent().getStringExtra("color");
        unotes = getIntent().getStringExtra("note");

        binding.upgradetitle.setText(utitle);
        binding.updatenotes.setText(unotes);

        if (ucolor.equals("1"))
        {
            binding.redColor.setImageResource(R.drawable.done);
        }else if (ucolor.equals("2"))
        {
            binding.greenColor.setImageResource(R.drawable.done);
        }else if (ucolor.equals("3"))
        {
            binding.yellowColor.setImageResource(R.drawable.done);
        }else if (ucolor.equals("4"))
        {
            binding.deepblueColor.setImageResource(R.drawable.done);
        }else if (ucolor.equals("5"))
        {
            binding.lightblueColor.setImageResource(R.drawable.done);
        }

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

        binding.updatebtn.setOnClickListener(v -> {

            String title = binding.upgradetitle.getText().toString();
            String notes = binding.updatenotes.getText().toString();

            UpdateNotes(title,notes);
        });
    }

    private void UpdateNotes(String title, String notes) {

        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d,yyyy",date.getTime());

        Notes updateNotes = new Notes();

        updateNotes.id = uid;
        updateNotes.notesTitle = title;
        updateNotes.notes = notes;
        updateNotes.notescolor = color;
        updateNotes.notesDate = sequence.toString();

        notesViewModel.updateNode(updateNotes);

        Toast.makeText(this, "Notes Updated Successfully", Toast.LENGTH_SHORT).show();

        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.delete)
        {
            BottomSheetDialog sheetDialog = new BottomSheetDialog(UpdateNote.this);

            View view = LayoutInflater.from(UpdateNote.this).inflate(R.layout.delete_bottom_popup,(LinearLayout) findViewById(R.id.bottom_popup));
            sheetDialog.setContentView(view);

            TextView yes,no;

            yes = view.findViewById(R.id.delete_yes);
            no = view.findViewById(R.id.delete_no);

            yes.setOnClickListener(v -> {

                notesViewModel.deleteNote(uid);
                finish();
            });

            no.setOnClickListener(v -> {
                sheetDialog.dismiss();
            });

            sheetDialog.show();
        }
        return true;
    }
}