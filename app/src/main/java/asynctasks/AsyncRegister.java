package asynctasks;

import android.os.AsyncTask;

import java.io.IOException;

import apiservice.ApiService;
import interfaces.RegisterResponseCallback;
import model.RegisterResponse;

/**
 * Created by Hi-Tech on 10/16/2017.
 */

public class AsyncRegister extends AsyncTask<String,String,String> {
    RegisterResponseCallback responseCallback;

    public AsyncRegister(RegisterResponseCallback responseCallback) {
        this.responseCallback=responseCallback;
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
        RegisterResponse registerResponse = new RegisterResponse(s);
        responseCallback.onRegisterResponseCallback(registerResponse.getUserInfo());
    }

}
