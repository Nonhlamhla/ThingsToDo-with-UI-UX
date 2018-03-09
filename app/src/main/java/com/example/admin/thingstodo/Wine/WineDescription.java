package com.example.admin.thingstodo.Wine;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.thingstodo.Classes.CatalogClass;
import com.example.admin.thingstodo.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class WineDescription extends AppCompatActivity {


    Toolbar toolbar_WineDescription;
    String ibImage0;
    TextView tvArea, tvEventTitle, tvDescription, tvDate, tvTime, tvPrice,tvCall,tvEmail, tvNumber, tvAmount, dis_count;
    ImageButton  ibShare, ibEmail, ibCall, ibMinus, ibAdd;
    private CatalogClass c;
    private String priceCalculate;
    private String price;
    int quantity = 2;
    FrameLayout mapFragment;


    /**
     * HORIZONTAL SCROLLVIEW
     */
    private ImageView imageView;
    private DescriptionAdapter adapters;
    private FirebaseDatabase database;
    private List<String> images = new ArrayList<>();


    @SuppressLint({"WrongViewCast", "CutPasteId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_description);

        /**
         * INSTANCES
         */
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
        tvNumber = findViewById(R.id.tvNumber);
        ibMinus = findViewById(R.id.ibMinus);
        ibAdd = findViewById(R.id.ibAdd);
        tvAmount = findViewById(R.id.tvAmount);
        dis_count = findViewById(R.id.dis_count);
        mapFragment = findViewById(R.id.mapFragment);
       // mapFragment = findViewById(R.id.mapFragment);

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
        tvAmount.setText(c.getPrice());
        dis_count.setText(c.getDiscount());

        ibImage0 = c.getImageurl();
        Glide.with(
                getApplicationContext())
                .load(c.getImageurl())
                .into(imageView);

        /**
         * HORIZONTAL SCROLLVIEW
         */

//        imageView = findViewById(R.id.image_View);
//        ViewPager viewPager =  findViewById(R.id.view_pager);
//
//        database = FirebaseDatabase.getInstance();
//        adapters = new DescriptionAdapter(this, images);
//        viewPager.setAdapter(adapters);
//
//        //Queries Firebase for places endpoint
//        database.getReference("ThingToDo/WineTastings/details").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot != null) {
//                    images.clear();
//                    if (dataSnapshot.hasChildren()) {
//                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
////                             images.add(snapshot.child("url").getValue().toString());
////                            images.add(snapshot.child("url2").getValue().toString());
////                            images.add(snapshot.child("url3").getValue().toString());
//                        }
//                        if (images.size() > 0) {
//                            adapters.notifyDataSetChanged();
//                        } else {
//                            System.out.println("No new items added");
//                        }
//
//                    } else {
//                        System.out.println("No children found");
//                    }
//                } else {
//                    System.out.println("No Such reference found or value is empty");
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w("Main ACtivity", "Failed to read value.", error.toException());
//            }
//        });
//
//
//



        /**
         * ONCLICKS
         */

        mapFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), WineDescription.class);
                startActivity(intent);
            }
        });

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

                final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);


                emailIntent.setType("plain/text");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{Uri.encode(enquiries.trim())});
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject");
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Text");


                startActivity(Intent.createChooser(emailIntent, "Send mail..."));


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

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity <=1000000) {
            /**
             * PRICE CALCULATION
             */
            priceCalculate = " " + quantity;
            price= " " + quantity *1500;

            tvAmount.setText("" +c.getPrice());

            tvAmount.setText("" +price);
            quantity = quantity + 1;
            displayQuantity(quantity);

            return;
        }

    }


    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity >= 0) {
            /**
             * PRICE CALCULATION
             */
            priceCalculate = " " + quantity;
            price= " " + quantity * 1500;

            tvAmount.setText("" +c.getPrice());

            tvAmount.setText("" +price);
            c.setPrice(price);
            quantity = quantity - 1;
            displayQuantity(quantity);

            return;
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int calculatedPrice) {
        tvNumber= findViewById(R.id.tvNumber);

        tvNumber.setText(" " + calculatedPrice);
        tvAmount.setText(String.valueOf(Double.valueOf(String.valueOf(tvPrice.getText()))*Double.valueOf(String.valueOf(tvNumber.getText()))));

    }

    /**
     * Calculates the price of the order.
     */
}

