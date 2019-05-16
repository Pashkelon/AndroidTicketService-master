package com.example.biletiki2.data.dto;

public class LockedSeats {
    private int row;
    private String seat;

    public LockedSeats(int row, String seat) {
        this.row = row;
        this.seat = seat;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}
