package com.example.islam.notes.add_dialog;

import android.content.Context;

import com.example.islam.notes.helper.DataBaseRef;
import com.example.islam.notes.models.Note;

class AddNoteInterActorImpl implements AddNoteInterActor {

    private Context context;

    public AddNoteInterActorImpl(Context context) {
        this.context = context;
    }

    @Override
    public void addNote(Note note, OnAddNoteDone onAddNoteDone) {
        DataBaseRef.getInstance(context).insertNote(note);
        onAddNoteDone.onAdded();

    }
}
