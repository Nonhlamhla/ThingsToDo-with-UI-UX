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
            edTime, tvFullDes, edCellNo,edEnquiries,edLocation ;
    ImageButton Image, Image0, Image1;
    TextView tvRating;
    Button btn_Add;
    private DatabaseReference databaseMusic;
    private StorageReference mStorageReference;
    private ProgressDialog mDialog;
    private static final int GALLERY_INTENT = 1;
    Uri uri, uri1, uri2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_admin);

        /**
         * DATABASE REFERENCE
         */
        databaseMusic = FirebaseDatabase.getInstance().getReference("ThingToDo/Music");
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
                startActivityForResult(intent, GALLERY_INTENT);
            }
        });

        Image1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //IMAGE
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT);
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

        /**
         * VALIDATIONS
         */
        if (!TextUtils.isEmpty(date)) {
            mDialog.setMessage("Loading...");
            mDialog.show();



            /**
             * ADDS ENTRIES INTO CORRECT LISTS
             */
            Log.i("T",uri.toString());
            StorageReference filePath = mStorageReference.child("MusicImages").child(uri.getLastPathSegment());
            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri imageUri = taskSnapshot.getDownloadUrl();


                    //CREATES A NEW UNIQUE ID IN DATABASE
//                    String id = databaseMusic.push().getKey();
//                    CatalogClass catalogClass = new CatalogClass(id, date, eventTitle, description, price, discount, imageUri.toString(), location, time, fulldesscription, cellno, enquiries);
//                    databaseMusic.child(id).setValue(catalogClass);
//                    mDialog.dismiss();
//                    Toast.makeText(getApplicationContext(), "Done Uploading ...", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), WineActivity.class);
                    startActivity(intent);

                }
            });
        } else {
            Toast.makeText(this, "You should enter a name", Toast.LENGTH_LONG).show();
        }

    }
}
