package com.androidtowerdefense.modelandroid;

import android.content.Context;
import android.content.SharedPreferences;

import com.androidtowerdefense.model.gamelogic.GameState;
import com.androidtowerdefense.model.ranking.ScoreRanking;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestion de l'application
 */
public class RankingManager {
    private Context context;

    public RankingManager(Context context){
        this.context = context;
    }

    public List<ScoreRanking> getRankings() {
        SharedPreferences preferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        List<ScoreRanking> scoreRankingList = new ArrayList<>();
        try {
            String result = preferences.getString("ranking",null);
            if(result != null) {
                JSONArray jsonArray = new JSONArray(result);
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String pseudo = jsonObject.getString("pseudo");
                    int level = Integer.parseInt(jsonObject.getString("level"));
                    int score = Integer.parseInt(jsonObject.getString("score"));
                    int time = Integer.parseInt(jsonObject.getString("time"));
                    ScoreRanking scoreRanking = new ScoreRanking(pseudo, level, score, time);
                    scoreRankingList.add(scoreRanking);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return scoreRankingList;
    }

    public void saveGameState(GameState gameState){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pseudo", gameState.getPseudo());
            jsonObject.put("level", gameState.getLevel());
            jsonObject.put("score", gameState.getScore());
            jsonObject.put("time", gameState.getTimeSeconds());

            saveState(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void saveState(JSONObject jsonObject){
        SharedPreferences preferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String result = preferences.getString("ranking",null);

        if(result != null){
            try {
                JSONArray jsonArrayResult = new JSONArray(result);
                jsonArrayResult.put(jsonObject);
                editor.putString("ranking", jsonArrayResult.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else {
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(jsonObject);
            editor.putString("ranking", jsonArray.toString());
        }
        editor.apply();
    }
}
