package com.inspireafrica.chatter;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends Activity {
 protected EditText mUsername;
 protected EditText mPassword;
 protected EditText mEmail;
 protected Button mSignUpButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_sign_up);
		mUsername=(EditText) findViewById(R.id.usernamesField);
		mPassword=(EditText) findViewById(R.id.passwordsField);
		mEmail=(EditText) findViewById(R.id.emailField);
		mSignUpButton=(Button) findViewById(R.id.signupButton);
		mSignUpButton.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				String username=mUsername.getText().toString().trim();
				String password=mPassword.getText().toString().trim();
				String email=mEmail.getText().toString().trim();
				if(username.isEmpty() || password.isEmpty() || email.isEmpty())
				{
					AlertDialog.Builder builder=new AlertDialog.Builder(SignUpActivity.this);
					builder.setMessage(R.string.error_string);
					builder.setTitle("Error");
					builder.setPositiveButton(android.R.string.ok,null);
					AlertDialog dialog=builder.create();
					dialog.show();				
					
				}else
				{
					//to complete
					//Sig in
					setProgressBarIndeterminateVisibility(true);
					ParseUser user=new ParseUser();
					user.setEmail(email);
					user.setPassword(password);
					user.setUsername(username);
					user.saveInBackground(new SaveCallback() {						
						@Override
						public void done(ParseException e) {
							// TODO Auto-generated method stub
							setProgressBarIndeterminateVisibility(false);
							if(e==null)
							{
							  Intent intent=new Intent(SignUpActivity.this,MainActivity.class);
							  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
							  startActivity(intent);
							}else
							{
								AlertDialog.Builder builder=new AlertDialog.Builder(SignUpActivity.this);
								builder.setMessage(e.getMessage());
								builder.setTitle("Error");
								builder.setPositiveButton(android.R.string.ok,null);
								AlertDialog dialog=builder.create();
								dialog.show();									
							}
							
						}
					});
				}
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
		return true;
	}

}
