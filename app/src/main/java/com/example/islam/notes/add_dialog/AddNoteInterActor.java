package com.example.islam.notes.add_dialog;

import com.example.islam.notes.models.Note;

interface AddNoteInterActor {

    interface OnAddNoteDone {
        void onAdded();

    }

    void addNote(Note note,OnAddNoteDone onAddNoteDone);
}
