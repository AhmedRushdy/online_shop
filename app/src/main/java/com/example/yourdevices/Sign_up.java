package com.example.yourdevices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yourdevices.models.Users;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.Login;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;


public class Sign_up extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    EditText email, password, name, address, phone;
    Button sign_in;
    private String TAG = "sign in ";
    LoginButton facebook;
    Button google;
    private GoogleSignInClient mGoogleSignInClient;


    private final static int RC_SIGN_IN = 555;
    private FacebookHelper facebookHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setTitle("Back");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FacebookSdk.sdkInitialize(getApplicationContext());

        createGoogleRequest();
        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        google = findViewById(R.id.google_sign_up);
        facebook = findViewById(R.id.fb);
        address = findViewById(R.id.et_address);
        phone = findViewById(R.id.et_phone_number);
        name = findViewById(R.id.et_name);
        sign_in = findViewById(R.id.sign_up_btn);

        sign_in.setOnClickListener(this);
        facebook.setOnClickListener(this);
        google.setOnClickListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent i = new Intent(getBaseContext(), MainActivity.class);
            startActivity(i);
        }


    }

    private void createAccount(final String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        currentUser = mAuth.getCurrentUser();
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(Sign_up.this, "success ", Toast.LENGTH_SHORT).show();
                            Users users = new Users(currentUser.getUid(),name.getText().toString(), address.getText().toString(), email, phone.getText().toString().trim(),"");
                            addUser(users);
                            Log.i("1111111111111", "user added successfuly");
                            Intent i = new Intent(getBaseContext(), MainActivity.class);
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getBaseContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }


    private void addUser(Users users) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(users.getPhone());
        myRef.setValue(users);

    }

    void createGoogleRequest() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void googleSignUp() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        currentUser = mAuth.getCurrentUser();
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            String name = currentUser.getDisplayName();
                            String uid = currentUser.getUid();
                            String email = currentUser.getEmail();
                            String phone = currentUser.getPhoneNumber();
                            String avatar = currentUser.getPhotoUrl().toString();
                            Log.d(TAG, "signInWithCredential:success");
                            Intent i = new Intent(getBaseContext(), MainActivity.class);
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(Sign_up.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_up_btn:
                createAccount(email.getText().toString().trim(), password.getText().toString().trim());
                break;
            case R.id.google_sign_up: {
                googleSignUp();
                Toast.makeText(Sign_up.this, "google clicked", Toast.LENGTH_LONG).show();
            }
            break;
            case R.id.fb: {
                logInByFacebook();
                Toast.makeText(Sign_up.this, "facebook clicked", Toast.LENGTH_LONG).show();
            }
            break;

        }


    }

    private void logInByFacebook() {
        facebookHelper = new FacebookHelper(facebook, this);
        facebookHelper.facebookLogin();
        facebookHelper.checkLoginStatus();

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
                Toast.makeText(this, "google accepted", Toast.LENGTH_SHORT).show();
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                Toast.makeText(this, e.getStackTrace().toString(), Toast.LENGTH_SHORT).show();
            }
        }
//        // Facebook auth
//        else {
//            facebookHelper.onActivityResultFB(requestCode,resultCode,data);
//            super.onActivityResult(requestCode, resultCode, data);
//
//        }
    }

}