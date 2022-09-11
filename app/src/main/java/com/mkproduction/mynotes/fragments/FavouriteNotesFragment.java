package com.mkproduction.mynotes.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.mkproduction.mynotes.R;
import com.mkproduction.mynotes.adapters.NotesRecyclerViewAdapter;
import com.mkproduction.mynotes.pojo.Note;
import com.mkproduction.mynotes.roomdatabase.NoteViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavouriteNotesFragment extends Fragment {
    private NotesRecyclerViewAdapter mNotNotesRecyclerViewAdapter;
    private Context context;
    private NoteViewModel noteViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNotNotesRecyclerViewAdapter = new NotesRecyclerViewAdapter(this.getContext(), this);
        intiViewModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite_notes, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.favourite_notes_fragment_recyclerview);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(mNotNotesRecyclerViewAdapter);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        this.context = this.getContext();
    }

    @Override
    public void onResume() {
        super.onResume();
        intiViewModel();
    }

    void intiViewModel() {
        noteViewModel.getAllNotesNote().observe(this, noteList -> {
            List<Note> notes = new ArrayList<>();
            for (Note note : noteList)
                if (note.getNoteIsFavourite() == 'y')
                    notes.add(note);
            mNotNotesRecyclerViewAdapter.setArrayListData(notes);
        });
    }
}
