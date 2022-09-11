package com.mkproduction.mynotes.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class NotesFragmentAdapter extends FragmentStateAdapter {
	ArrayList<Fragment> fragmentArrayList;
	Context context;

	public NotesFragmentAdapter ( @NonNull FragmentActivity fragmentActivity ) {
		super(fragmentActivity);
		this.context = fragmentActivity.getApplicationContext();
		this.fragmentArrayList = new ArrayList<>();
	}

	public void addFragment ( @NonNull Fragment fragment ) {
		fragmentArrayList.add(fragment);
	}

	@NonNull
	@Override
	public Fragment createFragment ( int position ) {
		return this.fragmentArrayList.get(position);
	}

	@Override
	public int getItemCount () {
		return this.fragmentArrayList.size();
	}
}
