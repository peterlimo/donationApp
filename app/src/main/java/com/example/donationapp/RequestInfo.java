package com.example.donationapp;

public class RequestInfo {
    String firstname;
    String lastname;
    String emailaddress;
    String address;
    String phone;
    String members;
    String other;

    public RequestInfo() {
    }

    public RequestInfo(String firstname, String lastname, String emailaddress, String address, String phone, String members, String other) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailaddress = emailaddress;
        this.address = address;
        this.phone = phone;
        this.members = members;
        this.other = other;
    }

    public String getFirstname() {

        return firstname;
    }
    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getLastname()
    {
        return lastname;
    }
    public void setLastname(String lastname){
        this.lastname = lastname;
    }
    public String getEmailaddress()
    {
        return emailaddress;
    }
    public void setEmailaddress(String emailaddress){
        this.emailaddress = emailaddress;
    }
    public String getAddress()
    {
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getPhone()
    {
        return phone;
    }
    public void setPhone(String phone){
        this.phone = phone;

    }

    public String getMembers()
    {
        return members;
    }
    public void setMembers(String members){
        this.members = members;
    }
    public String getOther()
    {
        return other;
    }
    public void setOther(String other){
        this.other = other;
    }
}

