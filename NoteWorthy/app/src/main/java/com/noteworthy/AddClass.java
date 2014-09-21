package com.noteworthy;

import com.parse.ParseFacebookUtils.Permissions.User;
import com.parse.ParseObject;
import com.parse.ParseUser;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

public class AddClass extends ActionBarActivity {

 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.fragment_add_class);
  
        android.app.ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0099FF")));
        
        String[][] classData = new String[5][10];
        String[] classesList = {"Calculus 223","Calculus 101","Physics 122",
          "EECS 223"};
        
        AutoCompleteTextView textView = (AutoCompleteTextView) 
        		findViewById(R.id.course_input);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, 
        		android.R.layout.simple_dropdown_item_1line, classesList);
 
        textView.setThreshold(1);
        textView.setAdapter(adapter1);
 
 }
 
 
 public void finishedAddingClass(View v) {
	 ParseUser user = ParseUser.getCurrentUser();
	 ParseObject course = new ParseObject("Courses");
	 
	 AutoCompleteTextView school = (AutoCompleteTextView)findViewById(R.id.school_input);
	 AutoCompleteTextView courseName = (AutoCompleteTextView)findViewById(R.id.course_input);
	 EditText prof = (EditText)findViewById(R.id.professor_input);
	 
	 course.put("courseName", courseName.getText().toString());
	 course.put("schoolName", school.getText().toString());
	 course.put("profName", prof.getText().toString());
	 course.put("user", user);
	 
	 course.saveInBackground();
	 finish();
 }

 @Override
 public boolean onCreateOptionsMenu(Menu menu) {
  // Inflate the menu; this adds items to the action bar if it is present.
  getMenuInflater().inflate(R.menu.add_class, menu);
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
}
