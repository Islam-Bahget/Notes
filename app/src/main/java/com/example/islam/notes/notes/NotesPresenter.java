package com.example.islam.notes.notes;

interface NotesPresenter {
    void getNotes(int type);

    void deletedNotes();

    void onDestroy();
}
