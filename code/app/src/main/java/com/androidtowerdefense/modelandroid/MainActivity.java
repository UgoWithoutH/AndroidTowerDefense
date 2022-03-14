package com.androidtowerdefense.modelandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.androidtowerdefense.R;
import com.androidtowerdefense.modelandroid.view.fragments.MenuFragment;
import com.androidtowerdefense.modelandroid.view.fragments.RankingFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("truc","Create");
        setContentView(R.layout.menu_tab);

        tabLayout = findViewById(R.id.menuTabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Game"));
        tabLayout.addTab(tabLayout.newTab().setText("Ranking"));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                if(pos == 0) {
                    fragmentTransaction
                            .replace(R.id.fragmentContainerMenu, MenuFragment.class,null)
                            .commit();
                }
                else if(pos == 1){
                    fragmentTransaction
                            .replace(R.id.fragmentContainerMenu, RankingFragment.class, null)
                            .commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainerMenu, MenuFragment.class, null)
                    .commit();
        }
        else{
            int pos = (int) savedInstanceState.get("pos");
            TabLayout.Tab tab = tabLayout.getTabAt(pos);
            tab.select();
        }
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
        outState.putInt("pos", tabLayout.getSelectedTabPosition());
        Log.d("truc","SaveInstanceState");
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

    private boolean inputData(){
        try{
            EditText pseudo=findViewById(R.id.pseudonyme);
            if(pseudo.length()==0){
                return false;
            }
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    private boolean inputScore(){
        try{
            EditText numberScores = findViewById(R.id.numberScores);
            EditText pseudo =  findViewById(R.id.pseudonyme);
//            rankingManager.setNumberScores(Integer.parseInt(numberScores.getText().toString()));
//            rankingManager.setPseudo(pseudo.getText().toString());
            return true;
        }
        catch(NumberFormatException ex){
            return false;
        }

    }

    public void NewGame(View view) {
        Log.d("truc", "Nouvelle Partie");
        if (!inputScore())
        {
            Log.d("truc", "InputScore pas correct");
            popUpWindow(view,1);
        }
        else if(!inputData()){
            Log.d("truc", "InputData pas correct");
            popUpWindow(view,0);
        }
        else{
            EditText pseudo =  findViewById(R.id.pseudonyme);
            Intent intent = new Intent(this,GameActivity.class);
            String test = pseudo.getText().toString();
            intent.putExtra("pseudo", test);
            startActivity(intent);
        }
    }

    public void VueOption(View view){
        Intent intent = new Intent(this, OptionActivity.class);
        startActivity(intent);
    }

    public void quitterApplication(View view){
        Log.d("truc", "Finish activity");
        finish();
        System.exit(0);
    }


    //Methode qui ajoute une popup pour l'user qui se ferme si on click a côté/dessus
    public void popUpWindow(View view, int file)
    {

        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window_data, null);
        if(file==1){
            popupView = inflater.inflate(R.layout.popup_window_score, null);
        }
        //Creer la popupWindow
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        //Affiche la popupwindow
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // ferme la popupwindow si on click dessus
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

}