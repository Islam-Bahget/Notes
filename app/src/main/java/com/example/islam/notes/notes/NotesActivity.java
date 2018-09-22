package com.example.islam.notes.notes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.islam.notes.Adapter.NotesAdapter;
import com.example.islam.notes.Adapter.ResetListener;
import com.example.islam.notes.R;
import com.example.islam.notes.add_dialog.AddDialogFragment;
import com.example.islam.notes.Adapter.AddListener;
import com.example.islam.notes.Adapter.Divider;
import com.example.islam.notes.Adapter.NotesRecyclerItemClickListener;
import com.example.islam.notes.customwidgets.NotesRecyclerView;
import com.example.islam.notes.Adapter.SimpleTouchCallBack;
import com.example.islam.notes.extras.Utils;
import com.example.islam.notes.mark_dialog.MarkDialog;
import com.example.islam.notes.Adapter.MarkListener;
import com.example.islam.notes.models.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesActivity extends AppCompatActivity
        implements View.OnClickListener,
        NotesView, AddListener, NotesRecyclerItemClickListener, MarkListener,
        ResetListener {

    private NotesRecyclerView recyclerView;
    private Toolbar toolbar;
    private AppCompatButton addButton;
    private NotesAdapter adapter;
    private List<Note> arrayList;

    private View emptyNote;
    private NotesPresenter notesPresenter;

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
        adapter.setRecyclerItemClick(this);
        adapter.setMResetListener(this);
        adapter.setHasStableIds(true);


        SimpleTouchCallBack callBack = new SimpleTouchCallBack(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(callBack);
        notesPresenter = new NotesPresenterImpl(this, this);
        int type = Utils.loadPref(this);
        notesPresenter.getNotes(type);

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


    private void showMarkDialog(int position) {
        MarkDialog markDialog = new MarkDialog();

        Bundle bundle = new Bundle();
        bundle.putSerializable("note", arrayList.get(position));
        markDialog.setArguments(bundle);
        markDialog.show(getSupportFragmentManager(), "mark");
    }

    private void showAddDialog() {

        AddDialogFragment dialog = new AddDialogFragment();
        dialog.show(getSupportFragmentManager(), "Add");
    }

    @Override
    public void setAdapter(List<Note> noteList) {

        this.arrayList = noteList;
        adapter.update(arrayList);


    }


    @Override
    public void noNote(String messae) {

        arrayList.clear();
        adapter.update(arrayList);
        Log.d("size", "Size is  " + arrayList.size());
    }

    @Override
    public void add() {
        showAddDialog();
    }

    @Override
    public void onItemClicked(int position) {
        showMarkDialog(position);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        notesPresenter.onDestroy();
    }

    @Override
    public void onMarked(int position) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.all_notes:
                notesPresenter.getNotes(Utils.ALL_NOTE_TYPE);
                Utils.savePref(this, Utils.ALL_NOTE_TYPE);
                return true;
            case R.id.complete:
                notesPresenter.getNotes(Utils.ALL_COMPLETED);
                Utils.savePref(this, Utils.ALL_COMPLETED);
                return true;
            case R.id.add_actoin:
                showAddDialog();
                return true;
            case R.id.least_time:
                notesPresenter.getNotes(Utils.ALL_ASCENDING);
                Utils.savePref(this, Utils.ALL_ASCENDING);
                return true;
            case R.id.most_time:
                notesPresenter.getNotes(Utils.ALL_DESCENDING);
                Utils.savePref(this, Utils.ALL_DESCENDING);
                return true;
            case R.id.incomplete:
                notesPresenter.getNotes(Utils.ALL_IN_COMPLETED);
                Utils.savePref(this, Utils.ALL_IN_COMPLETED);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void reset() {

        Utils.savePref(this, Utils.ALL_NOTE_TYPE);

        Utils.loadPref(this);
    }
}
