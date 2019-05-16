package com.example.biletiki2.data.orderControl.getHistory;

public class ShoppingHistory {
    private String artist, eventName;
    private long dateTime;
    private int eventId, orderNumber, totalPrice;
    private PlaceForClient[] places;

    public ShoppingHistory(String artist, String eventName, long dateTime, int eventId,
                                    int orderNumber, int totalPrice, PlaceForClient[] places) {
        this.artist = artist;
        this.eventName = eventName;
        this.dateTime = dateTime;
        this.eventId = eventId;
        this.orderNumber = orderNumber;
        this.totalPrice = totalPrice;
        this.places = places;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public PlaceForClient[] getPlaces() {
        return places;
    }

    public void setPlaces(PlaceForClient[] places) {
        this.places = places;
    }

    //=============================================================================================
    class PlaceForClient{
        private int place,price,row;
        private String side;

    public PlaceForClient(int place, int price, int row, String side) {
        this.place = place;
        this.price = price;
        this.row = row;
        this.side = side;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }
}
}
