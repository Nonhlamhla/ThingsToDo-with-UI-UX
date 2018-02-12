package com.example.admin.thingstodo.UserAuth;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.thingstodo.Classes.ProfileClass;
import com.example.admin.thingstodo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin0, btnSignUp0;
    TextView tVForgotP;
    EditText editTextUser, editTextEmail, editTextPassword;
    RelativeLayout activity_sign_up;
    private ProgressDialog mDialog;

    private FirebaseAuth auth;
    Snackbar snackbar;
    private static final int ALERT_DIALOG = 1;

    /**
     * PROFILE DECLARATIONS
     */

    private DatabaseReference databaseProfile;
    private String[] username = {"Department name", "Finance", "IT", "HR", "Administrative Information Service"};
    private String email, password;
    Boolean dismiss = false;

    public static String department, stuffno, CONTEXT, user_name;
    private Boolean sendVeri = false;
    int idTotal = 0;
    private DatabaseReference db, profiledb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        CONTEXT = "SignUpActivity";
        mDialog = new ProgressDialog(this);

        btnLogin0 = findViewById(R.id.btnLogin_signup);
        btnSignUp0 = findViewById(R.id.btnSignUp_register);
        tVForgotP = findViewById(R.id.tVForgot_pass);
        editTextUser = findViewById(R.id.editTextUser);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        /**
         * Email, Password Strings
         */
        email = editTextEmail.getText().toString();
        password = editTextPassword.getText().toString();


        db = FirebaseDatabase.getInstance().getReference("Users");

        /**
         * OnClickListeners for Buttons
         */
        btnSignUp0.setOnClickListener(this);
        btnLogin0.setOnClickListener(this);
        tVForgotP.setOnClickListener(this);

        /**
         * Initiate Firebase Authentication
         */
        auth = FirebaseAuth.getInstance();
    }

    @SuppressLint("NewApi")
    @Override
    public void onClick(View view) {

         if (view.getId() == R.id.btnLogin_signup){
            startActivity(new Intent(SignUp.this,LogIn.class));
            finish();
        }
        else if (view.getId() == R.id.tVForgot_pass) {
            startActivity(new Intent(SignUp.this,ForgotPassword.class));
            finish();
        }

        if (view.getId() == R.id.btn_verify_email) {
            sendEmailVerification();
        }


        if (view.getId() == R.id.btnSignUp_register) {

            /**
             * Email, Password, Username Code on how they should work
             */
            email = editTextEmail.getText().toString();
            password = editTextPassword.getText().toString();

            /**
             * Validation
             */

            user_name = editTextUser.getText().toString();

            if (user_name.isEmpty()) {
                editTextUser.setError("Username must not be empty");
            } else {
                if (email.isEmpty()) {

                    editTextEmail.setError("Email is empty");
                } else {

                    if (email.contains("@")) {


                        if (password.isEmpty()) {
                            editTextPassword.setError("Password is empty");
                        } else {
                            if (password.length() >= 7) {
                                int upperCaseCounter = 0, lowerCaseCounter = 0, digitCounter = 0, whiteSpaceCounter = 0, specialCounter = 0;

                                try {
                                    byte[] bytes = password.getBytes();
                                    ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                                    BufferedReader br = new BufferedReader(new InputStreamReader(bais));


                                    String pass = br.readLine();

                                    for (int i = 0; i < pass.length(); i++) {
                                        char ch = pass.charAt(i);
                                        if (Character.isAlphabetic(ch)) {
                                            if (Character.isUpperCase(ch)) {
                                                upperCaseCounter += 1;
                                            } else {
                                                lowerCaseCounter += 1;
                                            }
                                        } else if (Character.isDigit(ch)) {
                                            digitCounter += 1;
                                        } else {
                                            if (Character.isWhitespace(ch)) {
                                                whiteSpaceCounter += 1;
                                            } else {
                                                specialCounter += 1;
                                            }
                                        }
                                    }
                                    if (upperCaseCounter > 0) {
                                        if (lowerCaseCounter > 2) {
                                            if (specialCounter > 0) {
                                                if (digitCounter > 1) {


                                                    signUpUser(email, password);
                                                } else {
                                                    editTextPassword.setError("Password must contain at least 2 uppercase");
                                                }

                                            } else {
                                                editTextPassword.setError("Password must contain at least one special character");
                                            }

                                        } else {
                                            editTextPassword.setError("Password must contain at least 3 lowercase");
                                        }

                                    } else {
                                        editTextPassword.setError("Password must contain at least one uppercase");
                                    }


                                } catch (IOException e) {
                                    System.out.println("error in input.");
                                }
//
//
                            } else {
                                editTextPassword.setError("Password must contains more than 6 characters  ");
                            }
                        }
                    } else {
                        editTextEmail.setError("Email must contain @gmail.co.za");
                    }


                }


            }


        }
    }

    private void signUpUser(final String email, final String password) {
        mDialog.setMessage("Sending Verification Email ...");
        mDialog.show();
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            String id = auth.getCurrentUser().getUid();
                            LogIn.ACCOUNT_CHECK = "sign";

                            profiledb = FirebaseDatabase.getInstance().getReference("Users/" + id + "/Profile");


                            FirebaseApp.getApps(SignUp.this);

                                ProfileClass profileClass = new ProfileClass();
                                user_name = editTextUser.getText().toString();


                                profileClass.setUser_name(user_name);
                                profileClass.setLogId(auth.getCurrentUser().getUid());

                                profiledb.setValue(profileClass);

                                sendEmailVerification();

                                snackbar = Snackbar.make(activity_sign_up, " : ", Snackbar.LENGTH_SHORT);
                                snackbar.show();

                                Snackbar snackbar = Snackbar
                                        .make(activity_sign_up, " Verification Sent!!", Snackbar.LENGTH_LONG)
                                        .setAction("Done", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Snackbar snackbar1 = Snackbar.make(activity_sign_up, "Message is restored!", Snackbar.LENGTH_SHORT);
                                                snackbar1.show();
                                            }
                                        });

                                snackbar.show();

                                Intent intent = new Intent(SignUp.this,LogIn.class);
                                startActivity(intent);
                                //showDialog( ALERT_DIALOG );

                                mDialog.dismiss();
                            } else {
                                dismiss = false;
                                mDialog.dismiss();
                                String emails = task.getException().getMessage();
                                if (emails.contains("email")) {
                                    editTextEmail.setError(task.getException().getMessage());
                                } else {
                                    editTextEmail.setError(task.getException().getMessage());
                                }
                                snackbar = Snackbar.make(activity_sign_up, "Error: " + task.getException(), Snackbar.LENGTH_SHORT);
                                snackbar.show();

                            }
                        }

                });
    }




    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;
        if (id == ALERT_DIALOG) {
            @SuppressLint("RestrictedApi") ContextThemeWrapper ctw = new ContextThemeWrapper(this, R.style.MyStyle);
            AlertDialog.Builder builder = new AlertDialog.Builder(ctw);
            builder.setMessage("Verification email sent to")
                    .setTitle("Verification")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setCancelable(false)
                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

//                                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
//                                    startActivity(intent);
//                                    dialog.dismiss();
                                }
                            }
                    );
            dialog = builder.create();
        }
        if (dialog == null) {
            dialog = super.onCreateDialog(id);
        }
        return dialog;
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

    private void sendEmailVerification() {
        // Disable Verify Email button
        findViewById(R.id.btn_verify_email).setEnabled(false);

        final FirebaseUser user = auth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // Re-enable Verify Email button
                        findViewById(R.id.btn_verify_email).setEnabled(true);

                        if (task.isSuccessful()) {


                        } else {
                            Log.e("kjf", "sendEmailVerification failed!", task.getException());
                            Toast.makeText(getApplicationContext(), "Failed to send verification email.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
