package com.freedomedition.zet;

public class UserHelperClass {

    String str_title;
    String str_maxprogress;
    String str_currentprogress;
    String str_description;
    String str_videolink;
    String str_locationX;
    String str_locationY;
    String imageUrl;
    String str_image_vector;
    String str_number;
    String str_personName;
    String str_ending;
    String str_category;


    public UserHelperClass() {
        //This is an empty constructor
    }

    public UserHelperClass(String str_title, String str_maxprogress, String str_currentprogress, String str_description, String str_videolink, String str_locationX, String str_locationY, String imageUrl, String str_image_vector, String str_number,
                           String str_personName, String str_ending, String str_category) {
        this.str_title = str_title;
        this.str_maxprogress = str_maxprogress;
        this.str_currentprogress = str_currentprogress;
        this.str_description = str_description;
        this.str_videolink = str_videolink;
        this.str_locationX = str_locationX;
        this.str_locationY = str_locationY;
        this.imageUrl = imageUrl;
        this.str_image_vector = str_image_vector;
        this.str_number = str_number;
        this.str_personName = str_personName;
        this.str_ending = str_ending;
        this.str_category = str_category;
    }

    public String getStr_category() {
        return str_category;
    }
    public void setStr_category(String str_category) {
        this.str_category = str_category;
    }

    public String getStr_personName() {
        return str_personName;
    }
    public void setStr_personName(String str_personName) {
        this.str_personName = str_personName;
    }

    public String getStr_ending() {
        return str_ending;
    }
    public void setStr_ending(String str_ending) {
        this.str_ending = str_ending;
    }

    public String getStr_title() {
        return str_title;
    }
    public void setStr_title(String str_title) {
        this.str_title = str_title;
    }

    public String getStr_maxprogress() {
        return str_maxprogress;
    }
    public void setStr_maxprogress(String str_maxprogress) {
        this.str_maxprogress = str_maxprogress;
    }

    public String getStr_currentprogress() {
        return str_currentprogress;
    }
    public void setStr_currentprogress(String str_currentprogress) {
        this.str_currentprogress = str_currentprogress;
    }

    public String getStr_description(){return str_description;}
    public void setStr_description(String str_description){this.str_description = str_description;}

    public String getStr_videolink() {
        return str_videolink;
    }
    public void setStr_videolink(String str_videolink) {
        this.str_videolink = str_videolink;
    }

    public String getStr_locationX() {
        return str_locationX;
    }
    public void setStr_locationX(String str_locationX){this.str_locationX = str_locationX;}

    public String getStr_locationY() {
        return str_locationY;
    }
    public void setStr_locationY(String str_locationY) {
        this.str_locationY = str_locationY;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStr_image_vector() {
        return str_image_vector;
    }
    public void setStr_image_vector(String str_image_vector) {
        this.str_image_vector = str_image_vector;
    }

    public String getStr_number() {
        return str_number;
    }
    public void setStr_number(String str_number) {
        this.str_number = str_number;
    }
}

