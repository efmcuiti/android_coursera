/**
 * ModernArtActivity.java
 * Created on: October 22, 2014
 * Created for training purpose. All rights reserved for 
 * The Just DO!
 */
package org.thejustdo.modernartui;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

/**
 * Manages the main activity for the <code>MOMA</code> 
 * android mini-project.
 * @author efmcuiti [efmcuiti@gmail.com]
 */
public class ModernArtActivity extends Activity {
	
	/** Used for easy messages and identification. */
	private final String TAG = "TJDOModernART";
	
	/** Defines the RGB color for the red_box. */
	private static int red[] = new int[]{170, 0, 0};
	
	/** Defines the RGB color for the green_box. */
	private static int green[] = new int[]{2, 142, 116};
	
	/** Defines the RGB color for the blue_box. */
	private static int blue[] = new int[]{30, 104, 191};
	
	/** Defines the RGB color for the yellow_box. */
	private static int yellow[] = new int[]{255, 140, 3};
	
	/** Defines the RGB color for the pink_box. */
	private static int pink[] = new int[]{242, 225, 209};

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// 1. Mandatory invocation to the super class.
		super.onCreate(savedInstanceState);
		
		// 2. Setting the main UI component.
		setContentView(R.layout.activity_modern_art);
		
		// 3. Setting the listener for the seek bar.
		SeekBar colorChanger = (SeekBar) findViewById(R.id.color_changer);
		colorChanger.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				Log.i(TAG, "Stopped the tracking 'o touch.");
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				Log.i(TAG, "Started the tracking 'o touch.");
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// 1. Get the red_box component.
				TextView red_box = (TextView) findViewById(R.id.red_box);
				// 1.2 Get the Drawable component and modify its boxes colors.
				GradientDrawable drawable = (GradientDrawable) red_box.getBackground();
				drawable.setColor(Color.rgb(red[0], red[1] + progress, red[2] + progress));
				
				// 2. Get the green_box component.
				TextView green_box = (TextView) findViewById(R.id.green_box);
				// 2.2. Get the Drawable component and modify its colors.
				GradientDrawable dGreen = (GradientDrawable) green_box.getBackground();
				dGreen.setColor(Color.rgb(green[0] + progress, green[1], green[2] + progress));
				
				// 3. Get the blue_box component.
				TextView blue_box = (TextView) findViewById(R.id.blue_box);
				// 3.2. Get the Drawable component and modify its colors.
				GradientDrawable dBlue = (GradientDrawable) blue_box.getBackground();
				dBlue.setColor(Color.rgb(blue[0] + progress, blue[1] + progress, green[2]));
				
				// 4. Get the yellow_box component.
				TextView yellow_box = (TextView) findViewById(R.id.yellow_box);
				// 4.2. Get the Drawable component and modify its colors.
				GradientDrawable dYellow = (GradientDrawable) yellow_box.getBackground();
				dYellow.setColor(Color.rgb(yellow[0], yellow[1] + progress, yellow[2] + progress));
				
				// 5. Get the pink_box component.
				TextView pink_box = (TextView) findViewById(R.id.pink_box);
				// 5.2. Get the Drawable component and modify its colors.
				GradientDrawable dPink = (GradientDrawable) pink_box.getBackground();
				dPink.setColor(Color.rgb(pink[0], pink[1] - progress, pink[2] - progress));
			}
		});
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// 1. Setting up the menu option.
		item.setOnMenuItemClickListener(new OnMenuItemClickListener() {
		
			/* (non-Javadoc)
			 * @see android.view.MenuItem.OnMenuItemClickListener#onMenuItemClick(android.view.MenuItem)
			 */
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				MoreInfoDialogFragment dialog = new MoreInfoDialogFragment();
				dialog.show(getFragmentManager(), TAG);
				return true;
			}
		});
		return super.onOptionsItemSelected(item);
	}
}
