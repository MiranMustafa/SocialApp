package model;

import org.json.JSONObject;

/**
 * Created by Hi-Tech on 10/18/2017.
 */

public class LoginUserInfo {
    String status;
    String userID;
    String fullname;
    String username;
    public LoginUserInfo(JSONObject jsonObject) {
        setStatus(jsonObject.optString("status"));
        setUserID(jsonObject.optString("UserID"));
        setFullname(jsonObject.optString("Emri"));
        setUsername(jsonObject.optString("Username"));
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
