package com.example.hi_tech.socialapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import asynctasks.AsyncRegister;
import interfaces.RegisterResponseCallback;
import model.RegisterUserInfo;

/**
 * Created by Hi-Tech on 10/18/2017
 */

public class SignupActivity extends AppCompatActivity implements RegisterResponseCallback {
    Button signupButton;
    EditText nameEdittext;
    EditText usernameEdittext;
    EditText passwordEdittext;
    RegisterUserInfo userInfo;
    LinearLayout linearLayout;
    Button switchToSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        setContentView(R.layout.signup_layout);
        signupButton = (Button) findViewById(R.id.signup_button);
        switchToSignIn = (Button) findViewById(R.id.switch_sign_in);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fieldChecker()) {
                    new AsyncRegister(SignupActivity.this).execute(getUrl());
                    try {
                        if (userInfo.getStatus().equals("success")) {
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignupActivity.this,"This user exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (NullPointerException e){
                        e.printStackTrace();
                    }
                }
            }
        });
        switchToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    public String getPassword(){
        passwordEdittext = (EditText) findViewById(R.id.password_edittext);
        return    passwordEdittext.getText().toString();

    }
    public String getUsername(){
        usernameEdittext = (EditText) findViewById(R.id.username_edittext);
        return usernameEdittext.getText().toString();
    }
    public String getUrl(){
        nameEdittext = (EditText) findViewById(R.id.name_edittext);
        String fullName = nameEdittext.getText().toString();
        String[] name = fullName.split("\\s+");
        return "http://appsix.net/paintbook/index.php?RegisterUser=&User="
                +getUsername() +"&password="+getPassword()+"&Emri="+name[0]+"&Mbiemri="+name[1];
    }

    public boolean fieldChecker(){
        linearLayout=  (LinearLayout) findViewById(R.id.linear_layout);
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
    public void onRegisterResponseCallback(RegisterUserInfo userInfo) {
        this.userInfo=userInfo;
    }
}