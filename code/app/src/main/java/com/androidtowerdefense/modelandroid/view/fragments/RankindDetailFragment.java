package com.androidtowerdefense.modelandroid.view.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.DialogCompat;
import androidx.fragment.app.DialogFragment;

import com.androidtowerdefense.R;

public class RankindDetailFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        View dialogLayout = getLayoutInflater().inflate(R.layout.ranking_detail, null);
        builder.setView(dialogLayout);
        AlertDialog dialog = builder.create();

        TextView pseudoTextDetail = dialogLayout.findViewById(R.id.pseudoDetailValue);
        TextView scoreTextDetail = dialogLayout.findViewById(R.id.scoreDetailValue);
        TextView levelTextDetail = dialogLayout.findViewById(R.id.levelDetailValue);
        TextView timeTextDetail = dialogLayout.findViewById(R.id.timeDetailValue);

        String pseudo = getArguments().getString("PSEUDO_KEY");
        String level = getArguments().getString("LEVEL_KEY");
        String score = getArguments().getString("SCORE_KEY");
        String time = getArguments().getString("TIME_KEY");

        pseudoTextDetail.setText(pseudo);
        levelTextDetail.setText(level);
        scoreTextDetail.setText(score);
        timeTextDetail.setText(time);

        return dialog;
    }
}
