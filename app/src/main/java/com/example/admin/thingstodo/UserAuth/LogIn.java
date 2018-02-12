package com.example.admin.thingstodo.UserAuth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


public class LogIn extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin, btnSignUp;
    TextView tvFogotPassword;
    EditText etEmail, etPassword;
    RelativeLayout activitymain;


    //    private static final int RC_SIGN_IN = 1;
    //    public static String userId = "";
    //    public static String currentUserId = "";
    private ProgressDialog mDialog;
    public static String ACCOUNT_CHECK="login";
    private FirebaseAuth auth;
    private static final String TAG = "MAIN_ACTIVITY";
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mDialog = new ProgressDialog(this);

        /**
         * Signs user out PLACE IN LOGOUT ACTIVITY
         */
        //FirebaseAuth.getInstance().signOut();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {


                if (firebaseAuth.getCurrentUser() != null) {
                    if(ACCOUNT_CHECK!="sign") {
                        startActivity(new Intent(LogIn.this, LandingBottomNav.class));
                    }
                }
            }
        };
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            //profile activity here
            if(ACCOUNT_CHECK!="sign") {
                finish();
                startActivity(new Intent(getApplicationContext(), LandingBottomNav.class));
            }
        }

        /**
         * Instantiations
         */

        btnLogin = findViewById(R.id.btn_Login);
        btnSignUp = findViewById(R.id.btnSignUp);
        etEmail = findViewById(R.id.editText2);
        etPassword = findViewById(R.id.editText3);
        tvFogotPassword = findViewById(R.id.tvFogot_Password);
        activitymain = findViewById(R.id.activity_login);



        btnSignUp.setOnClickListener(this);
        tvFogotPassword.setOnClickListener(this);
        btnLogin.setOnClickListener(this);


        /**
         * Check if user is in the database
         */
        if (auth.getCurrentUser() != null ) {
            //            if(ACCOUNT_CHECK!="sign") {
            startActivity(new Intent(LogIn.this, LandingBottomNav.class));
            //            }

        }

    }

    /**
     *
     * SETS FORGET PASSWORD VIEWS
     */

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tvFogot_Password) {
            startActivity(new Intent(LogIn.this, ForgotPassword.class));
            finish();
        }
        if (view.getId() == R.id.btnSignUp) {
            startActivity(new Intent(LogIn.this, SignUp.class));
            finish();
        }
        if (view.getId() == R.id.btn_Login) {
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();


            if (email.isEmpty()) {
                etEmail.setError("Email is empty");


            } else {

                if (password.isEmpty()) {
                    etPassword.setError("Password is empty");
                } else {
                    loginUser(email, password);


                }
            }
        }
    }


    private void loginUser(String email, final String password) {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            boolean emailVerified = user.isEmailVerified();
            if (emailVerified) {
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                mDialog.setMessage("Signing in ...");
                                mDialog.show();

                                if (task.isSuccessful()) {
                                    if (ACCOUNT_CHECK != "sign") {
                                        startActivity(new Intent(LogIn.this, LandingBottomNav.class));
                                        mDialog.dismiss();
                                    }
                                } else {
                                    mDialog.dismiss();
                                    String emails = task.getException().getMessage();
                                    if (emails.contains("email")) {
                                        etEmail.setError(task.getException().getMessage());
                                    } else {
                                        etPassword.setError(task.getException().getMessage());
                                    }

                                    //
                                }

                            }
                        });
            } else {
                mDialog.dismiss();

                FirebaseAuth.getInstance().signOut();
                Toast.makeText(this, "Email is not verified", Toast.LENGTH_SHORT).show();

                //restart this activity

            }
        }else {

            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {


                            if (task.isSuccessful()) {
                                if(ACCOUNT_CHECK!="sign") {
                                    startActivity(new Intent(LogIn.this, LandingBottomNav.class));
                                    mDialog.dismiss();
                                }
                            } else {
                                mDialog.dismiss();
                                String emails = task.getException().getMessage();
                                if (emails.contains("email")) {
                                    etEmail.setError(task.getException().getMessage());
                                } else {
                                    etPassword.setError(task.getException().getMessage());
                                }

                            }

                        }
                    });
        }


    }

    @Override
    protected void onStart() {
        super.onStart();

        auth.addAuthStateListener(mAuthListener);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //replaces the default 'Back' button action
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory(Intent.CATEGORY_HOME);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
            finish();

        }
        return true;
    }

}