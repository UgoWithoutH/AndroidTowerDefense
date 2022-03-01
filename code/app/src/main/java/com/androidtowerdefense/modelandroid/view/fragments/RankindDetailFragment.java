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

        TextView pseudoTextDetail = root.findViewById(R.id.pseudoDetail);

        String pseudo = this.getArguments().getString("PSEUDO_KEY");

        pseudoTextDetail.setText(pseudo);

        return root;
    }
}
