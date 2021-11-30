package com.example.donationapp;

public class User {
    String FirstName,LastName,Email,Password,Uid;
    int usertype;
    public String getUid() {
        return Uid;
    }
    public void setUid(String uid) {
        Uid = uid;
    }
    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }
    public User() {
    }

    public User(String uid,String firstName, String lastName, String email, String password,int usertype) {
        Uid = uid;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        Password = password;
        usertype=usertype;

    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
