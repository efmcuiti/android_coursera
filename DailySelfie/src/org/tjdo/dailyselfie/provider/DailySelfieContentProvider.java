/**
 * DailySelfieContentProvider.java
 * Created on: Nov 25, 2014
 * For the sole purpose of the Just DO! ussage.
 * All rights reserved.
 */
package org.tjdo.dailyselfie.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/**
 * Provides all related to storage management for 
 * daily pictures.
 * @author efmcuiti
 *
 */
public class DailySelfieContentProvider extends ContentProvider {

	/** Used to know what kind of database we're using. */
	private static final int DATABASE_VERSION = 1;
	
	/** Name of the physical database to be used. */
	private static final String DATABASE_NAME = "tjdoselfies";
	
	/** SQL query to build the table. */
	private static final String CREATE_SELFIES_TABLE_RAW = "CREATE TABLE %s (" +
			"%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
			"%s TEXT NOT NULL, " +
			"%s TEXT NOT NULL);";
	/** Query to build table with real values replaced. */
	private static final String CREATE_SELFIES_TABLE = 
			String.format(CREATE_SELFIES_TABLE_RAW, 
					DailySelfieContract.SELFIES_TABLE_NAME, 
					DailySelfieContract._ID,
					DailySelfieContract.SELFIE_BITMAP_PATH,
					DailySelfieContract.SELFIE_NAME);
	
	/** Used to manage persistence layer. */
	private SelfiesDatabaseHelper mDBHelper;
	
	/**
	 * Used to handle communications with the database.
	 * @author efmcuiti
	 *
	 */
	private static class SelfiesDatabaseHelper extends SQLiteOpenHelper {

		/**
		 * Default constructor for the class.
		 */
		public SelfiesDatabaseHelper(Context c) {
			super(c, DATABASE_NAME, null, DATABASE_VERSION);
		}
		
		/* (non-Javadoc)
		 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
		 */
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_SELFIES_TABLE);
		}

		/* (non-Javadoc)
		 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
		 */
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + DailySelfieContract.SELFIES_TABLE_NAME);
			onCreate(db);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see android.content.ContentProvider#onCreate()
	 */
	@Override
	public boolean onCreate() {
		mDBHelper = new SelfiesDatabaseHelper(getContext());
		return (mDBHelper != null);
	}

	/* (non-Javadoc)
	 * @see android.content.ContentProvider#query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String)
	 */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		qb.setTables(DailySelfieContract.SELFIES_TABLE_NAME);
		
		Cursor c = qb.query(mDBHelper.getWritableDatabase(), projection, selection, 
				selectionArgs, null, null, sortOrder);
		
		c.setNotificationUri(getContext().getContentResolver(), uri);
		
		return c;
	}

	/* (non-Javadoc)
	 * @see android.content.ContentProvider#getType(android.net.Uri)
	 */
	@Override
	public String getType(Uri uri) {
		// Not implemented.
		return null;
	}

	/* (non-Javadoc)
	 * @see android.content.ContentProvider#insert(android.net.Uri, android.content.ContentValues)
	 */
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		long rowID = mDBHelper.getWritableDatabase().insert(
				DailySelfieContract.SELFIES_TABLE_NAME, "", values);
		if (rowID > 0) {
			Uri fullURI = ContentUris.withAppendedId(
					DailySelfieContract.CONTENT_URI, rowID);
			getContext().getContentResolver().notifyChange(fullURI, null);
			return fullURI;
		}
		throw new SQLException("Failed to add a new selfie into " + uri);
	}

	/* (non-Javadoc)
	 * @see android.content.ContentProvider#delete(android.net.Uri, java.lang.String, java.lang.String[])
	 */
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int deletedRows = mDBHelper.getWritableDatabase().delete(
				DailySelfieContract.SELFIES_TABLE_NAME, null, null);
		getContext().getContentResolver().notifyChange(
				DailySelfieContract.CONTENT_URI, null);
		return deletedRows;
	}

	/* (non-Javadoc)
	 * @see android.content.ContentProvider#update(android.net.Uri, android.content.ContentValues, java.lang.String, java.lang.String[])
	 */
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// Not implemented.
		return 0;
	}

}
