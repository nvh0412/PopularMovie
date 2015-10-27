package com.udacity.hoanv.popularmovie;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * Created by HoaNV on 9/7/15.
 */
public class ProgressDialogFragment extends DialogFragment {

    public static ProgressDialogFragment getInstance(){
        return new ProgressDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //Create progress dialog
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        //Set message
        progressDialog.setMessage("Loading...");

        progressDialog.setIndeterminate(true);

        return progressDialog;
    }

}
