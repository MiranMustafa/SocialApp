package model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Hi-Tech on 10/16/2017.
 */

public class RegisterResponse {
    RegisterUserInfo userInfo;

    public RegisterResponse(String s){
        try {
            JSONObject jsonObject = new JSONObject(s);
            userInfo  = new RegisterUserInfo(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public RegisterUserInfo getUserInfo(){
        return userInfo;
    }

}
