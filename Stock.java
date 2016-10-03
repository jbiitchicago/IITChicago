package com.example.root.lab8test;

/**
 * Created by root on 4/30/16.
 */
public class Stock {
    private int id;
    private String name;
    private double price;
    private double volume;
    private String buySellCall;
    private double percentageChange;
    private int rating;
    private String imageName;

    public Stock(){

    }

    public Stock(String name, double price,double volume, String buySellCall, double percentageChange,int rating ){
        this.name = name;
        this.price = price;
        this.volume = volume;
        this.buySellCall = buySellCall;
        this.percentageChange = percentageChange;
 //       this.sector = sector;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

   /* public String getSector() {
        return sector;
    }
    public void setSector(String sec) {
        sector = sec;
    }*/

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getVolume() {
        return volume;
    }
    public void setVolume(double volume) {
        this.volume = volume;
    }
    public String getBuySellCall() {
        return buySellCall;
    }
    public void setBuySellCall(String buySellCall) {
        this.buySellCall = buySellCall;
    }
    public double getPercentageChange() {
        return percentageChange;
    }
    public void setPercentageChange(double percentageChange) {
        this.percentageChange = percentageChange;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }


    @Override
    public String toString() {
        return "Stock Details :\n SYMBOL=" + name + "\n Offer Price=" + price
                + "\n Volume= " + volume  + "\n Experts Call : " + buySellCall
                +"\n %change=" + percentageChange +"\n Rating=" + rating+"\n";
    }
}
