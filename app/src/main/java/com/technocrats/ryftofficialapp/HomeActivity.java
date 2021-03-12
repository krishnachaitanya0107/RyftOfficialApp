package com.technocrats.ryftofficialapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class HomeActivity extends AppCompatActivity {
    MeowBottomNavigation bottomNavigation;
    private final static int ID_HOME=1;
    private final static int ID_CHAT=2;
    private final static int ID_CATEGORIES=3;
    private final static int ID_PROFILE=4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Rift");


        bottomNavigation = findViewById(R.id.btNav);
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_chat));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_categories));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_baseline_person_pin_24));

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FeedFragment()).commit();
        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                // your codes
            }
        });

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                // your codes
                Fragment select_fragment=null;
                switch (item.getId()){
                    case ID_HOME:
                        select_fragment = new FeedFragment();
                        break;
                    case ID_CHAT:
                        select_fragment = new ChatFragment();
                        break;
                    case ID_CATEGORIES:
                        select_fragment = new CategoryFragment();
                        break;
                    case ID_PROFILE:
                        select_fragment = new ProfileFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, select_fragment).commit();
            }
        });
        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                // your codes
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}