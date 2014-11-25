/**
 * SelfieRecord.java
 * Created on: Nov 25, 2014
 * For the sole purpose of the Just DO! ussage.
 * All rights reserved.
 */
package org.tjdo.dailyselfie;

import android.graphics.Bitmap;

/**
 * DTO like object for pictures management.
 * @author efmcuiti
 *
 */
public class SelfieRecord {

	/** Where to find the real photo taken. */
	private String mSelfieBitmapPath;
	
	/** The actual byte-data content. */
	private Bitmap mSelfieBitmap;
	
	/** Name for the actual photo. */
	private String mSelfieName;
	
	/**
	 * Full powered constructor for the class.
	 * @param selfieBitmapPath Where to find the image.
	 * @param selfieBitmap Actual byte contents for the image.
	 * @param selfieName Name of the photo.
	 */
	public SelfieRecord(String selfieBitmapPath, Bitmap selfieBitmap, String selfieName) {
		this.mSelfieBitmapPath = selfieBitmapPath;
		this.mSelfieBitmap = selfieBitmap;
		this.mSelfieName = selfieName;
	}
	
	/**
	 * Default constructor for the class.
	 */
	public SelfieRecord() {
	}

	/**
	 * @return the mSelfieBitmapPath
	 */
	public String getmSelfieBitmapPath() {
		return mSelfieBitmapPath;
	}

	/**
	 * @param mSelfieBitmapPath the mSelfieBitmapPath to set
	 */
	public void setmSelfieBitmapPath(String mSelfieBitmapPath) {
		this.mSelfieBitmapPath = mSelfieBitmapPath;
	}

	/**
	 * @return the mSelfieBitmap
	 */
	public Bitmap getmSelfieBitmap() {
		return mSelfieBitmap;
	}

	/**
	 * @param mSelfieBitmap the mSelfieBitmap to set
	 */
	public void setmSelfieBitmap(Bitmap mSelfieBitmap) {
		this.mSelfieBitmap = mSelfieBitmap;
	}

	/**
	 * @return the mSelfieName
	 */
	public String getmSelfieName() {
		return mSelfieName;
	}

	/**
	 * @param mSelfieName the mSelfieName to set
	 */
	public void setmSelfieName(String mSelfieName) {
		this.mSelfieName = mSelfieName;
	}
}
