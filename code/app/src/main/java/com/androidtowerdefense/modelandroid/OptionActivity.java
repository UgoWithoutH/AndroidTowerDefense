package com.androidtowerdefense.modelandroid;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.androidtowerdefense.R;
import com.androidtowerdefense.modelandroid.view.GameView;

public class OptionActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_tuto);
    }

    public void retourMenu(View view){
        finish();
    }
}
