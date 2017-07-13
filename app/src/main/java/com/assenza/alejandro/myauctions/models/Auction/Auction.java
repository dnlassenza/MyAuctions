package com.assenza.alejandro.myauctions.models.Auction;

/**
 * Created by alejandro on 13/07/17.
 */

public class Auction {

    private AuctionObject auctionObject;
    private String auctionName;
    private Bid bid;

    public Auction(String auctionName, String ObjName, String ObjDescription, int minValue) {
        this.auctionObject = new AuctionObject(ObjName, ObjDescription, minValue);
        this.auctionName = auctionName;
        this.bid = new Bid(minValue);
    }

    public String getAuctionName() { return auctionName; }

    public AuctionObject getBidObject() {
        return auctionObject;
    }

    public Bid getBid() {
        return bid;
    }
}
