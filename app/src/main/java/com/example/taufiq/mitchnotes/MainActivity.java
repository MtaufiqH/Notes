package com.example.taufiq.mitchnotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.taufiq.mitchnotes.adapter.RecyclerViewAdapter;
import com.example.taufiq.mitchnotes.models.Notes;
import com.example.taufiq.mitchnotes.utils.RecyclerItemDecorator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.onNoteClicked {

    // Variable Section
    private ArrayList<Notes> all_notes = new ArrayList<>();
    RecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.note_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Notes");


        initRecyclerView();
        dummyNotes();


        // Add all_notes with clicking Floating Action Button
        FloatingActionButton fab_addNotes = findViewById(R.id.fab_add_note);
        fab_addNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                startActivity(intent);
            }
        });

    }


    // initialisation RecylerView
    private void initRecyclerView() {
        // UI Component
        RecyclerView recyclerView = findViewById(R.id.recyclerView_id);
        adapter = new RecyclerViewAdapter(all_notes, this);
        recyclerView.setAdapter(adapter);
        RecyclerItemDecorator itemDecorator = new RecyclerItemDecorator(10, 10);
        recyclerView.addItemDecoration(itemDecorator);
        new ItemTouchHelper(touchHelper).attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    // dummy data for RecylerView Item.
    private void dummyNotes() {

        for (int i = 1; i <= 20; i++) {
            Notes aNotes = new Notes("Title " + i, "content #" + i, "Jan 2019");
            all_notes.add(aNotes);
        }

    }

    /**
     * Note Listener
     *
     * @param positions on the list
     **/
    // when all_notes is clicked move send data from Model to Note Editor.
    @Override
    public void onNoteClick(int positions) {
        Intent intent = new Intent(this, NoteActivity.class);
        intent.putExtra("ALL_NOTES", all_notes.get(positions));
        startActivity(intent);
    }


    /**
     * Function to make swipe recylerview to delete single data
     * on the list.
     *
     * @param notes
     */

    private void deleteNote(Notes notes) {
        all_notes.remove(notes);
        adapter.notifyDataSetChanged();


    }

    /*
    * ItemTouchHelper.SimpleCallback
    * Funtion that makes RecylerView item can be swiped.
    * */
    private ItemTouchHelper.SimpleCallback touchHelper = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            deleteNote(all_notes.get(viewHolder.getAdapterPosition()));
        }
    };



}
