package com.example.islam.notes.notes;

import android.content.Context;
import android.util.Log;

import com.example.islam.notes.data_repository.NoteDao;
import com.example.islam.notes.extras.Utils;
import com.example.islam.notes.helper.DataBaseRef;
import com.example.islam.notes.models.Note;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

class NotesInterActorImpl implements NotesInterActor {
    private Context context;

    private CompositeDisposable disposable;

    NotesInterActorImpl(Context context) {
        this.context = context;
        disposable = new CompositeDisposable();
    }

    @Override
    public void getNotes(final int type, final OnNotesDone onNotesDone) {

        switch (type) {
            case Utils.ALL_NOTE_TYPE:
                getAllNotes(onNotesDone);
                break;

            case Utils.ALL_DESCENDING:
                getNotesDec(onNotesDone);
                break;
            case Utils.ALL_ASCENDING:
                getNotesASC(onNotesDone);
                break;
            case Utils.ALL_COMPLETED:
                getCompleted(true, onNotesDone);
                break;
            case Utils.ALL_IN_COMPLETED:
                getCompleted(false, onNotesDone);
                break;
        }


    }

    private void getAllNotes(final OnNotesDone onNotesDone) {
        NoteDao noteDao = DataBaseRef.getInstance(context);

        disposable.add(noteDao.getNotes()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Note>>() {
                    @Override
                    public void accept(List<Note> noteLis) {
                        if (noteLis.size() == 0) {
                            onNotesDone
                                    .onNoNote("No Notes");
                        } else {
                            onNotesDone.onNotesFound(noteLis);
                        }

                    }


                }));
    }

    private void getNotesDec(final OnNotesDone onNotesDone) {
        NoteDao noteDao = DataBaseRef.getInstance(context);

        disposable.add(noteDao.getDescendingOrder()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Note>>() {
                    @Override
                    public void accept(List<Note> noteLis) {
                        if (noteLis.size() == 0) {
                            onNotesDone
                                    .onNoNote("No Notes");
                        } else {
                            onNotesDone.onNotesFound(noteLis);
                        }

                    }


                }));
    }

    private void getNotesASC(final OnNotesDone onNotesDone) {
        NoteDao noteDao = DataBaseRef.getInstance(context);

        disposable.add(noteDao.getAscendingOrder()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Note>>() {
                    @Override
                    public void accept(List<Note> noteLis) {
                        if (noteLis.size() == 0) {
                            onNotesDone
                                    .onNoNote("No Notes");
                        } else {
                            onNotesDone.onNotesFound(noteLis);
                        }

                    }


                }));
    }

    private void getCompleted(boolean completed, final OnNotesDone onNotesDone) {
        NoteDao noteDao = DataBaseRef.getInstance(context);

        disposable.add(noteDao.getCompleted(completed)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Note>>() {
                    @Override
                    public void accept(List<Note> noteLis) {
                        if (noteLis.size() == 0) {
                            onNotesDone
                                    .onNoNote("No Notes");
                        } else {
                            onNotesDone.onNotesFound(noteLis);
                        }

                    }


                }));
    }
}
