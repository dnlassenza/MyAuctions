package com.assenza.alejandro.myauctions.models.Auction;

/**
 * Created by alejandro on 13/07/17.
 */

public class AuctionObject {

    private String name;
    private String description;
    private int minValue;

    public AuctionObject(String name, String description, int minValue) {
        this.name = name;
        this.description = description;
        this.minValue = minValue;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getMinValue() {
        return minValue;
    }
}
