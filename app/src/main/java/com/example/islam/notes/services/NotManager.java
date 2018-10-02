package com.example.islam.notes.services;

import android.support.annotation.NonNull;

import androidx.work.Worker;

public class NotManager extends Worker {
    @NonNull
    @Override
    public Result doWork() {

        return Result.FAILURE;
    }
}
