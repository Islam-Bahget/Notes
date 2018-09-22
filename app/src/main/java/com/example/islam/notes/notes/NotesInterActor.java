package com.example.islam.notes.notes;

import com.example.islam.notes.models.Note;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

interface NotesInterActor {
    interface OnNotesDone {
        void onNotesFound(List<Note> noteList);

        void onNoNote(String message);

    }

    void getNotes(int type, OnNotesDone onNotesDone);
}
