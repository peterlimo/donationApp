package com.example.donationapp.data;

public class Cloth {
    String type,size,quantity,desc;

    public Cloth(String type, String size, String quantity, String desc) {
        this.type = type;
        this.size = size;
        this.quantity = quantity;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
