package com.androidtowerdefense.modelandroid.view.recycler_view;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidtowerdefense.R;
import com.androidtowerdefense.model.gamelogic.GameState;
import com.androidtowerdefense.modelandroid.view.fragments.RankindDetailFragment;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter {

    private AppCompatActivity parentActivity;
    private List<GameState> ranking;
    private FragmentManager fragmentManager;

    public MyAdapter(AppCompatActivity parentActivity, List<GameState> ranking, FragmentManager supportFragmentManager) {
        this.parentActivity = parentActivity;
        this.ranking = ranking;
        this.fragmentManager = supportFragmentManager;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View patternView = parentActivity.getLayoutInflater().inflate(R.layout.score_ranking_cell,parent,false);
        return new ViewHolderScoreRanking(patternView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        GameState gameState = ranking.get(position);
        ((ViewHolderScoreRanking)holder).getMyTextView().setText(gameState.getPseudo());

        ((ViewHolderScoreRanking) holder).setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                openDialogFragment(gameState.getPseudo(),
                        String.valueOf(gameState.getLevel()),
                        String.valueOf(gameState.getScore()),
                        String.valueOf(gameState.getTimeSeconds()));
                Toast.makeText(parentActivity, gameState.getPseudo(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return ranking.size();
    }

    private void openDialogFragment(String pseudo, String level, String score, String time){
        Bundle b = new Bundle();
        b.putString("PSEUDO_KEY", pseudo);
        b.putString("LEVEL_KEY", level);
        b.putString("SCORE_KEY", score);
        b.putString("TIME_KEY", time);

        RankindDetailFragment rankindDetailFragment = new RankindDetailFragment();
        rankindDetailFragment.setArguments(b);
        rankindDetailFragment.show(fragmentManager,"tag");
    }

}
