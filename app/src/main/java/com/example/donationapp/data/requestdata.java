package com.example.donationapp.data;

public class requestdata {
    String fname,lname,emails,address,phone,mem,other;

    public requestdata(String fname, String lname, String emails, String address,String phone,String mem,String other) {
        this.fname = fname;
        this.lname = lname;
        this.emails = emails;
        this.address = address;
        this.phone=phone;
        this.mem = mem;
        this.other = other;

    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getMem() {
        return mem;
    }

    public void setMem(String mem) {
        this.mem = mem;
    }
    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}


