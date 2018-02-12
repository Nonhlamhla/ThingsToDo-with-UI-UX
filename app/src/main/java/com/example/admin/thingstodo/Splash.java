package com.example.admin.thingstodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.admin.thingstodo.UserAuth.ForgotPassword;
import com.example.admin.thingstodo.UserAuth.LogIn;

public class Splash extends AppCompatActivity {

    Button btnStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

       btnStart = findViewById(R.id.btnStart);


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), LogIn.class);
                startActivity(intent);
            }
        });


        }
    }

