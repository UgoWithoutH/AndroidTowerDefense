package com.androidtowerdefense.modelandroid.view.recycler_view;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidtowerdefense.R;

public class ViewHolderScoreRanking extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView myTextView;
    ItemClickListener itemClickListener;

    public ViewHolderScoreRanking(@NonNull View itemView) {
        super(itemView);
        myTextView = itemView.findViewById(R.id.cellText);

        itemView.setOnClickListener(this);
    }

    public TextView getMyTextView() {
        return myTextView;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        this.itemClickListener.onItemClick(this.getLayoutPosition());
    }
}
