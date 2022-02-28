package com.androidtowerdefense.modelandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.androidtowerdefense.R;
import com.androidtowerdefense.model.Loop;
import com.androidtowerdefense.model.Manager;
import com.androidtowerdefense.model.gamelogic.GameManager;
import com.androidtowerdefense.model.gamelogic.action.IBuyer;
import com.androidtowerdefense.model.gamelogic.action.tower.BuyerTower;
import com.androidtowerdefense.modelandroid.view.GameView;
import com.androidtowerdefense.modelandroid.view.adapter.MyAdapter;
import com.androidtowerdefense.modelandroid.view.draw.DrawMap;

public class GameActivity extends AppCompatActivity{

    private Manager manager;
    private boolean constructTowers = true; //true pour test sinon false
    private Button pauseRestartButton;
    private Button speedButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle data = getIntent().getExtras();
        manager = (Manager) data.get("manager");
        Log.d("truc","Create2");
        setContentView(R.layout.game_view);
        GameView gameView = findViewById(R.id.myView);
        pauseRestartButton = findViewById(R.id.buttonStopRestart);
        speedButton = findViewById(R.id.speedButton);
        DrawMap drawMap = gameView.getDrawMap();
        GameManager gameManager = new GameManager(manager.getPseudo(), drawMap.getMap());
        manager.setGameManager(gameManager);
        gameView.setGameManager(gameManager);
        gameView.setGameActivity(this);
        gameView.setOnTouchListener((view, event) -> {
                if (constructTowers) {
                    Log.d("click","click");
                    IBuyer buyer = new BuyerTower(gameManager.getGame(), gameManager.getGameMap());
                    if(buyer.buy((int) event.getX()/drawMap.getWidthResize(), (int) event.getY()/drawMap.getHeightResize())){
                        gameView.invalidate();
                    } //déléguer tout ça dans le GameManager
                    constructTowers = true;
                }
                return true;
        });
        gameManager.start();
        //data.get(....);
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

    public void returnHome(View view) {
        manager.getScoreRanking().updateRanking(manager.getGameManager().getGame());
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void stopOrRestart(View view) {
        GameManager gameManager = manager.getGameManager();

        if (gameManager.getLoop().isRunning()) {
            pauseRestartButton.setText("Restart");
            gameManager.getLoop().setRunning(false);
        } else {
            pauseRestartButton.setText("Stop");
            gameManager.getLoop().setRunning(true);
            gameManager.start();
        }
    }

    public void speed(View view) {
        GameManager gameManager = manager.getGameManager();
        Loop boucle = gameManager.getLoop();
        if (!gameManager.getGame().isSpeed()) {
            speedButton.setText("X1");
            gameManager.getGame().setSpeed(true);
            boucle.setMillis(boucle.getMillis() / 2);
        } else {
            speedButton.setText("X2");
            gameManager.getGame().setSpeed(false);
            boucle.setMillis(boucle.getMillis() * 2);
        }
    }
}
