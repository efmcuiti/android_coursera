/**
 * SelfieViewAdapter.java
 * Created on: Nov 25, 2014
 * For the sole purpose of the Just DO! ussage.
 * All rights reserved.
 */
package org.tjdo.dailyselfie;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;

/**
 * Defines a class to manage the view for adding new selfies.
 * @author efmcuiti
 *
 */
public class SelfieViewAdapter extends CursorAdapter {

	/** Where inside the application to save the photos. */
	private static final String APP_DIR = "TJDODailySelfies/Selfies";
	
	/** Set of all taken daily photos. */
	private ArrayList<SelfieRecord> mSelfies = new ArrayList<SelfieRecord>();
	
	/** Used to inflate new pictures in the list view. */
	private static LayoutInflater sLayoutInflater = null;
	
	/** Application environment. */
	private Context mContext;
	
	/** Where will be all the photos being saved. */
	private String mSelfiesStoragePath;
	
	/**
	 * Used to manage the rows inside the list view.
	 * @author efmcuiti
	 *
	 */
	static class ViewHolder {
		/** Holds a thumb to be displayed on the list view. */
		ImageView thumbnail;
		/** Text to show at the side of the thumb. */
		String selfieName;
	}
	
	/**
	 * Default constructor for the class.
	 * @param context Application context to work with.
	 * @param c Cursor to work with (have all records from database).
	 * @param flags Don't know what are these for.
	 */
	public SelfieViewAdapter(Context context, Cursor c, int flags) {
		super(context, c, flags);
		// Initialize the local variables.
		this.mContext = context;
		sLayoutInflater = LayoutInflater.from(mContext);
		
		// TODO add the new logic for media validation.
	}

	/* (non-Javadoc)
	 * @see android.widget.CursorAdapter#newView(android.content.Context, android.database.Cursor, android.view.ViewGroup)
	 */
	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see android.widget.CursorAdapter#bindView(android.view.View, android.content.Context, android.database.Cursor)
	 */
	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see android.widget.CursorAdapter#swapCursor(android.database.Cursor)
	 */
	@Override
	public Cursor swapCursor(Cursor newCursor) {
		// 1. Clear all previous content.
		if (null != newCursor) {
			mSelfies.clear();
			if (newCursor.moveToFirst()) {
				// TODO add the new elements to the view.
			}
		}
		return super.swapCursor(newCursor);
	}
	
	/**
	 * Adds a new row view containing a picture.
	 * @param selfie New element to be added to the list.
	 */
	public void add(SelfieRecord selfie) {
		// TODO add the add new logic.
	}
}
