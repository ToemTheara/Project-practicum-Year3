package com.example.coffeeshop;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.coffeeshop.Fragment.FragmentAbout;
import com.example.coffeeshop.Fragment.FragmentCard;
import com.example.coffeeshop.Fragment.FragmentMap;
import com.example.coffeeshop.Fragment.FragmentPopularPlace;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        final DrawerLayout drawer = findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(Color.WHITE);
        toggle.syncState();

        getSupportActionBar().setTitle("Menu");
        loadFragment(new FragmentPopularPlace());
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                drawer.closeDrawers();
                switch (menuItem.getItemId()){
                    case R.id.nav_menu:
                        getSupportActionBar().setTitle("Popular Place");
                        fragment = new FragmentPopularPlace();
                        loadFragment(fragment);
                        break;
                    case R.id.nav_card:
                        getSupportActionBar().setTitle("Nearest Place");
                        fragment = new FragmentCard();
                        loadFragment(fragment);
                        break;
                    case R.id.nav_map:
                        getSupportActionBar().setTitle("Map");
                        fragment = new FragmentMap();
                        loadFragment(fragment);
                        break;
                    case R.id.nav_about:
                        getSupportActionBar().setTitle("About Us");
                        fragment = new FragmentAbout();
                        loadFragment(fragment);
                        break;
                }

                return true;
            }
        });

    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
