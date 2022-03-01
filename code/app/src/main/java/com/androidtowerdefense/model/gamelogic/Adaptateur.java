package com.androidtowerdefense.model.gamelogic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidtowerdefense.R;

import org.w3c.dom.Text;

public class Adaptateur extends RecyclerView.Adapter<Adaptateur.ViewHolder>{

    private String[] localDataSet;

    @NonNull
    @Override
    public Adaptateur.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.score_ranking_cell, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Adaptateur.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(@NonNull View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.cellText);

        }
        public TextView getTextView() {
            return textView;
        }
    }

    public Adaptateur(String[] dataSet) {
        localDataSet = dataSet;
    }

}
