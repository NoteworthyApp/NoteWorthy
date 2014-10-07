package com.noteworthy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.ParseFile;

import java.io.File;
import java.nio.ByteBuffer;

public class Profile extends ActionBarActivity {
    private static int RESULT_LOAD_IMAGE = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		
		android.app.ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0099FF")));



        Button setImage = (Button)findViewById(R.id.picture);
        setImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment()).commit();
        }


	}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null!= data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            File f = new File(picturePath);
            cursor.close();

            ImageView img = (ImageView)findViewById(R.id.profilePicture);
            img.setScaleType(ImageView.ScaleType.CENTER);
            float rotate = getOrientation(Profile.this, selectedImage, picturePath);
            img.setImageBitmap(rotate(rotate, f));
        }
    }

    //Rotates the image if it is not straight
    public Bitmap rotate(float x, File file) {

        Bitmap bitmapOrg = BitmapFactory.decodeFile(file.getAbsolutePath());

        int width = bitmapOrg.getWidth();

        int height = bitmapOrg.getHeight();


        int newWidth = 1200;

        int newHeight  = 1200;

        // calculate the scale - in this case = 0.4f

        float scaleWidth = ((float) newWidth) / width;

        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();

        matrix.postScale(scaleWidth, scaleHeight);
        matrix.postRotate(x);

        Bitmap resizedBitmap = Bitmap.createBitmap(bitmapOrg, 0, 0,width, height, matrix, true);

        int numBytes = resizedBitmap.getByteCount();
        ByteBuffer buffer = ByteBuffer.allocate(numBytes);
        //resizedBitmap.copyPixelsFromBuffer(buffer);
        byte[] array = buffer.array();
        ParseFile profilePicture = new ParseFile("pro_pic.jpg", array);
        profilePicture.saveInBackground();

        return resizedBitmap;
    }

    //Rotate the image to keep it in the correct orientation
    public float getOrientation(Context context, Uri uri, String path) {
        float rotate = 0;
        try {
            context.getContentResolver().notifyChange(uri, null);
            File imageFile = new File(path);
            ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch(orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270f;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180f;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90f;
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rotate;
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_profile,
					container, false);
			return rootView;
		}
	}

}
