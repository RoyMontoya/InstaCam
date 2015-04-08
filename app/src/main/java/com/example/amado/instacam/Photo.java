package com.example.amado.instacam;

import android.os.Environment;

import java.io.File;
import java.io.Serializable;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Amado on 10/03/2015.
 */
public class Photo implements Serializable{
    private UUID mID;
    private static final File sDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
    private User mUser;

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    private String mCaption;

    public String getCaption() {
        return mCaption;
    }

    public void setCaption(String caption) {
        mCaption = caption;
    }

    public Photo() {
        mID = UUID.randomUUID();
    }

    public UUID getID() {
        return mID;
    }

    public File getFile(){

        return new File(sDirectory, mID.toString());

    }
}
