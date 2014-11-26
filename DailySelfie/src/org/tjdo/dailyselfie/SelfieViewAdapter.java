/**
 * SelfieViewAdapter.java
 * Created on: Nov 25, 2014
 * For the sole purpose of the Just DO! ussage.
 * All rights reserved.
 */
package org.tjdo.dailyselfie;

import java.util.ArrayList;

import org.tjdo.dailyselfie.provider.DailySelfieContract;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Defines a class to manage the view for adding new selfies.
 * @author efmcuiti
 *
 */
public class SelfieViewAdapter extends CursorAdapter {
	
	/** The height for a thumb to be used in the list. */
	private static final int THUMB_HEIGHT = 160;
	
	/** The width for a thumb to be used in the list. */
	private static final int THUMB_WIDTH = 120;
	
	/** Set of all taken daily photos. */
	private ArrayList<SelfieRecord> mSelfies = new ArrayList<SelfieRecord>();
	
	/** Used to inflate new pictures in the list view. */
	private static LayoutInflater sLayoutInflater = null;
	
	/** Application environment. */
	private Context mContext;
	
	/**
	 * Used to manage the rows inside the list view.
	 * @author efmcuiti
	 *
	 */
	static class ViewHolder {
		/** Holds a thumb to be displayed on the list view. */
		ImageView thumbnail;
		/** Text to show at the side of the thumb. */
		TextView selfieName;
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
	}

	/* (non-Javadoc)
	 * @see android.widget.CursorAdapter#newView(android.content.Context, android.database.Cursor, android.view.ViewGroup)
	 */
	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		// 1. Build a new data container.
		ViewHolder holder = new ViewHolder();
		View newRow = sLayoutInflater.inflate(R.layout.new_selfie_row, parent, false);
		
		// 2. Building empty components to the view holder.
		holder.thumbnail = (ImageView) newRow.findViewById(R.id.thumbnail);
		holder.selfieName = (TextView) newRow.findViewById(R.id.selfieName);
		
		// 3. Adding it to the container.
		newRow.setTag(holder);
		
		return newRow;
	}

	/* (non-Javadoc)
	 * @see android.widget.CursorAdapter#bindView(android.view.View, android.content.Context, android.database.Cursor)
	 */
	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		// 1. Getting the object where the new row must be configured.
		ViewHolder holder = (ViewHolder) view.getTag();
		
		// 2. All the elements for the new row comes from the current 
		// cursor position.
		holder.thumbnail.setImageBitmap(
				getThumbnailBitmap(cursor.getString(cursor.getColumnIndex(DailySelfieContract.SELFIE_BITMAP_PATH))));
		holder.selfieName.setText(cursor.getString(cursor.getColumnIndex(DailySelfieContract.SELFIE_NAME)));

	}
	
	/**
	 * Given a path to a full sized picture, this method 
	 * returns a Bitmap scaled to match the default thumb
	 * bounds.
	 * @param imagePath Where to find the whole-sized picture.
	 * @return The scaled decoded image.
	 */
	private Bitmap getThumbnailBitmap(String imagePath) {
		// 1. The options shall be configured so a thumbnail is 
		// returned instead of the whole image.
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.outHeight = THUMB_HEIGHT;
		options.outWidth = THUMB_WIDTH;
		
		return BitmapFactory.decodeFile(imagePath, options);
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
				do {
					mSelfies.add(getSelfieRecordFromCursor(newCursor));
				} while(newCursor.moveToNext());
				notifyDataSetChanged();
			}
		}
		return super.swapCursor(newCursor);
	}
	
	/**
	 * Adds a new row view containing a picture.
	 * @param selfie New element to be added to the list.
	 */
	public void add(SelfieRecord selfie) {
		if (null == selfie) {
			return;
		}
		
		// 1. Building and adding the new selfie to the content 
		// provider.
		mSelfies.add(selfie);
		ContentValues values = new ContentValues();
		
		// 1.1. Building the values map to be added to the content provider.
		values.put(DailySelfieContract.SELFIE_BITMAP_PATH, selfie.getSelfieBitmapPath());
		values.put(DailySelfieContract.SELFIE_NAME, selfie.getSelfieName());
		
		// 1.2. Adding the new photo to the content provider.
		ContentResolver resolver = mContext.getContentResolver();
		resolver.insert(DailySelfieContract.CONTENT_URI, values);
	}
	
	/**
	 * Given a cursor pointed to an actual record, this method 
	 * builds a new DTO so it can be used later on inside de application.
	 * @param c Cursor pointing to the current record.
	 * @return The DTO built based on cursor current values.
	 */
	private SelfieRecord getSelfieRecordFromCursor(Cursor c) {
		SelfieRecord selfie = new SelfieRecord();
		
		// 1. Building each record field.
		selfie.setSelfieBitmapPath(
				c.getString(
						c.getColumnIndex(
								DailySelfieContract.SELFIE_BITMAP_PATH)));
		selfie.setSelfieName(
				c.getString(
						c.getColumnIndex(
								DailySelfieContract.SELFIE_NAME)));
		
		return selfie;
	}
	
	/**
	 * Returns the selfie at a given position.
	 * @param position Where is the selfie located.
	 * @return If any pic at all.
	 */
	public SelfieRecord getSelfieAt(int position) {
		return mSelfies.get(position);
	}
}
