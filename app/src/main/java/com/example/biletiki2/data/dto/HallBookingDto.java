package com.example.biletiki2.data.dto;

import java.util.ArrayList;
import java.util.List;

public class HallBookingDto {
    private long id;
    private List<LockedSeats> list;

    public HallBookingDto(long id, List<LockedSeats> list) {
        this.id = id;
        this.list = list;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<LockedSeats> getList() {
        return list;
    }

    public void setList(List<LockedSeats> list) {
        this.list = list;
    }
}
