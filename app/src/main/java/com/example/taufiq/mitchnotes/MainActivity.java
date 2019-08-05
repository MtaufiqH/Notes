package com.example.taufiq.mitchnotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.taufiq.mitchnotes.adapter.RecyclerViewAdapter;
import com.example.taufiq.mitchnotes.models.Notes;
import com.example.taufiq.mitchnotes.utils.RecyclerItemDecorator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.onNoteClicked {

    // Variable Section
    private ArrayList<Notes> notes = new ArrayList<>();
    private FloatingActionButton fab_addNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.note_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Notes");

        initRecyclerView();
        dummyNotes();



        // Add notes with clicking Floating Action Button
        fab_addNotes = findViewById(R.id.fab_add_note);
        fab_addNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,NoteActivity.class);
                startActivity(intent);
            }
        });

    }


    // initialisation RecylerView
    private void initRecyclerView() {
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(notes, this);
        // UI Component
        RecyclerView recyclerView = findViewById(R.id.recyclerView_id);
        recyclerView.setAdapter(adapter);
        RecyclerItemDecorator itemDecorator = new RecyclerItemDecorator(10, 10);
        recyclerView.addItemDecoration(itemDecorator);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }



    // dummy data for RecylerView Item.
    private void dummyNotes() {

        for (int i = 1; i <= 20; i++) {
            Notes aNotes = new Notes("Title " + i, "content #" + i, "Jan 2019");
            notes.add(aNotes);
        }

    }

    /**
     * Note Listener
     *
     * @param positions on the list
     **/
    // when notes is clicked move send data from Model to Note Editor.

    @Override
    public void onNoteClick(int positions) {
        Intent intent = new Intent(this,NoteActivity.class);
        intent.putExtra("ALL_NOTES", notes.get(positions));
        startActivity(intent);
    }


}
