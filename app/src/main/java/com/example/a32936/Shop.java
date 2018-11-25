package com.example.a32936;

import android.widget.ImageView;

import java.security.acl.Owner;

public class Shop {
    private int imageId;
    private int shopAccount;
    private String shopName;
    private String shopAddress;
    private String Owner;

    public Shop(){
    }

    public int getShopAccount() { return shopAccount; }

    public void setShopAccount(int shopAccount) {this.shopAccount = shopAccount; }

    public String getShopAddress(){ return shopAddress; }

    public void setShopAddress(String shopAddress){ this.shopAddress = shopAddress; }

    public String getName() {
        return shopName;
    }

    public void setName(String name) {
        this.shopName = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setOwner(String owner){ this.Owner = owner; }

    public String getOwner(){ return Owner; }

}
