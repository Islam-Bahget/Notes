package com.example.islam.notes.data_repository;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.islam.notes.models.Note;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.internal.operators.maybe.MaybeToObservable;

@Dao
public interface NoteDao {

    @Insert
    void insertNote(Note note);

    @Query("select * from notes")
    Flowable<List<Note>> getNotes();

    @Delete
    void deleteNote(Note note);
}
