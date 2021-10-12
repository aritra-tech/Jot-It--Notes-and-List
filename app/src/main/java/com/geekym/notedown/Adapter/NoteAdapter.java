package com.geekym.notedown.Adapter;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geekym.notedown.Activity.UpdateNote;
import com.geekym.notedown.MainActivity;
import com.geekym.notedown.Model.Notes;
import com.geekym.notedown.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.notesViewHolder> {

    MainActivity mainActivity;
    List<Notes> notes;
    List<Notes> allNotes;


    public NoteAdapter(MainActivity mainActivity, List<Notes> notes) {
        this.mainActivity = mainActivity;
        this.notes = notes;
        allNotes = new ArrayList<>(notes);

    }

    public void searchNotes(List<Notes> filternote)
    {
        this.notes = filternote;
        notifyDataSetChanged();
    }
    @Override
    public notesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new notesViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.items,parent,false));
    }

    @Override
    public void onBindViewHolder(NoteAdapter.notesViewHolder holder, int position) {

        Notes note = notes.get(position);

        holder.title.setText(note.notesTitle);
        holder.notesdate.setText(note.notesDate);

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(mainActivity, UpdateNote.class);

            intent.putExtra("id",note.id);
            intent.putExtra("title",note.notesTitle);
            intent.putExtra("color",note.notescolor);
            intent.putExtra("note",note.notes);
            mainActivity.startActivity(intent);
        });

        switch (note.notescolor) {
            case "1":
                holder.notecolor.setBackgroundResource(R.drawable.red_color);
                break;
            case "2":
                holder.notecolor.setBackgroundResource(R.drawable.green_color);
                break;
            case "3":
                holder.notecolor.setBackgroundResource(R.drawable.yellow_color);
                break;
            case "4":
                holder.notecolor.setBackgroundResource(R.drawable.deepblue_color);
                break;
            case "5":
                holder.notecolor.setBackgroundResource(R.drawable.lightblue_color);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class notesViewHolder extends RecyclerView.ViewHolder {

        TextView title,notesdate;
        View notecolor;
        public notesViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.notetitle);
            notesdate = itemView.findViewById(R.id.notedate);
            notecolor = itemView.findViewById(R.id.noteColor);
        }
    }

}
