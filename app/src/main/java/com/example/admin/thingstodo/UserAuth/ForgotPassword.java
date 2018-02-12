package com.example.admin.thingstodo.UserAuth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.thingstodo.BottomNavigation.LandingBottomNav;
import com.example.admin.thingstodo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin1, btnReset;
    EditText ed_email;
    private RelativeLayout activity_forgot;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        ed_email = findViewById(R.id.ed_email);
        btnLogin1 = findViewById(R.id.btn_Login1);
        btnReset = findViewById(R.id.btn_Submit);
        activity_forgot = findViewById(R.id.forgotP_Activity);

        btnReset.setOnClickListener(this);
        btnLogin1.setOnClickListener(this);

        /**
         * Init Firebase
         */
        auth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_Login1)
        {
            startActivity(new Intent(this,LogIn.class));
            finish();
        }
        else if (view.getId() == R.id.btn_Submit)
        {
            String mail =ed_email.getText().toString();
            if(mail.isEmpty()) {
                ed_email.setError("Please Enter Email");

            }else
            {
                resetPassword(ed_email.getText().toString());
            }
        }

    }


    private void resetPassword(final String email) {
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Snackbar snackBar = Snackbar.make(activity_forgot,"We have sent password to email: "+email,Snackbar.LENGTH_SHORT);
                            snackBar.show();
                        }
                        else {
                            Snackbar snackBar = Snackbar.make(activity_forgot,"Failed to send password",Snackbar.LENGTH_SHORT);
                            snackBar.show();

                        }

                    }
                });
    }
}

