package model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Hi-Tech on 10/18/2017.
 */

public class LoginResponse {
    LoginUserInfo loginUserInfo;

    public LoginResponse(String s){
        try {
            JSONObject jsonObject = new JSONObject(s);
            String logininfo= jsonObject.getString("User");
            JSONArray jsonArray = jsonObject.getJSONArray("User");
            Log.i("login info",logininfo);
            loginUserInfo = new LoginUserInfo(jsonArray.optJSONObject(0));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public LoginUserInfo getLoginUserInfo() {
        return loginUserInfo;
    }
}
