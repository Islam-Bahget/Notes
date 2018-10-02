package com.example.islam.notes;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.islam.notes.data_repository.AppDataBase;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TestRoom extends AppCompatActivity {


    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_room);

        recyclerView = findViewById(R.id.user_recycler);
        TestAdapter adapter = new TestAdapter(this);
//        recyclerView.setLayoutManager(
//                new GridLayoutManager(this, GridLayoutManager.DEFAULT_SPAN_COUNT));
        recyclerView.setAdapter(adapter);

    }


}


