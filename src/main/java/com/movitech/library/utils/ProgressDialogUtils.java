package com.movitech.library.utils;

import com.movitech.library.R;
import com.movitech.library.widget.GifView;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 加载框工具类
 * @author Nick.Wu
 * @version 2016-3-29
 *
 */
public class ProgressDialogUtils extends ProgressDialog {

	private static ProgressDialogUtils mProgressDialog = null;
	private GifView mGifView;
	private static TextView mLoadTextView;

	public ProgressDialogUtils(Context context) {
		super(context);
	}
	
	public static ProgressDialogUtils getInstance(Context context) {
		if (mProgressDialog == null) {
			mProgressDialog = new ProgressDialogUtils(context);
			mProgressDialog.setOnDismissListener(new OnDismissListener() {
				
				@Override
				public void onDismiss(DialogInterface dialog) {
					if (mProgressDialog != null) {
						mProgressDialog.dismiss();
						mProgressDialog = null;
					}
				}
			});
		}
		return mProgressDialog;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.load_progress_dialog);
		
		mGifView = (GifView) findViewById(R.id.gif);
		mGifView.setMovieResource(R.raw.loading);
		mLoadTextView = (TextView) findViewById(R.id.load_tv);
		mLoadTextView.setText(R.string.loading_str);
	}
	
	public static void showPorgressDialog(Context context) {
		if (context != null) {
			mProgressDialog = ProgressDialogUtils.getInstance(context);
			mProgressDialog.setCanceledOnTouchOutside(false);
			mProgressDialog.show();
		}
	}
	
	public static void dismissProgressDialog() {
		if (mProgressDialog != null) {
			mProgressDialog.dismiss();
			mProgressDialog = null;
		}
	}
}
