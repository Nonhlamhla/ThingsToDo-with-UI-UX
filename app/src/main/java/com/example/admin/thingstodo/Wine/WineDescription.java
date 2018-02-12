package com.example.admin.thingstodo.Wine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.admin.thingstodo.BottomNavigation.LandingBottomNav;
import com.example.admin.thingstodo.R;

public class WineDescription extends AppCompatActivity {


    Toolbar toolbar_WineDescription;
    ImageButton ibImage;
    TextView tvArea, tvEventTitle, tvDescription, tvDate, tvTime, tvPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_description);

        ibImage = findViewById(R.id.ibImage);
        tvArea = findViewById(R.id.tvArea);
        tvEventTitle = findViewById(R.id.tvEventTitle);
        tvDescription = findViewById(R.id.tvDescription);
        tvDate = findViewById(R.id.tvDate);
        tvTime = findViewById(R.id.tvTime);
        tvPrice = findViewById(R.id.tvPrice);



        /**
         * ToolBar
         */
        toolbar_WineDescription = findViewById(R.id.toolbar_WineDescription);
        toolbar_WineDescription.setTitle(" ");
        setSupportActionBar(toolbar_WineDescription);
        toolbar_WineDescription.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_arrow));
        toolbar_WineDescription.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LandingBottomNav.class);
                startActivity(intent);
            }
        });
    }
    }

