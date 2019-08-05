package com.example.taufiq.mitchnotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.taufiq.mitchnotes.models.Notes;

public class NoteActivity extends AppCompatActivity implements View.OnTouchListener,
        GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener, View.OnClickListener {

    private static final String TAG = "NoteActivity";
    private static final int EDIT_MODE_ENABLE = 1;
    private static final int EDIT_MODE_DISABLED = 0;


    private LineEditText lineEditText;
    private EditText note_tile_edit;
    private TextView note_title;
    private RelativeLayout backArrowContainer, checkMarkContainer;
    private ImageButton mBack, mCheck;

    private boolean isNewNote;

    private Notes theNotes;

    private GestureDetector gestureDetector;

    private int MODE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        lineEditText = findViewById(R.id.note_edit);
        note_tile_edit = findViewById(R.id.note_title_edit_id);
        note_title = findViewById(R.id.note_title_id);
        backArrowContainer = findViewById(R.id.back_container);
        checkMarkContainer = findViewById(R.id.check_container);
        mBack = findViewById(R.id.note_back);
        mCheck = findViewById(R.id.note_check);


        if (getIncomingIntent()) {
            // New Note will make (Edit Mode)
            setNewNoteProperties();
            enableEditMode();
        } else {
            // this is not a new note (View Mode)
            setNoteProperties();
            disableContentInteraction();
        }


        setTouchListener();
    }


    private void setTouchListener() {
        lineEditText.setOnTouchListener(this);
        gestureDetector = new GestureDetector(this, this);
        mCheck.setOnClickListener(this);
        note_title.setOnClickListener(this);
        mBack.setOnClickListener(this);
    }


    // disable interaction when in the View Mode
    private void disableContentInteraction() {
        lineEditText.setKeyListener(null);
        lineEditText.setFocusable(false);
        lineEditText.setFocusableInTouchMode(false);
        lineEditText.setCursorVisible(false);
        lineEditText.clearFocus();
    }

    // enable interaction to the EditText (Edit Mode)
    private void enabledContentInteraction() {
        lineEditText.setKeyListener(new EditText(this).getKeyListener());
        lineEditText.setFocusable(true);
        lineEditText.setFocusableInTouchMode(true);
        lineEditText.setCursorVisible(true);
        lineEditText.requestFocus();
    }

    private void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) this.getSystemService(INPUT_METHOD_SERVICE);
        View view = this.getCurrentFocus();
        if (view == null){
            view = new View(this);
        }
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

    private void enableEditMode() {
        // Show the Check mark and hide back arrow
        backArrowContainer.setVisibility(View.GONE);
        checkMarkContainer.setVisibility(View.VISIBLE);
        // show edit title
        note_title.setVisibility(View.GONE);
        note_tile_edit.setVisibility(View.VISIBLE);
        // And then Enable Edit Mode
        MODE = EDIT_MODE_ENABLE;
        // enable interaction in edit Mode
        enabledContentInteraction();

    }

    private void disabledEditMode() {
        hideKeyboard();

        // Show the Check mark and hide back arrow
        backArrowContainer.setVisibility(View.VISIBLE);
        checkMarkContainer.setVisibility(View.GONE);
        // show edit title
        note_title.setVisibility(View.VISIBLE);
        note_tile_edit.setVisibility(View.GONE);
        // And then disabled Edit Mode
        MODE = EDIT_MODE_DISABLED;




    }


    /*
     * this method used to get new incoming notes from intent Extras.
     * */
    private boolean getIncomingIntent() {
        if (getIntent().hasExtra("ALL_NOTES")) {
            theNotes = getIntent().getParcelableExtra("ALL_NOTES");
            MODE = EDIT_MODE_DISABLED;
            isNewNote = false;
            return false;
        }

        MODE = EDIT_MODE_ENABLE;
        isNewNote = true;
        return true;
    }


    /*
     * this method used to set a default properties
     * when we make a new note
     * */
    private void setNewNoteProperties() {
        note_title.setText("Note Title");
        note_tile_edit.setText("Note Title");
    }


    /*
     * set Properties to the Views
     * */
    private void setNoteProperties() {
        note_tile_edit.setText(theNotes.getTitle());
        note_title.setText(theNotes.getTitle());
        lineEditText.setText(theNotes.getContent());
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return gestureDetector.onTouchEvent(motionEvent);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        // when LineEditText get Double Tap Edit Mode is Enabled
        enableEditMode();
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.note_check:
                disableContentInteraction();
                disabledEditMode();
                break;
            case R.id.note_title_id:
                enableEditMode();
                note_tile_edit.requestFocus();
                note_tile_edit.setSelection(note_tile_edit.length());
                break;
            case R.id.note_back:
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {

        if (MODE == EDIT_MODE_ENABLE) {
            onClick(mCheck);
        } else {
            super.onBackPressed();
        }

    }
}
