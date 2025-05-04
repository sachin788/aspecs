package com.example.aspecs.models;

public class Readingglassesmodel {
    String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    String brandname;
    String discount;
    String imageurl;
    String price;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    String uid;
    String rating;
    String size;
    String subimage1;
    String subimage2;

    public Readingglassesmodel(String uid, String brandname,String email, String discount, String imageurl, String price, String rating, String size, String subimage1, String subimage2, String subimage3) {
        this.brandname = brandname;
        this.email = email;
        this.uid = uid;
        this.discount = discount;
        this.imageurl = imageurl;
        this.price = price;
        this.rating = rating;
        this.size = size;
        this.subimage1 = subimage1;
        this.subimage2 = subimage2;
        this.subimage3 = subimage3;
    }

    public String getSubimage1() {
        return subimage1;
    }

    public void setSubimage1(String subimage1) {
        this.subimage1 = subimage1;
    }

    public String getSubimage2() {
        return subimage2;
    }

    public void setSubimage2(String subimage2) {
        this.subimage2 = subimage2;
    }

    public String getSubimage3() {
        return subimage3;
    }

    public void setSubimage3(String subimage3) {
        this.subimage3 = subimage3;
    }

    String subimage3;

    Readingglassesmodel(){
    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

}
