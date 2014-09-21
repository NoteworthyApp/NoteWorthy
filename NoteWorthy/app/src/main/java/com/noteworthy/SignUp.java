package com.noteworthy;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class SignUp extends ActionBarActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		
		android.app.ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0099ff")));
        
		Parse.initialize(this, "7NPKTk8T7KMyMGI97tRgHshB9LjwSvYXa1Z0RIzJ", "pqg8dny3PhjSrdXiKr7XPvc6rWwBy49I9h9TRozx");
		
		getActionBar().setDisplayHomeAsUpEnabled(true);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void done(View v) {
		ParseUser uniqueUser = new ParseUser();
		
		EditText username = (EditText)findViewById(R.id.signup_username);
		EditText password = (EditText)findViewById(R.id.signup_password);
		EditText email = (EditText)findViewById(R.id.email);
		
		//Test
		Log.v("String", username.toString());
		
		uniqueUser.setUsername(username.getText().toString());
		uniqueUser.setPassword(password.getText().toString());
		uniqueUser.setEmail(email.getText().toString());
		
		
		uniqueUser.signUpInBackground(new SignUpCallback() {
			  public void done(ParseException e) {
			    if (e == null) {
			      // Hooray! Let them use the app now.
			    	Log.v("Signup", "TESTETSTETS");
			    } else {
			    	Log.v("WTF", "EEEEEEEEEEEE");
			    	Log.v("ts", e.toString());
			      // Sign up didn't succeed. Look at the ParseException
			      // to figure out what went wrong
			    	e.printStackTrace();
			    }
			  }
			});
		
		Toast t = Toast.makeText(this, "You are ready to start sharing!", Toast.LENGTH_LONG);
		t.show();
		finish();
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_sign_up,
					container, false);
			return rootView;
		}
	}

}
