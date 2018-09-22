package com.example.islam.notes.mark_dialog;

import com.example.islam.notes.models.Note;

 interface MarkPresenter {
    void update(Note note);

    void destroy();
}
