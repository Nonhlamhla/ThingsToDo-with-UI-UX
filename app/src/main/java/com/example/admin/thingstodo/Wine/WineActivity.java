package com.example.admin.thingstodo.Wine;

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


public class WineActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {


    Toolbar toolbar_Wine;
    RecyclerView lvWine;
    List<CatalogClass> catalogListWine;
    DatabaseReference databaseWine;
    private CatalogAdapter adapters;
    LinearLayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine);


        /**
         * WINE LISTVIEW
         */
        databaseWine = FirebaseDatabase.getInstance().getReference("ThingToDo/WineTastings");
        lvWine = findViewById(R.id.lvWine);

        catalogListWine = new ArrayList<>();
        lvWine = findViewById(R.id.lvWine);
        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);


        databaseWine.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                catalogListWine.clear();
               // System.out.println("System: " + dataSnapshot.hasChildren()); //CHECKS IF THERE ARE CHILDREN IN THE DATABASE

                for (DataSnapshot catalogclassSnapshot : dataSnapshot.getChildren()) {
                    //System.out.println("System: " + dataSnapshot.getValue());

                    CatalogClass catalogClass = catalogclassSnapshot.getValue(CatalogClass.class);
                    catalogListWine.add(catalogClass);

                }
                adapters = new CatalogAdapter(WineActivity.this, catalogListWine);
                lvWine.setLayoutManager(layoutManager);
                lvWine.setAdapter(adapters); //HERE ADAPTER IS NULL

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });


//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                catalogListWine.clear();
//
//                for (DataSnapshot catalogclassSnapshot : dataSnapshot.getChildren()) {
//
//                    CatalogClass catalogClass = catalogclassSnapshot.getValue(CatalogClass.class);
//                    lvWine = (RecyclerView) findViewById(R.id.lvWine);
//                    catalogListWine.add(catalogClass);
//                    layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
//                    adapters = new CatalogAdapter(WineActivity.this, catalogListWine);
//                    lvWine.setLayoutManager(layoutManager);
//                    lvWine.setAdapter(adapters);
//                }
//            }
//
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


        /**
         * ToolBar
         */
        toolbar_Wine = findViewById(R.id.toolbar_Wine);
        toolbar_Wine.setTitle("Wine Tasting & Expos");
        setSupportActionBar(toolbar_Wine);
        toolbar_Wine.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_arrow));
        toolbar_Wine.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LandingBottomNav.class);
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

        adapters.getFilter().filter(newText);
        return true;
    }
}
