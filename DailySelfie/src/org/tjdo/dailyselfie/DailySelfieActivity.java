/**
 * DailySelfieActivity.java
 * Created on: November, 2014
 * For the sole purpose of the Just DO! Usage.
 * All rights reserved.
 */
package org.tjdo.dailyselfie;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


/**
 * Main activity for the last project in the Android Handheld devices 
 * basic certification.
 * @author efmcuiti [efmcuiti@gmail.com]
 *
 */
public class DailySelfieActivity extends ListActivity {
	
	/** Defines the parameter to be passed to the camera application. */
	private static final int REQUEST_IMAGE_CAPTURE = 1;
	
	/** Used to identify messages on logs. */
	private static final String TAG = "TJDO-DailySelfie";
	
	/** Used to temporal storage of the new selfie. */
	private String mCurrentImagePath;

    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 1. Setting the customized layout list view.
        setContentView(R.layout.activity_daily_selfie);
    }


    /* (non-Javadoc)
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.daily_selfie, menu);
        return true;
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_camera) {
        	dispatchTakePicture();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


	/**
	 * Executes the camera application for photo taking.
	 */
	private void dispatchTakePicture() {
		Intent takePic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if (takePic.resolveActivity(getPackageManager()) != null) {
			// 1. Configuring the path where the actual picture will be stored.
			File imgFile = null;
			
			try {
				imgFile = createImageFile();
				// Updating the current image path.
				mCurrentImagePath = "file:" + imgFile.getAbsolutePath();
			} catch (IOException e) {
				// Error saving image files.
				Log.e(TAG, String.format("Couldn't create temporal image file!"), e);
				return;
			}
			
			// 2. Setting where to save the picture and starting the intent.
			if (imgFile != null) {
				Log.i(TAG, String.format("The new picture will be saved here %s", mCurrentImagePath));
				takePic.putExtra(MediaStore.EXTRA_OUTPUT, 
						Uri.fromFile(imgFile));
				startActivityForResult(takePic, REQUEST_IMAGE_CAPTURE);
			}
		}
	}
	
	/**
	 * Builds a temporal file where the new image will be stored.
	 * @return The temporal file.
	 * @throws IOException When the operation can't be performed.
	 */
	private File createImageFile() throws IOException {
		// The new filename is created from a date time stamp.
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
		String imageFileName = "SELFIE_" + timeStamp + "_";
		File storageDir = getExternalFilesDir(null);
		
		// Building the answer object.
		File image = File.createTempFile(imageFileName, ".jpg", storageDir);
		return image;
	}


	/* (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Getting the taken image.
		if ((requestCode == REQUEST_IMAGE_CAPTURE) && (resultCode == RESULT_OK)) {
			if (data == null) {
				Log.e(TAG, "Fatal error, data is null.");
				return;
			}
			Bundle extras = data.getExtras();
			Bitmap thumbnail = (Bitmap) extras.get("data");
			Log.i(TAG, String.format("Selfie taken! %s", thumbnail.getByteCount()));
		}
	}
}
