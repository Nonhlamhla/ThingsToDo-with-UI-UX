package com.example.admin.thingstodo.Music;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.thingstodo.Classes.CatalogClass;
import com.example.admin.thingstodo.R;
import com.example.admin.thingstodo.Wine.WineActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MusicAdmin extends AppCompatActivity {

    EditText edDate, edArea, edEventTitle, edDescription, edPrice, edDiscount,
            edTime, tvFullDes, edCellNo, edEnquiries, edLocation;
    ImageButton Image, Image0, Image1;
    TextView tvRating;
    Button btn_Add;
    private DatabaseReference databaseMusic;
    private StorageReference mStorageReference;


    private ProgressDialog mDialog;

    /**
     * GOES WITH GALLERY IMAGE UPLOAD
     */
    StorageReference filePath, filePath1, filePath2;
    private static final int GALLERY_INTENT = 1;
    private static final int GALLERY_INTENT1 = 2;
    private static final int GALLERY_INTENT2 = 3;
    Uri uri, uri1, uri2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_admin);

        /**
         * DATABASE REFERENCE
         */
        databaseMusic = FirebaseDatabase.getInstance().getReference("ThingToDo/Music/details");
        mStorageReference = FirebaseStorage.getInstance().getReference();

        /**
         * VIEW INSTANCES
         */
        edDate = findViewById(R.id.edDate);
        edArea = findViewById(R.id.edArea);
        edEventTitle = findViewById(R.id.edEventTitle);
        edDescription = findViewById(R.id.edDescription);
        edPrice = findViewById(R.id.edPrice);
        edDiscount = findViewById(R.id.edDiscount);
        edTime = findViewById(R.id.edTime);
        tvFullDes = findViewById(R.id.tvFullDes);
        edCellNo = findViewById(R.id.edCell_No);
        edEnquiries = findViewById(R.id.edEnquiries);
        edLocation = findViewById(R.id.edLocation);
        Image = findViewById(R.id.Image);
        Image0 = findViewById(R.id.Image0);
        Image1 = findViewById(R.id.Image1);
        tvRating = findViewById(R.id.tvRating);
        btn_Add = findViewById(R.id.btn_Add);
        mDialog = new ProgressDialog(this);

        /**
         * IMAGEVIEW ATTACHMENT
         */
        Image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //IMAGE
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT);
            }
        });

        Image0.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //IMAGE
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT1);
            }
        });

        Image1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //IMAGE
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT2);
            }
        });

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddMusic();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {
            uri = data.getData();
            Image.setImageURI(uri);
        }
        if (requestCode == GALLERY_INTENT1 && resultCode == RESULT_OK) {
            uri1 = data.getData();
            Image0.setImageURI(uri1);
        }

        if (requestCode == GALLERY_INTENT2 && resultCode == RESULT_OK) {
            uri2 = data.getData();
            Image1.setImageURI(uri2);
        }

    }


    private void AddMusic() {
        final String date = edDate.getText().toString().trim();
        final String location = edArea.getText().toString().trim();
        final String eventTitle = edEventTitle.getText().toString().trim();
        final String description = edDescription.getText().toString().trim();
        final String price = edPrice.getText().toString().trim();
        final String discount = edDiscount.getText().toString().trim();
        final String time = edTime.getText().toString().trim();
        final String fulldesscription = tvFullDes.getText().toString().trim();
        final String cellno = edCellNo.getText().toString().trim();
        final String enquiries = edEnquiries.getText().toString().trim();


        mDialog.setMessage("Loading...");
        mDialog.show();


        /**
         * ADDS ENTRIES INTO CORRECT LISTS
         */
        Log.i("T", uri.toString());

        filePath = mStorageReference.child("MusicImages").child(uri.getLastPathSegment());
        if (uri1 != null) {
            filePath1 = mStorageReference.child("MusicImages").child(uri1.getLastPathSegment());
        }
        if (uri2 != null) {
            filePath2 = mStorageReference.child("MusicImages").child(uri2.getLastPathSegment());
        }


        /**
         * PUSHES ITEMS TO DATABASE
         */
        final String id = databaseMusic.push().getKey();


        if (uri != null) {
            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    @SuppressWarnings("VisibleForTests") final Uri imageUri = taskSnapshot.getDownloadUrl();


                    if (filePath1 != null) {
                        filePath1.putFile(uri1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                @SuppressWarnings("VisibleForTests") final Uri imageUri2 = taskSnapshot.getDownloadUrl();

                                System.out.println("MUSIC IMAGE URI: " + imageUri2);
                                if (filePath1 != null) {

                                    filePath2.putFile(uri2).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            @SuppressWarnings("VisibleForTests") Uri imageUri3 = taskSnapshot.getDownloadUrl();

                                            System.out.println("MUSIC IMAGE URI: " + imageUri3);


                                            //CREATES A NEW UNIQUE ID IN DATABASE
                                            // String id = databaseWine.push().getKey();
                                            CatalogClass catalogClass = new CatalogClass(id, imageUri3.toString());

                                            catalogClass.setId(id);
                                            catalogClass.setEventTitle(eventTitle);
                                            catalogClass.setDescription(description);
                                            catalogClass.setPrice(price);
                                            catalogClass.setDiscount(discount);
                                            catalogClass.setDate(date);
                                            catalogClass.setTime(time);
                                            catalogClass.setLocation(location);
                                            catalogClass.setFulldesscription(fulldesscription);
                                            catalogClass.setCellno(cellno);
                                            catalogClass.setEnquiries(enquiries);
                                            catalogClass.setImageurl(imageUri.toString());
                                            catalogClass.setImageurl2(imageUri2.toString());
                                            catalogClass.setImageurl3(imageUri3.toString());

                                            catalogClass.setImageurl((imageUri3.toString()));


                                            databaseMusic.child(id).setValue(catalogClass);
                                            mDialog.dismiss();
                                            Toast.makeText(getApplicationContext(), "Done Uploading ...", Toast.LENGTH_SHORT).show();

                                            Intent intent = new Intent(getApplicationContext(), MusicActivity.class);
                                            startActivity(intent);

                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            });
        }
    }
}

