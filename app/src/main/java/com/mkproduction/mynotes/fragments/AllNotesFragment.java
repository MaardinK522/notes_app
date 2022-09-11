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
import com.mkproduction.mynotes.roomdatabase.NoteViewModel;

public class AllNotesFragment extends Fragment {
    private NotesRecyclerViewAdapter notesRecyclerViewAdapter;
    private NoteViewModel noteViewModel;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notesRecyclerViewAdapter = new NotesRecyclerViewAdapter(this.getContext(),this);
        noteViewModel.getAllNotesNote().observe(this, notes -> notesRecyclerViewAdapter.setArrayListData(notes));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_notes, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.all_notes_fragment_recyclerview);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(notesRecyclerViewAdapter);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        this.context = context;
    }

    @Override
    public void onResume() {
        super.onResume();
        noteViewModel.getAllNotesNote().observe(this, notes -> notesRecyclerViewAdapter.setArrayListData(notes));
    }
}
