package com.example.biletiki2.data.dto;

public class PriceRanges {
    private String color;
    private double price;
    private int[] rows;

    public PriceRanges(String color, double price, int[] rows) {
        this.color = color;
        this.price = price;
        this.rows = rows;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int[] getRows() {
        return rows;
    }

    public void setRows(int[] rows) {
        this.rows = rows;
    }
}
