package com.example.islam.notes.mark_dialog;

import com.example.islam.notes.models.Note;

interface MarkInterActor {
    interface OnMarkedListener {
        void done();
    }

    void updateNote(Note note, OnMarkedListener markedListener);
}
