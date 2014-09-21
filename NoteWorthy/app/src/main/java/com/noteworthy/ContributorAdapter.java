package com.noteworthy;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ContributorAdapter extends ArrayAdapter<Contributor> {
    
	 
	ArrayList<Contributor> contributors;
	
	public ContributorAdapter(Context context, int calendarListItem, ArrayList<Contributor> contributors) {
		
		super(context, calendarListItem, contributors);
		this.contributors = contributors;
		
    }
    
	@Override
	public Contributor getItem(int position) {
		// TODO Auto-generated method stub
		return contributors.get(position);
	}
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       
       Contributor contributor = getItem(position);    
       Log.d("pos", position+"");
       // Check if an existing view is being reused, otherwise inflate the view
       if (convertView == null) {
          convertView = LayoutInflater.from(getContext()).inflate(R.layout.calendarlist_item, parent, false);
       }
       // Lookup view for data population
       TextView cName = (TextView) convertView.findViewById(R.id.nameLine);
       TextView cCon = (TextView) convertView.findViewById(R.id.ContributionLine);
       ImageView cPic = (ImageView) convertView.findViewById(R.id.profilePicture);
       // Populate the data into the template view using the data object
       cName.setText(contributor.name);
       cCon.setText(contributor.contributionClass);
       cPic.setImageResource(contributor.pictureResource);
       
       // Return the completed view to render on screen
       return convertView;
   }
}
