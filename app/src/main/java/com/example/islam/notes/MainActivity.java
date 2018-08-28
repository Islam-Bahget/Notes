package com.example.islam.notes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);


        initBackground();
    }

    private void initBackground() {
        AppCompatImageView background=findViewById(R.id.background);
        Glide.with(this).load(R.drawable.background).into(background);
    }
}
