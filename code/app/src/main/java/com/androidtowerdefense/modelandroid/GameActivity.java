package com.androidtowerdefense.modelandroid;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.androidtowerdefense.R;
import com.androidtowerdefense.model.Manager;
import com.androidtowerdefense.model.gamelogic.GameManager;
import com.androidtowerdefense.model.gamelogic.action.IBuyer;
import com.androidtowerdefense.model.gamelogic.action.tower.BuyerTower;
import com.androidtowerdefense.modelandroid.view.CheckerModelChanges;
import com.androidtowerdefense.modelandroid.view.GameView;

public class GameActivity extends AppCompatActivity {

    private Manager manager;
    private boolean constructTowers = true; //true pour test sinon false

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle data = getIntent().getExtras();
        manager = (Manager) data.get("manager");
        Log.d("truc","Create2");

        setContentView(R.layout.game_view);
        GameView gameView = findViewById(R.id.myView);
        GameManager gameManager = new GameManager(manager.getPseudo(), gameView.getDrawMap().getMap());
        manager.setGameManager(gameManager);
        //initializeGame(gameView);
        gameView.setOnTouchListener((view, event) -> {
                Log.d("click","x : " + event.getX() + " y : " + event.getY());
                if (constructTowers) {
                    IBuyer buyer = new BuyerTower(gameManager.getGame(), gameManager.getGameMap());
                    float x = event.getX();
                    float y = event.getY();
                    if(buyer.buy(x, y)){
                        gameView.invalidate();
                    } //déléguer tout ça dans le GameManager
                    constructTowers = true;
                }
                return true;
        });
        //data.get(....);
    }

    private void initializeGame(GameView gameView){
        CheckerModelChanges cmc = new CheckerModelChanges(manager.getGameManager(), gameView);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    if(manager.getGameManager().getLoop().isRunning()){
                        cmc.check();
                    }
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //RestoreInstanceState ne passe que après Start lorsque l'on tourne l'écran
        Log.d("truc","Start2");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("truc","Resume2");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("truc","SaveInstanceState2");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //RestoreInstanceState ne passe que après Start lorsque l'on tourne l'écran
        Log.d("truc","RestoreInstanceState2");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("truc","Restart2");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("truc","Stop2");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("truc","Pause2");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("truc","Destroy2");
    }
}
