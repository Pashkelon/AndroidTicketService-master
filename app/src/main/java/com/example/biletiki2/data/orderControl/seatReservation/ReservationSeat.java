package com.example.biletiki2.data.orderControl.seatReservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationSeat {
    private List<PlaceForAdmin> list;

    public ReservationSeat(List<PlaceForAdmin> list) {
        this.list = new ArrayList<>();
        this.list = list;
    }

    public List<PlaceForAdmin> getList() {
        return list;
    }

    public void setList(List<PlaceForAdmin> list) {
        this.list = list;
    }

}
