package com.restart.homescreen.Model;

/**
 * Created by Ahmed El Genedy on 02-Aug-19.
 * Copyright (C) 2019 SuperGenedy. supergenedy@gmail.com All Rights Reserved.
 */
public class DataItem {

    private String itemName;
    private String itemCatName;
    private String imageURL;

    public DataItem(String itemName, String itemCatName, String imageURL) {
        this.itemName = itemName;
        this.itemCatName = itemCatName;
        this.imageURL = imageURL;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemCatName() {
        return itemCatName;
    }

    public String getImageURL() {
        return imageURL;
    }
}
