package com.mkproduction.mynotes.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.mkproduction.mynotes.activities.NoteViewActivity;
import com.mkproduction.mynotes.R;
import com.mkproduction.mynotes.fragments.FavouriteNotesFragment;
import com.mkproduction.mynotes.pojo.Note;
import com.mkproduction.mynotes.roomdatabase.NoteViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotesRecyclerViewAdapter extends RecyclerView.Adapter<NotesRecyclerViewAdapter.NotesItemViewHolder> {
    private final Context context;
    private final ArrayList<Note> noteArrayList;
    private NoteViewModel noteViewModel;
    private Fragment mCurrentFragment;

    public NotesRecyclerViewAdapter(Context context, Fragment fragment) {
        this.context = context;
        noteArrayList = new ArrayList<>();
        this.mCurrentFragment = fragment;
    }

    @NonNull
    @Override
    public NotesItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_note_item_layout, parent, false);
        return new NotesItemViewHolder(view);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull NotesItemViewHolder holder, int position) {
        noteViewModel = new ViewModelProvider((FragmentActivity) context).get(NoteViewModel.class);
        Note note = noteArrayList.get(position);
        Log.d("Context class name", "class name : " + mCurrentFragment.getClass().getCanonicalName());
        Log.d("are Same", String.valueOf(mCurrentFragment.getClass().getCanonicalName() == FavouriteNotesFragment.class.getCanonicalName()));

        if (mCurrentFragment.getClass().getCanonicalName() == FavouriteNotesFragment.class.getCanonicalName() && note.getNoteIsFavourite() != 'y') {
            return;
        }
        holder.mNoteTitleTextview.setText(note.getNoteTitle());
        holder.mNoteDataTextview.setText(note.getNoteData());
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, NoteViewActivity.class);
            intent.putExtra("noteStatus", "noteEdit");
            intent.putExtra("noteID", note.getID());
            context.startActivity(intent);
        });
        holder.itemView.setOnLongClickListener(view -> {
            new AlertDialog.Builder(context).setTitle("Confirm").setMessage("Are you sure you want o delete the selected note?").setPositiveButton("YES", (dialogInterface, i) -> {
                noteViewModel.deleteNote(note);
                Toast.makeText(context, "Note got deleted.", Toast.LENGTH_SHORT).show();
            }).setNegativeButton("NO", null).show();
            return false;
        });
        holder.mRootViewLinearLayout.setBackground(holder.gradientDrawable);
        if (note.getNoteIsFavourite() == 'y')
            holder.mFavouriteImageview.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_star_24));
        else
            holder.mFavouriteImageview.setImageDrawable(null);
    }

    int getRandomNm() {
        return (new Random().nextInt(255) - 125);
    }

    @Override
    public int getItemCount() {
        if (noteArrayList.size() > 0)
            return noteArrayList.size();
        return 0;
    }

    public void setArrayListData(List<Note> notes) {
        this.noteArrayList.clear();
        this.noteArrayList.addAll(notes);
        notifyDataSetChanged();
    }

    public static class NotesItemViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mFavouriteImageview;
        private final TextView mNoteTitleTextview;
        private final TextView mNoteDataTextview;
        private final LinearLayout mRootViewLinearLayout;
        private final GradientDrawable gradientDrawable;

        @SuppressLint("UseCompatLoadingForDrawables")
        public NotesItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mRootViewLinearLayout = itemView.findViewById(R.id.note_item_parent_linear_layout);
            mFavouriteImageview = itemView.findViewById(R.id.recyclerview_note_item_is_favourite_imageview);
            mNoteTitleTextview = itemView.findViewById(R.id.recyclerview_note_item_title_textview);
            mNoteDataTextview = itemView.findViewById(R.id.recyclerview_note_item_data_textview);
            gradientDrawable = (GradientDrawable) itemView.getContext().getDrawable(R.drawable.color_theme_border);
        }
    }
}
