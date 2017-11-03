package asynctasks;

import android.os.AsyncTask;

import java.io.IOException;

import apiservice.ApiService;
import interfaces.LoginReponseCallback;
import model.LoginResponse;

/**
 * Created by Hi-Tech on 10/18/2017.
 */

public class AsyncLoginTask extends AsyncTask<String,String,String> {
    LoginReponseCallback reponseCallback;
    public AsyncLoginTask(LoginReponseCallback reponseCallback){
        this.reponseCallback=reponseCallback;
    }

    @Override
    protected String doInBackground(String... strings) {
        try{
            return ApiService.get(strings[0]);
        } catch (IOException e ){
            return null;
        }
    }
    protected void onPostExecute(String s){
        super.onPostExecute(s);
        LoginResponse loginReponse = new LoginResponse(s);
        reponseCallback.onLoginResponseCallback(loginReponse.getLoginUserInfo());
    }
}
