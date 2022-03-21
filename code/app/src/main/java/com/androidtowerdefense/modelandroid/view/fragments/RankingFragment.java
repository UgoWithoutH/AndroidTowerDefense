package com.androidtowerdefense.modelandroid.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidtowerdefense.R;
import com.androidtowerdefense.model.ranking.RankingManager;
import com.androidtowerdefense.model.ranking.ScoreRanking;
import com.androidtowerdefense.modelandroid.view.recycler_view.MyAdapter;

import java.util.List;

public class RankingFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ranking_view,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RankingManager rankingManager = new RankingManager(requireContext());
        List<ScoreRanking> scoreRankingList =  rankingManager.getRankings();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new MyAdapter((AppCompatActivity) getContext(), scoreRankingList,getActivity().getSupportFragmentManager()));

    }
}