/**
 * ModernArtActivity.java
 * Created on: October 22, 2014
 * Created for training purpose. All rights reserved for 
 * The Just DO!
 */
package org.thejustdo.modernartui;

import android.app.Activity;
import android.os.Bundle;

/**
 * Manages the main activity for the <code>MOMA</code> 
 * android mini-project.
 * @author efmcuiti [efmcuiti@gmail.com]
 */
public class ModernArtActivity extends Activity {
	
	/** Used for easy messages and identification. */
	private final String TAG = "TJDOModernART";

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// 1. Mandatory invocation to the super class.
		super.onCreate(savedInstanceState);
		
		// 2. Setting the main UI component.
		setContentView(R.layout.activity_modern_art);
	}
}
