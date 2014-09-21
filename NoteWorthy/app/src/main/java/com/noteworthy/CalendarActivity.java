package com.noteworthy;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ListView;

public class CalendarActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        
        android.app.ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0099FF")));
    
        ArrayList<Contributor> contributorList = new ArrayList<Contributor>();
    	ListView listView = (ListView) findViewById(R.id.contributorsList);
    	
    	
    	Contributor[] items = { 
                new Contributor(R.drawable.ic_launcher, "Larry Page","Vector 101", 2), 
                new Contributor(R.drawable.ic_launcher, "Dave Fontenot","Calculus", 4), 
                new Contributor(R.drawable.ic_launcher, "Alexis Ohanian","Data Structures", 5), 
                new Contributor(R.drawable.ic_launcher, "Almighty Gaben","Brewing Science", 3), 
                new Contributor(R.drawable.ic_launcher, "Buckwheat","Science Fiction", 2)};
    	
    	ArrayList<Contributor> cList = new ArrayList<Contributor>();
    	
    	
    	for(int i = 0; i < items.length; i++)
    	{
    		cList.add(items[i]);
    	}
    	ContributorAdapter adapter = new ContributorAdapter(this,R.layout.calendarlist_item, cList);
    	listView.setAdapter(adapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.calendar, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	public void openNotes(View view){
		Intent intent = new Intent(this, notesDisplay.class);
		startActivity(intent);
		
	}
}
