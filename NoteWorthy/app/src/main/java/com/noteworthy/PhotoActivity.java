package com.noteworthy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
 
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.util.Base64;
import android.util.Log;
 
public class PhotoActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo);
		dispatchTakePictureIntent();//must be put in with a specific method, will repeat in oncreate
	}
	
	private void dispatchTakePictureIntent() {
	    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
	        startActivityForResult(takePictureIntent, 1);
	    }
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		postToServer(data.getData());
		Intent intent = new Intent(this, NoteDescription.class);
    	startActivity(intent);
	}
	
	private String getPath(Uri uri)
	{
		String[] data = { MediaStore.Images.Media.DATA };
		CursorLoader loader = new CursorLoader(getBaseContext(), uri, data, null, null, null);
		Cursor cursor = loader.loadInBackground();
		int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}
	
	private void postToServer(final Uri input)
	{
		final String loc = getPath(input);
		Thread thread = new Thread(new Runnable(){
		  @Override
		  public void run(){
			  
			  List<PostParameter> params = new ArrayList<PostParameter>();
			  params.add(new PostParameter<File>("file", new File(loc)));
			  
			  MultipartPost post = new MultipartPost(params);
			  try {
				post.send("http://noteworthy.cloudapp.net");
			} catch (Exception e) {
				Log.d("fuckAndroid", e.toString());
			}
			  
		  }
		});
		thread.start();
	}
	
}
