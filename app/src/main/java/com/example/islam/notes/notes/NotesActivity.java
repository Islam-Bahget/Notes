package com.example.islam.notes.notes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.islam.notes.R;
import com.example.islam.notes.add_dialog.AddDialogFragment;
import com.example.islam.notes.customwidgets.AddListener;
import com.example.islam.notes.customwidgets.Divider;
import com.example.islam.notes.customwidgets.NotesRecyclerView;
import com.example.islam.notes.customwidgets.SimpleTouchCallBack;
import com.example.islam.notes.models.Note;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class NotesActivity extends AppCompatActivity
        implements View.OnClickListener, NotesView, AddListener {

    NotesRecyclerView recyclerView;
    Toolbar toolbar;
    AppCompatButton addButton;
    NotesAdapter adapter;
    List<Note> arrayList;

    View emptyNote;
    NotesPresenter notesPresenter;

    CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initBackground();
    }

    private void initViews() {
        arrayList = new ArrayList<>();

        adapter = new NotesAdapter(this, arrayList);
        adapter.setAddListener(this);


        SimpleTouchCallBack callBack = new SimpleTouchCallBack(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(callBack);
        notesPresenter = new NotesPresenterImpl(this, this);
        notesPresenter.getNotes();

        emptyNote = findViewById(R.id.empty_note);
        addButton = findViewById(R.id.btn_add);
        addButton.setOnClickListener(this);

        toolbar = findViewById(R.id.tool_bar);

        recyclerView = findViewById(R.id.notes_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new Divider(this, LinearLayoutManager.VERTICAL));
        recyclerView.hideIfEmpty(toolbar);
        recyclerView.showIfEmpty(emptyNote);
        recyclerView.setAdapter(adapter);

        helper.attachToRecyclerView(recyclerView);

        setSupportActionBar(toolbar);
    }

    private void initBackground() {
        AppCompatImageView background = findViewById(R.id.background);
        Glide.with(this).load(R.drawable.background).into(background);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                showAddDialog();
        }

    }


    @Override
    protected void onStart() {
        super.onStart();

    }


    private void showAddDialog() {

        AddDialogFragment dialog = new AddDialogFragment();
        dialog.show(getSupportFragmentManager(), "Add");
    }

    @Override
    public void setAdapter(List<Note> noteList) {
        this.arrayList = noteList;
        adapter.update(noteList);


    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("life", "Paused");
    }


    @Override
    public void noNote(String messae) {

        Toast.makeText(this, messae, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void add() {
        showAddDialog();
    }
}
