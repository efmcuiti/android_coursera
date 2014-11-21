/**
 * MoreInfoDialogFragment.java
 * Crated on: Oct 27, 2014
 * For the sole purpose of the Just DO! ussage.
 * All rights reserved.
 */
package org.thejustdo.modernartui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

/**
 * This class builds a basic alert dialog to 
 * Handle the whish from the user to get more information
 * about <code>MOMA</code>
 * @author edimende
 */
public class MoreInfoDialogFragment extends DialogFragment {
	
	/** Where to send the user when chooses to visit the MOMA web site. */
	private static final String _MOMA_URL = "http://moma.org";

	/* (non-Javadoc)
	 * @see android.app.DialogFragment#onCreateDialog(android.os.Bundle)
	 */
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// 1. Creating a basic alert dialog.
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage(R.string.message)
			.setPositiveButton(R.string.visit_moma, 
					new OnClickListener() {
						
						/* (non-Javadoc)
						 * @see android.content.DialogInterface.OnClickListener#onClick(android.content.DialogInterface, int)
						 */
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// build the intent for a web page browsing.
							Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(_MOMA_URL));
							startActivity(intent);
						}
					})
			.setNegativeButton(R.string.cancel, new OnClickListener() {
				
				/* (non-Javadoc)
				 * @see android.content.DialogInterface.OnClickListener#onClick(android.content.DialogInterface, int)
				 */
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Log.i(getTag(), "User cancelled this dialog.");
				}
			});
		return builder.create();
	}
}
