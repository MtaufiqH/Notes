package com.example.taufiq.mitchnotes.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.taufiq.mitchnotes.R;
import com.example.taufiq.mitchnotes.models.Notes;

import java.util.ArrayList;

/**
 * Created By Taufiq on 6/26/2019.
 * MitchNotes
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private ArrayList<Notes> notes;
    private onNoteClicked OnClicked;

    public RecyclerViewAdapter(ArrayList<Notes> notes, onNoteClicked onNoteClicked) {
        this.notes = notes;
        OnClicked = onNoteClicked;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item, viewGroup, false);
        return new RecyclerViewHolder(view, OnClicked);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder viewHolder, int i) {

        viewHolder.titleNotes.setText(notes.get(i).getTitle());
        viewHolder.timeStampNotes.setText(notes.get(i).getTimeStamp());

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


    /**
     * My View Holder
     * {@link RecyclerView.ViewHolder}
     */

    class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView titleNotes, timeStampNotes;
        onNoteClicked onNoteClicked;

        RecyclerViewHolder(@NonNull final View i, onNoteClicked onNoteClicked) {
            super(i);
            titleNotes = i.findViewById(R.id.note_title_id);
            timeStampNotes = i.findViewById(R.id.note_times_id);
            this.onNoteClicked = onNoteClicked;
            i.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onNoteClicked.onNoteClick(getAdapterPosition());
        }
    }

    public interface onNoteClicked {
        void onNoteClick(int positions);
    }
}