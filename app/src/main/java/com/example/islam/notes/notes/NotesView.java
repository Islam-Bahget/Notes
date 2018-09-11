package com.example.islam.notes.notes;

import com.example.islam.notes.models.Note;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

interface NotesView {
    void setAdapter(List<Note> noteList);

    void noNote(String messae);

}
