package com.androidtowerdefense.modelandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidtowerdefense.R;
import com.androidtowerdefense.model.Manager;
import com.androidtowerdefense.modelandroid.view.GameView;
import com.androidtowerdefense.modelandroid.view.adapter.MyAdapter;

public class MainActivity extends AppCompatActivity {

    private Manager manager;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("truc","Create");
        Log.i("Totot","-------------");
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
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new MyAdapter(this,manager.getScoreRanking()));
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

    private boolean inputScore(){
        try{
            EditText numberScores = findViewById(R.id.numberScores);
            EditText pseudo =  findViewById(R.id.pseudonyme);
            manager.getScoreRanking().setNumberScores(Integer.parseInt(numberScores.getText().toString()));
            manager.setPseudo(pseudo.getText().toString());
            if(pseudo.length()==0){
                return false;
            }
            return true;
        }
        catch(NumberFormatException ex){
            return false;
        }

    }

    //TODO : Ajouter le même mécanisme pour le pseudonyme de l'user
    public void newGame(View view) {
        Log.d("truc", "Nouvelle Partie");
        if (!inputScore())
        {
            Log.d("truc", "Input Data / input String pas correct");
            popUpWindow(view);
        }
        else{
            Intent intent = new Intent(this,GameActivity.class);
            intent.putExtra("manager",manager);
            startActivity(intent);
        }
    }

    public void quitterApplication(View view){
        Log.d("truc", "Finish activity");
        finish();
        System.exit(0);
    }


    //Methode qui ajoute une popup pour l'user qui se ferme si on click a côté etc..
    public void popUpWindow(View view)
    {
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

}