package com.example.islam.notes.add_dialog;

import com.example.islam.notes.models.Note;

interface AddNotePresenter {

    void addNote(Note note);

    void onDestroy();
}
