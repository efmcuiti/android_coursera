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
	private String selfieBitmapPath;
	
	/** The actual byte-data content. */
	private Bitmap selfieBitmap;
	
	/** Name for the actual photo. */
	private String selfieName;
	
	/**
	 * Full powered constructor for the class.
	 * @param selfieBitmapPath Where to find the image.
	 * @param selfieBitmap Actual byte contents for the image.
	 * @param selfieName Name of the photo.
	 */
	public SelfieRecord(String selfieBitmapPath, Bitmap selfieBitmap, String selfieName) {
		this.selfieBitmapPath = selfieBitmapPath;
		this.selfieBitmap = selfieBitmap;
		this.selfieName = selfieName;
	}
	
	/**
	 * Default constructor for the class.
	 */
	public SelfieRecord() {
	}

	/**
	 * @return the mSelfieBitmapPath
	 */
	public String getSelfieBitmapPath() {
		return selfieBitmapPath;
	}

	/**
	 * @param mSelfieBitmapPath the mSelfieBitmapPath to set
	 */
	public void setSelfieBitmapPath(String mSelfieBitmapPath) {
		this.selfieBitmapPath = mSelfieBitmapPath;
	}

	/**
	 * @return the mSelfieBitmap
	 */
	public Bitmap getSelfieBitmap() {
		return selfieBitmap;
	}

	/**
	 * @param mSelfieBitmap the mSelfieBitmap to set
	 */
	public void setSelfieBitmap(Bitmap mSelfieBitmap) {
		this.selfieBitmap = mSelfieBitmap;
	}

	/**
	 * @return the mSelfieName
	 */
	public String getSelfieName() {
		return selfieName;
	}

	/**
	 * @param mSelfieName the mSelfieName to set
	 */
	public void setSelfieName(String mSelfieName) {
		this.selfieName = mSelfieName;
	}
}
