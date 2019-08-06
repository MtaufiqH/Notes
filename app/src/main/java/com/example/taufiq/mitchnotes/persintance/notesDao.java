package com.example.taufiq.mitchnotes.persintance;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.taufiq.mitchnotes.models.Notes;

import java.util.List;

/**
 * Created By Taufiq on 8/6/2019.
 */

@Dao
public interface notesDao {

    @Insert
    long[] insertNotes(Notes... notes);

    @Delete
    int deleteNotes(Notes...notes);

    @Update
    int updateNotes(Notes...notes);

    @Query("SELECT * FROM notes_table")
    LiveData<List<Notes>> getAllNotes();



}














