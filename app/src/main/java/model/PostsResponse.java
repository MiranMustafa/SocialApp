package model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Hi-Tech on 10/19/2017.
 */

public class PostsResponse {
    ArrayList<PostsInfo> postsInfos = new ArrayList<>();

    public  PostsResponse(String s){
        try {
            JSONObject jsonObject = new JSONObject(s);
            Log.i("message",s);
            JSONArray jsonArray =jsonObject.optJSONArray("postet");
            for (int i=0;i<jsonArray.length();i++){
                postsInfos.add(new PostsInfo(jsonArray.optJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public  ArrayList<PostsInfo> getPostsInfos() {
        return postsInfos;
    }
}
