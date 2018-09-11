package com.example.islam.notes.notes;

import android.content.Context;

import com.example.islam.notes.models.Note;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

class NotesPresenterImpl implements NotesPresenter, NotesInterActor.OnNotesDone {

    private NotesInterActor interActor;
    private Context context;
    private NotesView notesView;

     NotesPresenterImpl(Context context, NotesView notesView) {
        this.context = context;
        this.notesView = notesView;
        this.interActor = new NotesInterActorImpl(context);
    }

    @Override
    public void getNotes() {
        if (notesView != null) {
            interActor.getNotes(this);
        }

    }

    @Override
    public void deletedNotes() {

    }

    @Override
    public void onDestroy() {
        if (notesView != null) {
            notesView = null;
        }

    }

    @Override
    public void onNotesFound(List<Note> noteList) {

        if (notesView != null) {
            notesView.setAdapter(noteList);
        }
    }

    @Override
    public void onNoNote(String message) {

        if (notesView != null) {
            notesView.noNote(message);
        }
    }
}
