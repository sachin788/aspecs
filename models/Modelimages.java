package com.example.aspecs.models;

public class Modelimages {

    String title;
    String image;
    String gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    String desc;
     Modelimages(){

     }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Modelimages(String title, String image,String desc,String gender) {
        this.title = title;
        this.image = image;
        this.desc = desc;
        this.gender= gender;
    }
}
