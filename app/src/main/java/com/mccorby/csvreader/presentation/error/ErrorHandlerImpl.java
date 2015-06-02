package com.mccorby.csvreader.presentation.error;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by JAC on 02/06/2015.
 */
public class ErrorHandlerImpl implements ErrorHandler {

    private Activity mActivity;

    public ErrorHandlerImpl(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    public void displayError(final String message) {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(mActivity, message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
