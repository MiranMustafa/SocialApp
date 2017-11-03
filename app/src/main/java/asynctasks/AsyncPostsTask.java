package asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import apiservice.ApiService;
import interfaces.PostsResponseCallback;
import model.PostsResponse;

/**
 * Created by Hi-Tech on 10/19/2017.
 */

public class AsyncPostsTask extends AsyncTask<String,String,String> {
    PostsResponseCallback callback;
    public AsyncPostsTask(PostsResponseCallback callback){
        this.callback=callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.i("message",s);
        PostsResponse postsResponse= new PostsResponse(s);
        callback.onPostsResponseCallback(postsResponse.getPostsInfos());
    }

    protected String doInBackground(String... strings) {
        try{
            return ApiService.get(strings[0]);
        } catch (IOException e ){
            return null;
        }
    }
}