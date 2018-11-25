package com.example.a32936;

public class Goods {
    private String goodsname;
    private double goodsprice;
    private int goodsimageId;
    private int belongshop;
    public Goods(){
    }

    public  void setBelongshop( int belongShop ) { this.belongshop = belongShop; }

    public int getBelongshop () { return belongshop; }

    public String getGoodsName() {
        return goodsname;
    }

    public void setGoodsName(String name) {
        this.goodsname = name;
    }

    public int getGoodsImageId() {
        return goodsimageId;
    }

    public void setGoodsImageId(int imageId) {
        this.goodsimageId = imageId;
    }

    public double getGoodsPrice(){ return goodsprice; }

    public void setGoodsPrice(double goodsPrice) { this.goodsprice = goodsPrice; }
}
