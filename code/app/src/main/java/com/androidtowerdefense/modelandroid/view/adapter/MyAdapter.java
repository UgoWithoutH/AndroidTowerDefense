package com.androidtowerdefense.modelandroid.view.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.androidtowerdefense.R;
import com.androidtowerdefense.model.ScoreRanking;
import com.androidtowerdefense.model.gamelogic.GameState;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter {

    private AppCompatActivity parentActivity;
    private ScoreRanking scoreRanking;

    public MyAdapter(AppCompatActivity parentActivity, ScoreRanking scoreRanking) {
        this.parentActivity = parentActivity;
        this.scoreRanking = scoreRanking;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View patternView = parentActivity.getLayoutInflater().inflate(R.layout.score_ranking_cell,parent,false);
        return new ViewHolderScoreRanking(patternView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        GameState gameState = scoreRanking.getRanking().get(position);
        ((ViewHolderScoreRanking)holder).getMyTextView().setText(gameState.getPseudo());
    }

    @Override
    public int getItemCount() {
        return scoreRanking.getRanking().size();
    }
}
