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

import org.tjdo.dailyselfie.provider.DailySelfieContract;

import android.app.ListActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


/**
 * Main activity for the last project in the Android Handheld devices 
 * basic certification.
 * @author efmcuiti [efmcuiti@gmail.com]
 *
 */
public class DailySelfieActivity extends ListActivity implements LoaderCallbacks<Cursor> {
	
	/** Defines the parameter to be passed to the camera application. */
	private static final int REQUEST_IMAGE_CAPTURE = 1;
	
	/** Used to identify messages on logs. */
	private static final String TAG = "TJDO-DailySelfie";
	
	/** Used to temporal storage of the new selfie. */
	private String mCurrentImagePath;
	
	/** Used to temporal storage of the new selfie's name. */
	private String mCurrentImageName;
	
	/** Who will manage the list view so far. */
	private SelfieViewAdapter mAdapter;

    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 0. No external storage means that this application won't work.
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
        	Toast.makeText(
        			getApplicationContext(), 
        			"External storage not available.", 
        			Toast.LENGTH_LONG).show();
        	finish();
        }
        
        // 1. Setting the customized layout list view.
        setContentView(R.layout.activity_daily_selfie);
        
        // 2. Adding the adapter for the visual rows.
        mAdapter = new SelfieViewAdapter(getApplicationContext(), null, 0);
        setListAdapter(mAdapter);
        
        // 3. Starting the cursor loader.
        getLoaderManager().initLoader(0, null, this);
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
				mCurrentImagePath = imgFile.getAbsolutePath();
				mCurrentImageName = imgFile.getName();
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
			Log.i(TAG, String.format("Selfie taken! %s", mCurrentImagePath));
			SelfieRecord sr = new SelfieRecord(mCurrentImagePath, null, mCurrentImageName);
			// Adding the new element to the view.
			mAdapter.add(sr);
		}
	}


	/* (non-Javadoc)
	 * @see android.app.LoaderManager.LoaderCallbacks#onCreateLoader(int, android.os.Bundle)
	 */
	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		// 1. Setting up the new cursor loader.
		String columns[] = new String[] {
				DailySelfieContract._ID,
				DailySelfieContract.SELFIE_BITMAP_PATH, 
				DailySelfieContract.SELFIE_NAME
		};
		return new CursorLoader(getApplicationContext(), 
				DailySelfieContract.CONTENT_URI, columns, null, null, null);
	}


	/* (non-Javadoc)
	 * @see android.app.LoaderManager.LoaderCallbacks#onLoadFinished(android.content.Loader, java.lang.Object)
	 */
	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		mAdapter.swapCursor(data);
	}


	/* (non-Javadoc)
	 * @see android.app.LoaderManager.LoaderCallbacks#onLoaderReset(android.content.Loader)
	 */
	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		mAdapter.swapCursor(null);
	}
}
