package com.example.admin.thingstodo.Wine;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.admin.thingstodo.BottomNavigation.LandingBottomNav;
import com.example.admin.thingstodo.Classes.CatalogClass;
import com.example.admin.thingstodo.R;

public class WineDescription extends AppCompatActivity {


    Toolbar toolbar_WineDescription;
    String ibImage0;
    TextView tvArea, tvEventTitle, tvDescription, tvDate, tvTime, tvPrice,tvCall,tvEmail;
    ImageButton imageView, ibShare, ibEmail, ibCall;
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
        ibShare = findViewById(R.id.ibShare);
        ibEmail = findViewById(R.id.ibEmail);
        ibCall = findViewById(R.id.ibCall);
        tvCall = findViewById(R.id.tvCall);
        tvEmail = findViewById(R.id.tvEmail);


        Intent intent = getIntent();
        c = (CatalogClass) intent.getSerializableExtra("select");

        tvArea.setText(c.getLocation());
        tvDescription.setText(c.getFulldesscription());
        tvDate.setText(c.getDate());
        tvTime.setText(c.getTime());
        tvPrice.setText(c.getPrice());
        tvDate.setText(c.getDate());
        tvCall.setText(c.getCellno());
        tvEmail.setText(c.getEnquiries());


        ibImage0 = c.getImageurl();
        Glide.with(
                getApplicationContext())
                .load(c.getImageurl())
                .into(imageView);


        /**
         * ONCLICKS
         */

        ibCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cellno = tvCall.getText().toString();
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+ Uri.encode(cellno.trim())));
                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(callIntent);


            }
        });

        ibEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String enquiries = tvEmail.getText().toString();
                /* Create the Intent */
                final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

/* Fill it with Data */
                emailIntent.setType("plain/text");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{Uri.encode(enquiries.trim())});
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject");
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Text");

/* Send it off to the Activity-Chooser */
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));

//                String enquiries = tvEmail.getText().toString();
//
//                Intent emailIntent;
//
//                emailIntent = new Intent(Intent.ACTION_SENDTO);
//                emailIntent.setData(Uri.parse("" + Uri.encode(enquiries.trim())));
//                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "subject");
//                emailIntent.putExtra(Intent.EXTRA_TEXT, "text");
//
//                if (emailIntent.resolveActivity(getPackageManager()) != null) {
//                    startActivity(emailIntent);
//                } else {
//                    Toast.makeText(WineDescription.this, "Email not found", Toast.LENGTH_SHORT).show();
//                }

            }
        });

        ibShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, "ThingsToDo App");
                startActivity(Intent.createChooser(sharingIntent, "Share via"));

            }
        });


        /**
         * ToolBar
         */
        toolbar_WineDescription = findViewById(R.id.toolbar_WineDescription);
        toolbar_WineDescription.setTitle(c.getEventTitle());
        setSupportActionBar(toolbar_WineDescription);
        toolbar_WineDescription.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_arrow));
        toolbar_WineDescription.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WineActivity.class);
                startActivity(intent);
            }
        });
    }
}

