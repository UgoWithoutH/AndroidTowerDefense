package com.androidtowerdefense.modelandroid.view.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidtowerdefense.R;

public class ViewHolderScoreRanking extends RecyclerView.ViewHolder {

    private TextView myTextView;

    public ViewHolderScoreRanking(@NonNull View itemView) {
        super(itemView);
        myTextView = itemView.findViewById(R.id.cellText);
    }

    public TextView getMyTextView() {
        return myTextView;
    }
}
