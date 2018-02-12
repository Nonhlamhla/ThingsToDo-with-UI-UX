package com.example.admin.thingstodo.BottomNavigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.thingstodo.R;


public class LandingBottomNav extends AppCompatActivity  { //implements SearchView.OnQueryTextListener

    private TextView mTextMessage;
    ImageView Profile;
    Toolbar toolbar_Navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                     toolbar_Navigation.setTitle("Home");
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_dashboard:
                    toolbar_Navigation.setTitle("Saved");
                    fragment = new SavedFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_notifications:
                    toolbar_Navigation.setTitle("Add");
                    fragment = new AddFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_botton_nav);

        Profile = findViewById(R.id.Profile);


        /**
         * Toolbar
         */
        toolbar_Navigation =  findViewById(R.id.toolbar_Navigation);
        toolbar_Navigation.setTitle("Home");
        setSupportActionBar(toolbar_Navigation);

        /**
         * loading the first Fragment
         */
        loadFragment(new HomeFragment());

        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LandingBottomNav.this, com.example.admin.thingstodo.UserAuth.Profile.class);
                startActivity(intent);
            }
        });


        /**
         * Bottom Navigation
         */
        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void loadFragment(Fragment fragment) {

        /**
         * Loads fragment
         */
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

    /**
     * SearchView
     */
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_items, menu);
//        MenuItem menuItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
//        searchView.setOnQueryTextListener(this);
//        return true;
//
//    }
//
//    public boolean onQueryTextSubmit(String query) {
//
//        return false;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String newText) {
//
////        adapters.getFilter().filter(newText);
//        return true;






