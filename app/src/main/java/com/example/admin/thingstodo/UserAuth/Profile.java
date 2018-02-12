package com.example.admin.thingstodo.UserAuth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.v7.widget.Toolbar;

import com.example.admin.thingstodo.BottomNavigation.LandingBottomNav;
import com.example.admin.thingstodo.R;


public class Profile extends AppCompatActivity {

    Toolbar toolbar_Profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar_Profile = findViewById(R.id.toolbar_Profile);
        toolbar_Profile.setTitle("Payment");
        setSupportActionBar(toolbar_Profile);
        toolbar_Profile.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_arrow));
        toolbar_Profile.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),LandingBottomNav.class);
                startActivity(i);
            }
        });
    }
}
