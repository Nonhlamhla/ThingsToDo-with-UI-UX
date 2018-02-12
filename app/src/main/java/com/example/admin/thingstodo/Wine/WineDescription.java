package com.example.admin.thingstodo.Wine;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.thingstodo.BottomNavigation.LandingBottomNav;
import com.example.admin.thingstodo.Classes.CatalogClass;
import com.example.admin.thingstodo.R;

public class WineDescription extends AppCompatActivity {


    Toolbar toolbar_WineDescription;
    String ibImage0;
    TextView tvArea, tvEventTitle, tvDescription, tvDate, tvTime, tvPrice;
    ImageButton imageView;
    private CatalogClass c;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_description);


        tvArea = findViewById(R.id.tvArea);
        tvEventTitle = findViewById(R.id.tvEventTitle);
        tvDescription = findViewById(R.id.tvDescription);
        tvDate = findViewById(R.id.tvDate);
        tvTime = findViewById(R.id.tvTime);
        tvPrice = findViewById(R.id.tvPrice);
        imageView = findViewById(R.id.imageView);


        Intent intent = getIntent();
        c = (CatalogClass) intent.getSerializableExtra("select");

        tvArea.setText(c.getLocation());
        tvEventTitle.setText(c.getEventTitle());
        tvDescription.setText(c.getFulldesscription());
        tvDate.setText(c.getDate());
        tvTime.setText(c.getTime());
        tvPrice.setText(c.getPrice());
        tvDate.setText(c.getDate());


        ibImage0 = c.getImageurl();
        Glide.with(
                getApplicationContext())
                .load(c.getImageurl())
                .into(imageView);





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

