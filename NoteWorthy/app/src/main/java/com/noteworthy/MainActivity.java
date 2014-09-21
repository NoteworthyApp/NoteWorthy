package com.noteworthy;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.PopupMenu;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.os.Build;

import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        android.app.ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0099ff")));
        
        Parse.initialize(this, "7NPKTk8T7KMyMGI97tRgHshB9LjwSvYXa1Z0RIzJ", "pqg8dny3PhjSrdXiKr7XPvc6rWwBy49I9h9TRozx");
        

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        
        ParseObject fubu = new ParseObject("FuBu");
        fubu.put("Israel", "Hill");
        fubu.saveInBackground();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
    
    public void signMeUp(View v) {
    	Intent intent = new Intent(this, SignUp.class);
    	startActivity(intent);
    }
    
    public void signIn(View view){
    	
    	EditText username = (EditText)findViewById(R.id.username);
    	EditText password = (EditText)findViewById(R.id.password);
    	
    	ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {

			@Override
			public void done(ParseUser uniqueUser, ParseException e) {
				// TODO Auto-generated method stub
				if (uniqueUser != null) {
	    		      // Hooray! The user is logged in.
						Intent intent = new Intent(MainActivity.this, Classes.class);
						startActivity(intent);
	    		    } 
					else {
	    		      // Sign up failed. Look at the ParseException to see what happened.
						AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
						builder.setMessage(R.string.invalid);
						builder.setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
					           public void onClick(DialogInterface dialog, int id) {
					               // User clicked OK button
					           }
					       });
						AlertDialog dialog = builder.create();
						dialog.show();
	    		    }
	    		  }
    		});
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
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
