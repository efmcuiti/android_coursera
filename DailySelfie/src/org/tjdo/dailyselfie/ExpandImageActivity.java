/**
 * ExpandingImageActivity.java
 * Created on: November, 2014
 * For the sole purpose of the Just DO! Usage.
 * All rights reserved.
 */
package org.tjdo.dailyselfie;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * Given a call from another activity, this one shows 
 * a full sized picture and that's all.
 * @author efmcuiti
 *
 */
public class ExpandImageActivity extends Activity {

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expand_image);
		
		// Hiding menu.
		getActionBar().hide();
		
		// Getting the image to be shown.
		Bundle extras = this.getIntent().getExtras();
		String imgPath = extras.getString(DailySelfieActivity.SELFIE_FULL_PATH);
		
		// Showing image if there is something to show.
		if ((null == imgPath) 
				|| ("".equalsIgnoreCase(imgPath.trim()))) {
			setResult(RESULT_CANCELED);
			finish();
		}
		
		ImageView img = (ImageView) findViewById(R.id.fullSizeSelfie);
		img.setImageBitmap(BitmapFactory.decodeFile(imgPath));
		
		// If the image is clicked, this activity should return.
		img.setOnClickListener(new OnClickListener() {
			
			/* (non-Javadoc)
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			@Override
			public void onClick(View v) {
				setResult(RESULT_OK);
				finish();
			}
		});
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expand_image, menu);
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
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
