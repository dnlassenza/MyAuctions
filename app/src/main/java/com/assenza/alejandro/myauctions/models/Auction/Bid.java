package com.assenza.alejandro.myauctions.models.Auction;

/**
 * Created by alejandro on 13/07/17.
 */

public class Bid {

    private int lastBid;
    private int currentBid;

    public Bid(int value) {
        this.lastBid = value;
        this.currentBid = value;
    }

    public boolean SetBid(int value) {
        this.lastBid = this.currentBid;
        this.currentBid = value;
    }

    public int GetCurrentBid() { return this.currentBid; }

    public int GetLastBid() { return this.lastBid; }
}