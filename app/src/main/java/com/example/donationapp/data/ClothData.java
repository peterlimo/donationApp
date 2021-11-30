package com.example.donationapp.data;

public class ClothData {
    String clothesType;
    String clothesQuantity;
    String clothesDesc;
    String clothesSizes;
    String url;
    public ClothData(String clothesType, String clothesQuantity, String clothesDesc, String clothesSizes,String url) {
        this.clothesType = clothesType;
        this.clothesQuantity = clothesQuantity;
        this.clothesDesc = clothesDesc;
        this.clothesSizes = clothesSizes;
        this.url=url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getClothesType() {

        return clothesType;
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
