package com.magnus.futbot.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
class BidPlayer {
    private @Id @GeneratedValue String id;
    private String name;
    private int rating;
    private int minBidPrice;
    private int maxBidPrice;

    BidPlayer() {
    }

    BidPlayer(String name, int rating, int minBidPrice, int maxBidPrice) {
        this.setName(name);
        this.setRating(rating);
        this.setMinBidPrice(minBidPrice);
        this.setMaxBidPrice(maxBidPrice);
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getMinBidPrice() {
        return minBidPrice;
    }

    public void setMinBidPrice(int minBidPrice) {
        this.minBidPrice = minBidPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxBidPrice() {
        return maxBidPrice;
    }

    public void setMaxBidPrice(int maxBidPrice) {
        this.maxBidPrice = maxBidPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof BidPlayer))
            return false;
        BidPlayer employee = (BidPlayer) o;
        return Objects.equals(this.id, employee.id) && Objects.equals(this.name, employee.name)
                && Objects.equals(this.rating, employee.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.rating);
    }

    @Override
    public String toString() {
        return "Bid Player{" + "id=" + this.id + ", name='" + this.name + '\'' + ", rating='" + this.rating + '\''
                + '}';
    }
}