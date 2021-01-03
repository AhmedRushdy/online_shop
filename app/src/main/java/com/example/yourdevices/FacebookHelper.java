package com.example.yourdevices;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.yourdevices.main.MainActivity;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
/*
 it has created for using in two activities (Sign in and log in )
*/

public class FacebookHelper {
        private LoginButton loginButton;
        private Context context;
        private CallbackManager callbackManager;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();    // ...
    //    // Initialize Firebase Auth
        public FacebookHelper(LoginButton loginButton, Context context) {
            this.loginButton = loginButton;
            this.context = context;
        }

        public void facebookLogin() {

            callbackManager = CallbackManager.Factory.create();
            loginButton.setPermissions(Arrays.asList("email", "public_profile"));
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

                @Override
                public void onSuccess(LoginResult loginResult) {
                    Toast.makeText(context, "Login successfull", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(context, MainActivity.class);
                    context.startActivity(i);
                }

                @Override
                public void onCancel() {
                    Toast.makeText(context, "cancel", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(FacebookException error) {
                    Toast.makeText(context, "error log", Toast.LENGTH_SHORT).show();
                }


            });

        }

        public void onActivityResultFB(int requestCode, int resultCode, Intent data) {
            callbackManager.onActivityResult(requestCode, resultCode, data);

        }

        AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if (currentAccessToken == null) {

                    Toast.makeText(context, "User Logged out", Toast.LENGTH_LONG).show();
                } else
                    loadUserProfile(currentAccessToken);
            }
        };

        private void loadUserProfile(AccessToken newAccessToken) {
            GraphRequest request = GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    try {

                        String first_name = object.getString("first_name");
                        String last_name = object.getString("last_name");
                        String email = object.getString("email");
                        String id = object.getString("id");
                        String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";

                        Log.d("Name", first_name + " " + last_name);
                        Log.d("email", email);
                        Log.d("image", image_url);


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(context, "exception", Toast.LENGTH_SHORT).show();
                        Log.d("Error_fb", "fb" + e.getMessage());
                    }
                }
            });

            Bundle parameters = new Bundle();
            parameters.putString("fields", "first_name,last_name,email,id");
            request.setParameters(parameters);
            request.executeAsync();
        }

        public void checkLoginStatus() {

            if (AccessToken.getCurrentAccessToken() != null) {
                loadUserProfile(AccessToken.getCurrentAccessToken());
            }
        }
   }