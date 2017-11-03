package com.example.hi_tech.socialapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import asynctasks.AsyncLoginTask;
import interfaces.LoginReponseCallback;
import model.LoginUserInfo;


public class LoginActivity extends Activity implements LoginReponseCallback{
    EditText loginUsernameEdittext;
    EditText loginPasswordEdittext;
    Button   btnSignin;
    LinearLayout linearLayout;
    LoginUserInfo loginUserInfo;
    Button switchToSignUp;
    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AppPreferences.init(getApplicationContext());
        try {
            if (!AppPreferences.getUserId().equals("")) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
            super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        btnSignin =  findViewById(R.id.signin_button);
        switchToSignUp = findViewById(R.id.switch_sign_up);
//        String username=getIntent().getStringExtra("username");
//        String password=getIntent().getStringExtra("password");
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String status = "success";
                if(fieldChecker()) {
                    new AsyncLoginTask(LoginActivity.this).execute(getUrl());
                    try {
                        Log.i("Status", loginUserInfo.getStatus());
                        if (loginUserInfo.getStatus().equals(status)) {
                            AppPreferences.saveUserId(loginUserInfo.getUserID());
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Keni shenuar te dhenat gabim",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (NullPointerException e){
                        e.printStackTrace();
                    }
                }
            }
        });
        switchToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SignupActivity.class);
                startActivity(intent);
            }
        });

    }

    public String getUrl(){
        loginUsernameEdittext = findViewById(R.id.login_username_edittext);
        loginPasswordEdittext = findViewById(R.id.login_password_edittext);
        String email = loginUsernameEdittext.getText().toString();
        String md5Password = md5(loginPasswordEdittext.getText().toString());
        return "http://appsix.net/paintbook/index.php?User="+email+"&Password="+md5Password;
    }
    public static String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    public boolean fieldChecker(){
        linearLayout=  findViewById(R.id.login_linear);
        for (int i = 1; i < linearLayout.getChildCount(); i++) {
            EditText editText = (EditText) linearLayout.getChildAt(i);
            if(editText.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(),
                        "Fill the field:"+editText.getHint().toString(),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }


    @Override
    public void onLoginResponseCallback(LoginUserInfo loginUserInfo) {
        this.loginUserInfo=loginUserInfo;
    }
}