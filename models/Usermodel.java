package com.example.aspecs.models;

public class Usermodel {
    String Username;
    String Email;
    String City;
    String Mobileno;
    String Profileimage;

    public String getProfileimage() {
        return Profileimage;
    }

    public void setProfileimage(String profileimage) {
        Profileimage = profileimage;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getMobileno() {
        return Mobileno;
    }

    public void setMobileno(String mobileno) {
        Mobileno = mobileno;
    }

    public Usermodel(String email, String mobileno, String profileimage, String username) {
        Email = email;
        Mobileno = mobileno;
        Profileimage = profileimage;
        Username = username;
    }
}
