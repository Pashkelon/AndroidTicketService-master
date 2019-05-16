package com.example.biletiki2.data.dto;

import java.util.ArrayList;
import java.util.List;

public class ListLockedSeats {
    private List<LockedSeats> lockedSeats;
    private List<PriceRanges> priceRanges;

    public ListLockedSeats(List<LockedSeats> list, List<PriceRanges> priceList) {
        this.lockedSeats = list;
        this.priceRanges = priceList;
    }

    public List<PriceRanges> getPriceList() {
        return priceRanges;
    }

    public ListLockedSeats(List<LockedSeats> list) {
        this.lockedSeats = list;
    }

    public void add(LockedSeats seats) {
        lockedSeats.add(seats);
    }

    public List<LockedSeats> getList() {
        return lockedSeats;
    }
}
