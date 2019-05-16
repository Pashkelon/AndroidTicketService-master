package com.example.biletiki2.data.orderControl.seatReservation;

public class PlaceForAdmin {
    private int place, price, row;
    private String side, status;

    public PlaceForAdmin(int place, int price, int row, String side, String status) {
        this.place = place;
        this.price = price;
        this.row = row;
        this.side = side;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
