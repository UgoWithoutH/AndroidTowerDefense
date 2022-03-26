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
    private MenuFragment menuFragment;
    private RankingFragment rankingFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("truc","Create");
        setContentView(R.layout.menu_tab);

        menuFragment = new MenuFragment();
        rankingFragment = new RankingFragment();

        tabLayout = findViewById(R.id.menuTabLayout);
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.onglet1)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.onglet2)));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                if(menuFragment == null){
                    menuFragment = new MenuFragment();
                }

                if(rankingFragment == null){
                    rankingFragment = new RankingFragment();
                }

                if(pos == 0) {
                    fragmentTransaction
                            .replace(R.id.fragmentContainerMenu, menuFragment,null)
                            .commit();
                }
                else if(pos == 1){
                    fragmentTransaction
                            .replace(R.id.fragmentContainerMenu, rankingFragment, null)
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
                    .add(R.id.fragmentContainerMenu, menuFragment, null)
                    .commit();
        }
        else{
            int pos = (int) savedInstanceState.get("pos");
            TabLayout.Tab tab = tabLayout.getTabAt(pos);
            tab.select();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("pos", tabLayout.getSelectedTabPosition());
    }

    private boolean inputData(){
        try{
            EditText pseudo=findViewById(R.id.pseudonyme);
            if(pseudo.getText().toString().trim().length()==0){
                return false;
            }
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public void newGame(View view) {
        if(!inputData()){
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
        finish();
        System.exit(0);
    }


    //Methode qui ajoute une popup pour l'user qui se ferme si on click a côté/dessus
    public void popUpWindow(View view, int file)
    {
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window_data, null);
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