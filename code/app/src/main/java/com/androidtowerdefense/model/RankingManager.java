package com.androidtowerdefense.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.androidtowerdefense.model.gamelogic.GameState;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Gestion de l'application
 */
public class RankingManager implements Serializable {

    private final List<GameState> ranking;
    private int numberScores;
    private String pseudo;
    private Context context;

    public RankingManager(Context context){
        this.context = context;
        ranking = new ArrayList<>();
        numberScores = 10;
    }

    public String getPseudo() {return pseudo;}
    public void setPseudo(String pseudo) {this.pseudo = pseudo;}

    public List<GameState> getRanking() {
        return ranking;
    }

    public void setNumberScores(int numberScores) {this.numberScores = numberScores;}

    private void stubTest(){
        ranking.add(new GameState("TOTO"));
        ranking.add(new GameState("TITI"));
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

    public void loadRanking(){
        SharedPreferences preferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        try {
            String result = preferences.getString("ranking",null);
            if(result != null) {
                JSONArray jsonArray = new JSONArray(result);
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    GameState gameState = new GameState(jsonObject.getString("pseudo"));
                    gameState.setLevel(Integer.parseInt(jsonObject.getString("level")));
                    gameState.setScore(Integer.parseInt(jsonObject.getString("score")));
                    gameState.setTimeSeconds(Integer.parseInt(jsonObject.getString("time")));

                    updateRanking(gameState);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Met a jour le Classement une fois que la partie est terminée
     * Prépare aussi la persistence
     * @param gameState GameState
     */
    public void updateRanking(GameState gameState) {

        if(numberScores == 0){
            ranking.clear();
            return;
        }

        if (!ranking.isEmpty()) {
            if (ranking.size() >= numberScores) {
                Collections.sort(ranking);
                GameState lowerState = ranking.get(ranking.size() - 1);
                if (lowerState != gameState) {
                    ranking.remove(lowerState);
                }
            }
        }
        ranking.add(gameState);
        if(ranking.size() > 1){
            Collections.sort(ranking);
        }
    }
}
