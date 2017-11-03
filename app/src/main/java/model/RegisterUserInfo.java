package model;

import org.json.JSONObject;

/**
 * Created by Hi-Tech on 10/17/2017.
 */

public class RegisterUserInfo {
    String status;

    public RegisterUserInfo(JSONObject jsonObject) {
        setStatus(jsonObject.optString("status"));
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


