package com.mkproduction.mynotes.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.mkproduction.mynotes.R;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {
	private Button mSignupButton;
	private EditText mUserEmailEdittext;
	private EditText mUserPassword;
	private EditText mUserRepeatPassword;
	private CheckBox mRememberUserCheckBox;
	private TextView mAlreadyLoggedInTextView;
	private FirebaseAuth mFirebaseAuth;
	private boolean doubleBackToExitPressedOnce = false;

	@Override
	protected void onCreate ( Bundle savedInstanceState ) {
		super.onCreate(savedInstanceState);
		SharedPreferences sharedPreferences = getSharedPreferences("userStatus", MODE_PRIVATE);
		if ( sharedPreferences.getBoolean("isLoggedIn", false) ) {
			Toast.makeText(this, "Already logged in", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(this, MainActivity.class));
		} else {
			setContentView(R.layout.activity_sign_up);
			mFirebaseAuth = FirebaseAuth.getInstance();
			findViews();
			mSignupButton.setOnClickListener(view -> {
				if ( mUserEmailEdittext.getText().toString().trim().isEmpty() && mUserEmailEdittext.getText().toString().trim().isEmpty() && mUserEmailEdittext.getText().toString().trim().isEmpty() )
					Toast.makeText(this, "Please fill-up your credentials", Toast.LENGTH_SHORT).show();
				else {
					String userEmail = mUserEmailEdittext.getText().toString().trim();
					String userPassword = mUserPassword.getText().toString().trim();
					String userRepeatPassword = mUserRepeatPassword.getText().toString().trim();
					if ( !userPassword.equals(userRepeatPassword) )
						Toast.makeText(this, "Please enter same password", Toast.LENGTH_SHORT).show();
					else {
						mFirebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword)
								.addOnSuccessListener(authResult -> Log.d("Signup Activity", " Signup Done successfully"))
								.addOnFailureListener(e -> {
									String message = "Something went wrong";
									if ( Objects.requireNonNull(e.getLocalizedMessage()).contains("already") )
										message = " Already exists an account";
									Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
								});
						if ( mRememberUserCheckBox.isChecked() ) {
							sharedPreferences.edit().putBoolean("isLoggedIn", true).apply();
						}
						finishActivity(0);
					}
				}
			});
			mAlreadyLoggedInTextView.setOnClickListener(view -> startActivity(new Intent(this, LoginActivity.class)));
		}
	}


	private void findViews () {
		mUserEmailEdittext = findViewById(R.id.user_email_edittext);
		mUserPassword = findViewById(R.id.user_pass_1_edittext);
		mUserRepeatPassword = findViewById(R.id.user_pass_2_edittext);
		mRememberUserCheckBox = findViewById(R.id.signup_activity_remember_user_check_box);
		mSignupButton = findViewById(R.id.signup_button);
		mAlreadyLoggedInTextView = findViewById(R.id.already_logged_in_textview);
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