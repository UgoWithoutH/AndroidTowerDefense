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
import com.androidtowerdefense.model.ranking.ScoreRanking;
import com.androidtowerdefense.modelandroid.view.fragments.RankingDetailFragment;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter {

    private AppCompatActivity parentActivity;
    private List<ScoreRanking> scoreRankingList;
    private FragmentManager fragmentManager;

    public MyAdapter(AppCompatActivity parentActivity, List<ScoreRanking> ranking, FragmentManager supportFragmentManager) {
        this.parentActivity = parentActivity;
        this.scoreRankingList = ranking;
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
        ScoreRanking scoreRanking = scoreRankingList.get(position);
        ((ViewHolderScoreRanking)holder).getMyTextView().setText(scoreRanking.getPseudo());

        ((ViewHolderScoreRanking) holder).setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                openDialogFragment(scoreRanking.getPseudo(),
                        String.valueOf(scoreRanking.getLevel()),
                        String.valueOf(scoreRanking.getScore()),
                        String.valueOf(scoreRanking.getTime()));
                Toast.makeText(parentActivity, scoreRanking.getPseudo(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return scoreRankingList.size();
    }

    private void openDialogFragment(String pseudo, String level, String score, String time){
        Bundle b = new Bundle();
        b.putString("PSEUDO_KEY", pseudo);
        b.putString("LEVEL_KEY", level);
        b.putString("SCORE_KEY", score);
        b.putString("TIME_KEY", time);

        RankingDetailFragment rankingDetailFragment = new RankingDetailFragment();
        rankingDetailFragment.setArguments(b);
        rankingDetailFragment.show(fragmentManager,"tag");
    }

}
