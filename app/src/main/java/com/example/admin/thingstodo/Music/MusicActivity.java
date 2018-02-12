package com.example.admin.thingstodo.Music;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.admin.thingstodo.Classes.CatalogClass;
import com.example.admin.thingstodo.BottomNavigation.LandingBottomNav;
import com.example.admin.thingstodo.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MusicActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{


    Toolbar toolbar_Music;
    RecyclerView lvMusic;
    List<CatalogClass> catalogListMusic;
    DatabaseReference databaseMusic;
    MusicAdapter adapters0;
    LinearLayoutManager layoutManager0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);


        catalogListMusic = new ArrayList<>();

        /**
         * MUSIC LISTVIEW
         */
        databaseMusic = FirebaseDatabase.getInstance().getReference("ThingToDo/Music");

        databaseMusic.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                catalogListMusic.clear();
                for (DataSnapshot catalogclassSnapshot : dataSnapshot.getChildren()) {
                    lvMusic = findViewById(R.id.lvMusic);
                    CatalogClass catalogClass = catalogclassSnapshot.getValue(CatalogClass.class);
                    catalogListMusic.add(catalogClass);
                    layoutManager0 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                    MusicAdapter adapters0 = new MusicAdapter(MusicActivity.this, catalogListMusic);
                    lvMusic.setLayoutManager(layoutManager0);
                    lvMusic.setAdapter(adapters0);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        /**
         * ToolBar
         */
        toolbar_Music = findViewById(R.id.toolbar_Music);
        toolbar_Music.setTitle("Music");
        setSupportActionBar(toolbar_Music);
        toolbar_Music.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_arrow));
        toolbar_Music.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LandingBottomNav.class);
                startActivity(intent);
            }
        });
    }

    /**
     * SearchView
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
        return true;

    }

    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        adapters0.getFilter().filter(newText);
        return true;
    }
}

