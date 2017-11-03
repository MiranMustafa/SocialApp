package model;

import org.json.JSONObject;

/**
 * Created by Hi-Tech on 10/19/2017.
 */

public class PostsInfo {
    String id;
    String userID;
    String username;
    String photoUrl;
    String pershkrimi;
    String createdDate;

    public PostsInfo(JSONObject jsonObject){
        setId(jsonObject.optString("id"));
        setUserID(jsonObject.optString("user_id"));
        setUsername(jsonObject.optString("username"));
        setPhotoUrl(jsonObject.optString("photo_url"));
        setPershkrimi(jsonObject.optString("pershkrimi"));
        setCreatedDate(jsonObject.optString("created_date"));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public void setPershkrimi(String pershkrimi) {
        this.pershkrimi = pershkrimi;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
