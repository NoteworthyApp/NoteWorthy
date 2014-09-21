
package com.noteworthy;
 
import java.util.ArrayList;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.MenuItem;
 
public class notesDisplay extends FragmentActivity{
 
	
	HackyViewPager pager;
	
	private ImageLoader imageLoader = ImageLoader.getInstance();
	
	private ArrayList<String> mediaUrls = new ArrayList<String>();
	
	
	
	
	
	private MediaAdapter mediaAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notesdisplay);
		
		mediaUrls.add("http://i.imgur.com/mrFiu7z.png");
		mediaUrls.add("http://i.imgur.com/mrFiu7z.png");
		mediaUrls.add("http://i.imgur.com/mrFiu7z.png");
		mediaUrls.add("http://i.imgur.com/mrFiu7z.png");
		
		 mediaAdapter = new MediaAdapter(getSupportFragmentManager(), mediaUrls.size());
		 
		 
		 
		 pager = (HackyViewPager) findViewById(R.id.pager);
		 
		 pager.setAdapter(mediaAdapter);
		 
		 pager.setCurrentItem(0);
		 
		 android.app.ActionBar bar = getActionBar();
	        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0099FF")));
		 
	}
	
	
	private class MediaAdapter extends FragmentStatePagerAdapter
	{
        public MediaAdapter(FragmentManager fm, int size) {
            super(fm);
            mediaUrls.size();
            //CHANGED2
        }
 
        @Override
        public Fragment getItem(final int position)
        {
        	Log.d("showmepls", ""+position);
            String media = mediaUrls.get(position);
            return ImageFragment.newInstance(position, media);
        }
 
        @Override
        public int getCount()
        {
            return mediaUrls.size();
        }
 
    }

	public boolean onOptionsItemsSelected(MenuItem item){
			 int id = item.getItemId();

			 if (id == R.id.thumbsup) {
		        	Intent i = new Intent(this, Profile.class);
		        	startActivity(i);
		            return true;
		        }
		        return super.onOptionsItemSelected(item);
	}
}