package com.example.taufiq.mitchnotes.persintance;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.taufiq.mitchnotes.models.Notes;

/**
 * Created By Taufiq on 8/6/2019.
 */
@Database(entities = {Notes.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {


    public static final String DATABASE_NAME = "notes_db";

    private static NoteDatabase INSTANCE;

    static NoteDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    NoteDatabase.class,
                    DATABASE_NAME
            ).build();
        }

        return INSTANCE;
    }

}
