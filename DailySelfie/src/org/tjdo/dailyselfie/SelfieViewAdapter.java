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

}
