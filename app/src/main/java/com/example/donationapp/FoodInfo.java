package com.example.donationapp;

public class FoodInfo {
     String foodType;
     String foodQuantity;
    String foodDesc;
    public FoodInfo() {
    }
        public String getFoodType() {

            return foodType;
        }
        public void setFoodType(String foodType){
            this.foodType = foodType;
        }

    public String getFoodQuantity() {
        return foodQuantity;
    }
    public void setFoodQuantity(String foodQuantity){
        this.foodQuantity = foodQuantity;
    }
    public String getFoodDesc() {
        return foodQuantity;
    }
    public void setFoodDesc(String foodDesc){
        this.foodDesc = foodDesc;
    }
}

