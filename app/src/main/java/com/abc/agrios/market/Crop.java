package com.abc.agrios.market;

public class Crop {
    private String cropName;
    private String cropPrice;
    private String cropMarket;

    public Crop(String cropName, String cropPrice, String cropMarket) {
        this.cropName = cropName;
        this.cropPrice = cropPrice;
        this.cropMarket = cropMarket;
    }

    public String getCropName() {
        return cropName;
    }

    public String getCropPrice() {
        return cropPrice;
    }

    public String getCropMarket() {
        return cropMarket;
    }
}
