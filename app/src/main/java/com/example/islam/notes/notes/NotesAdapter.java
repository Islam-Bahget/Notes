package com.example.islam.notes.notes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.islam.notes.R;
import com.example.islam.notes.customwidgets.AddListener;
import com.example.islam.notes.customwidgets.SwipeListener;
import com.example.islam.notes.helper.DataBaseRef;
import com.example.islam.notes.models.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements SwipeListener {

    private static final int ITEM = 0;
    private static final int FOOTER = 1;
    private List<Note> arrayList;
    private Context context;

    private AddListener listener;

    @Override
    public void onSwipe(int position) {
        if (position < arrayList.size()) {
            DataBaseRef.getInstance(context).deleteNote(arrayList.get(position));
            arrayList.remove(position);
            notifyItemRemoved(position);
        }

    }

    public void setAddListener(AddListener listener) {
        this.listener = listener;
    }

     NotesAdapter(Context context, List<Note> arrayList) {
        update(arrayList);
        this.context = context;
    }

    void update(List<Note> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new NotesHolder(
                    LayoutInflater.from(context).inflate(R.layout.note_row, parent, false));

        } else {
            return new FooterHolder(LayoutInflater
                    .from(context)
                    .inflate(R.layout.footer, parent, false)
                    , listener);

        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        if (holder instanceof NotesHolder) {
            Note note = arrayList.get(position);

            NotesHolder notesHolder = (NotesHolder) holder;
            notesHolder.what.setText(note.getWhat());
        }

    }

    @Override
    public int getItemCount() {
        if (arrayList == null || arrayList.isEmpty()) {
            return 0;
        } else
            return arrayList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < arrayList.size()) {
            return ITEM;

        } else {
            return FOOTER;
        }
    }


    class NotesHolder extends RecyclerView.ViewHolder {


        AppCompatTextView what, when;

         NotesHolder(View itemView) {
            super(itemView);
            what = itemView.findViewById(R.id.what_text_view);
            when = itemView.findViewById(R.id.when_text_view);
        }
    }

    class FooterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        AppCompatButton addButton;

        AddListener listener;

         FooterHolder(View itemView, AddListener listener) {
            super(itemView);
            this.listener = listener;
            addButton = itemView.findViewById(R.id.btn_footer);

            addButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.add();
        }
    }
}
