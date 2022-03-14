package com.androidtowerdefense.modelandroid.view.alert_dialog;

import android.app.Dialog;
import android.app.GameManager;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.androidtowerdefense.R;

public class MyDialogFragment extends DialogFragment {

    //GameManager gm = new GameManager();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.continueGame)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {
                    }

                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        return builder.create();
    }
}
