package com.androidtowerdefense.modelandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.androidtowerdefense.R;
import com.androidtowerdefense.model.Manager;
import com.androidtowerdefense.modelandroid.view.GameView;

public class MainActivity extends AppCompatActivity {

    Manager manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("truc","Create");
        //GameView gameView = findViewById(R.id.myView);
        //gameView.invalidate(); -> déclancher le onDraw
        setContentView(R.layout.game_menu);
        manager = new Manager();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //RestoreInstanceState ne passe que après Start lorsque l'on tourne l'écran
        Log.d("truc","Start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("truc","Resume");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("truc","SaveInstanceState");
        outState.putSerializable("manager",manager);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("truc","RestoreInstanceState");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("truc","Restart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("truc","Stop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("truc","Pause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("truc","Destroy");
    }

    private void inputData(){
        EditText numberScores = findViewById(R.id.numberScores);
        EditText pseudo = findViewById(R.id.numberScores);
        manager.getScoreRanking().setNumberScores(Integer.parseInt(numberScores.getText().toString()));
        manager.setPseudo(pseudo.getText().toString());
    }

    public void newGame(View view) {
        //inputData();
        Intent intent = new Intent(this,GameActivity.class);
        intent.putExtra("manager",manager);
        startActivity(intent);
    }
}