package com.mkproduction.mynotes.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.mkproduction.mynotes.R;
import com.mkproduction.mynotes.adapters.NotesFragmentAdapter;
import com.mkproduction.mynotes.fragments.AllNotesFragment;
import com.mkproduction.mynotes.fragments.FavouriteNotesFragment;

public class MainActivity extends AppCompatActivity {
	private TabLayout tabLayout;
	private ViewPager2 viewPager2;
	private boolean doubleBackToExitPressedOnce = false;

	@Override
	protected void onCreate ( Bundle savedInstanceState ) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// getting views.
		tabLayout = findViewById(R.id.main_activity_tab_layout);
		viewPager2 = findViewById(R.id.main_activity_view_pager);
		FloatingActionButton floatingActionButton = findViewById(R.id.main_activity_to_note_view_activity_floating_action_button);
		// preparing fragments
		NotesFragmentAdapter fragmentAdapter = new NotesFragmentAdapter(this);
		AllNotesFragment allNotesFragment = new AllNotesFragment();
		FavouriteNotesFragment favouriteNotesFragment = new FavouriteNotesFragment();
		fragmentAdapter.addFragment(allNotesFragment);
		fragmentAdapter.addFragment(favouriteNotesFragment);
		viewPager2.setAdapter(fragmentAdapter);
		// preparing tab layout.
		tabLayout.addTab(tabLayout.newTab().setText("All"));
		tabLayout.addTab(tabLayout.newTab().setText("Favourite"));
		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
		tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected ( TabLayout.Tab tab ) {
				viewPager2.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabUnselected ( TabLayout.Tab tab ) {

			}

			@Override
			public void onTabReselected ( TabLayout.Tab tab ) {

			}
		});
		viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
			@Override
			public void onPageSelected ( int position ) {
				super.onPageSelected(position);
				tabLayout.setScrollPosition(position, 0, true);
			}
		});
		floatingActionButton.setOnClickListener(view -> {
			Intent intent = new Intent(this, NoteViewActivity.class);
			intent.putExtra("noteStatus", "newNote");
			startActivity(intent);
		});
	}

	@Override
	public void onBackPressed () {
		if ( doubleBackToExitPressedOnce ) {
			System.exit(0);
			return;
		}
		this.doubleBackToExitPressedOnce = true;
		Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
		new Handler(Looper.getMainLooper()).postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
	}

	@Override
	public boolean onCreateOptionsMenu ( @NonNull Menu menu ) {
		getMenuInflater().inflate(R.menu.main_activity_popupmenu_resource, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@SuppressLint( "NonConstantResourceId" )
	@Override
	public boolean onOptionsItemSelected ( @NonNull MenuItem item ) {

		switch ( item.getItemId() ) {
			case R.id.main_activity_menu_logout_item:
				startActivity(new Intent(this, LoginActivity.class));
				SharedPreferences sharedPreferences = getSharedPreferences("userStatus", MODE_PRIVATE);
				sharedPreferences.edit().putBoolean("isLoggedIn", false).apply();
				Toast.makeText(this, "Log out successfully", Toast.LENGTH_SHORT).show();
				break;
		}
		return super.onOptionsItemSelected(item);
	}
}