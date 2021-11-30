package com.example.donationapp;

public class ClothesInfo {
    String clothesType;
    String clothesQuantity;
    String clothesDesc;
    String clothesSizes;
    String url;
    public ClothesInfo() {
    }
    public String getClothesType() {

        return clothesType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setClothesType(String clothesType)
    {
        this.clothesType = clothesType;
    }

    public String getClothesQuantity()
    {
        return clothesQuantity;
    }
    public void setClothesQuantity(String clothesQuantity){
        this.clothesQuantity = clothesQuantity;
    }
    public String getClothesDesc()
    {
        return clothesDesc;
    }
    public void setClothesDesc(String clothesDesc){
        this.clothesDesc = clothesDesc;
    }
    public String getClothesSizes()
    {
        return clothesSizes;
    }
    public void setClothesSizes(String clothesSizes){
        this.clothesSizes = clothesSizes;
    }
}

