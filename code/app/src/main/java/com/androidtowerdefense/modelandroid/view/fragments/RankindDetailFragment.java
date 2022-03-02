package com.androidtowerdefense.modelandroid.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.androidtowerdefense.R;

public class RankindDetailFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.ranking_detail,container,false);

        TextView pseudoTextDetail = root.findViewById(R.id.pseudoDetailValue);
        TextView scoreTextDetail = root.findViewById(R.id.scoreDetailValue);
        TextView levelTextDetail = root.findViewById(R.id.levelDetailValue);
        TextView timeTextDetail = root.findViewById(R.id.timeDetailValue);

        String pseudo = this.getArguments().getString("PSEUDO_KEY");
        String level = this.getArguments().getString("LEVEL_KEY");
        String score = this.getArguments().getString("SCORE_KEY");
        String time = this.getArguments().getString("TIME_KEY");

        pseudoTextDetail.setText(pseudo);
        levelTextDetail.setText(level);
        scoreTextDetail.setText(score);
        timeTextDetail.setText(time);

        return root;
    }
}
