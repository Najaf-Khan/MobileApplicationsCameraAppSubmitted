package com.example.hp1.json_to_list_example;

import android.graphics.Bitmap;

/**
 * Created by Sarmad on 15-Nov-17.
 */

public class PersonData {

    private String fullName;
    private String userName;
    private Bitmap image;

    public PersonData(String fullName,
                      String userName,
                      Bitmap image) {
        this.fullName = fullName;
        this.userName = userName;
        this.image = image;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUserName() {
        return userName;
    }

    public Bitmap getImage() {
        return image;
    }
}