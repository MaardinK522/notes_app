package com.mkproduction.mynotes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.mkproduction.mynotes.R;

public class LoginActivity extends AppCompatActivity {
	private EditText mUserEmailEditText;
	private EditText mUserPasswordEditText;
	private TextView mCreateNewUserTextView;
	private FirebaseAuth mFirebaseAuth;
	private boolean doubleBackToExitPressedOnce = false;

	@Override
	protected void onCreate ( Bundle savedInstanceState ) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		findViews();
		mFirebaseAuth = FirebaseAuth.getInstance();
		findViewById(R.id.login_activity_login_button).setOnClickListener(view -> {
			String userEmail = mUserEmailEditText.getText().toString().trim();
			String userPassword = mUserPasswordEditText.getText().toString().trim();
			if ( !userEmail.isEmpty() && userPassword.isEmpty() )
				mFirebaseAuth.signInWithEmailAndPassword(userEmail, userPassword)
						.addOnCompleteListener(task -> {
							if ( task.isSuccessful() ) {
								Toast.makeText(this, "Login successfully done", Toast.LENGTH_SHORT).show();
								Log.d("Login Activity", task.toString());
								startActivity(new Intent(this, MainActivity.class));
							}
							Toast.makeText(this, "Please enter valid credentials", Toast.LENGTH_SHORT).show();
							Log.d("Login Activity", task.getResult().toString());
						});
			else
				Toast.makeText(this, "Please fill-up all credentials", Toast.LENGTH_SHORT).show();
		});
		mCreateNewUserTextView.setOnClickListener(view -> startActivity(new Intent(this, SignUpActivity.class)));
	}

	private void findViews () {
		mUserEmailEditText = findViewById(R.id.login_activity_user_email_edittext);
		mUserPasswordEditText = findViewById(R.id.login_activity_user_password_edittext);
		mCreateNewUserTextView = findViewById(R.id.login_activity_signup_textview);
	}

	@Override
	protected void onStart () {
		super.onStart();
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
}