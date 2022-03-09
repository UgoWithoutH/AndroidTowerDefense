package com.androidtowerdefense.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.androidtowerdefense.model.gamelogic.GameState;

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
    private int numberScores;
    private String pseudo;


    public RankingManager(Context context){
        this.context = context;
        numberScores = 10;
    }

    public String getPseudo() {return pseudo;}
    public void setPseudo(String pseudo) {this.pseudo = pseudo;}

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

    public void setNumberScores(int numberScores) {this.numberScores = numberScores;}

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

    /**
     * Met a jour le Classement une fois que la partie est terminée
     * Prépare aussi la persistence
     * @param gameState GameState
     */
    public void updateRanking(GameState gameState) {
        //List<GameState> ranking = getRankings();

//        if(numberScores == 0){
//            ranking.clear();
//            return;
//        }
//
//        if (!ranking.isEmpty()) {
//            if (ranking.size() >= numberScores) {
//                Collections.sort(ranking);
//                GameState lowerState = ranking.get(ranking.size() - 1);
//                if (lowerState != gameState) {
//                    ranking.remove(lowerState);
//                }
//            }
//        }
//        ranking.add(gameState);
//        if(ranking.size() > 1){
//            Collections.sort(ranking);
//        }
    }
}
