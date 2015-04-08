package com.example.amado.instacam;

import android.util.Log;

import com.facebook.model.GraphObject;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Amado on 02/04/2015.
 */
public class User implements Serializable{
    private String mFirstName;
    private String mLastName;
    private Date mBirthday;
    private String mAvatarURL;
    private static final String TAG = "User";

    private static User sCurrentUser;

    public static User getCurrentUser() {
        return sCurrentUser;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public Date getBirthday() {
        return mBirthday;
    }

    public void setBirthday(Date birthday) {
        mBirthday = birthday;
    }

    public String getAvatarURL() {
        return mAvatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        mAvatarURL = avatarURL;
    }

    public static void setCurrentUser(GraphObject graphObject) {
        if(sCurrentUser==null){
            sCurrentUser = new User(graphObject);
        }
    }

    User(GraphObject graphObject){
        mFirstName =(String) graphObject.getProperty("first_name");
        mLastName =(String) graphObject.getProperty("last_name");
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        try {
            mBirthday = sdf.parse((String) graphObject.getProperty("birthday"));
        }catch (ParseException e){

            Log.d(TAG, "failed at parsing date"+graphObject.getProperty("birthday"));
        }
        mAvatarURL = (String) graphObject.getPropertyAs("picture", GraphObject.class)
                .getPropertyAs("data", GraphObject.class)
                .getProperty("url");
    }
}
