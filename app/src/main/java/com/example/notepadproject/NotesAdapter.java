package com.example.notepadproject;

import android.content.Context;import android.view.LayoutInflater;
import android.view.View;import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private ArrayList<Note> notes;

    NotesAdapter(Context context, ArrayList<Note> notes) {
        this.notes = notes;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.note_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotesAdapter.ViewHolder holder, int position) {
        holder.tvNumber.setText(notes.get(position).getId());
        holder.tvTitle.setText(notes.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void refreshElements(ArrayList<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public void addElement(Note note) {
        notes.add(note);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvNumber, tvTitle;

        ViewHolder(View view) {
            super(view);
            tvNumber = view.findViewById(R.id.tvNumber);
            tvTitle = view.findViewById(R.id.tvTitle);
        }
    }
}