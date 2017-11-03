package com.example.hi_tech.socialapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import adapters.PostsInfoAdapter;
import asynctasks.AsyncPostsTask;
import interfaces.PostsResponseCallback;
import model.PostsInfo;

public class MainActivity extends AppCompatActivity implements PostsResponseCallback{
    ImageButton imageButton;
    ListView listView;
    PostsInfoAdapter adapter;
    TextView logOut;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppPreferences.init(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new AsyncPostsTask(this).execute(getUrl());
        logOut = (TextView) findViewById(R.id.log_out);
        adapter = new PostsInfoAdapter(getLayoutInflater());
        listView = (ListView) findViewById(R.id.list_view);
        try{
        listView.setAdapter(adapter);
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
        imageButton =(ImageButton) findViewById(R.id.newpost_button);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),NewpostActivity.class);
                startActivity(intent);
            }
        });
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                AppPreferences.saveUserId("");
                startActivity(intent);
            }
        });
    }
    public String getUrl(){
        return "http://appsix.net/paintbook/index.php?GetPostet=&UserID="+AppPreferences.getUserId();
    }
    @Override
    public void onPostsResponseCallback(ArrayList<PostsInfo> postsInfo) {
        adapter.setPostsInfos(postsInfo);
    }
    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

}

