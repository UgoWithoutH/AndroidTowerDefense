package com.androidtowerdefense.modelandroid;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.androidtowerdefense.R;

public class GameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("truc","Create2");
        setContentView(R.layout.game_test);
        Bundle data = getIntent().getExtras();
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
}
