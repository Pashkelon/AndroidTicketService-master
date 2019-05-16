package com.example.biletiki2.data.adminDto.getEvents;

public class SearchEventListRequestDto {
    private long dateFrom, dateTo;
    private int hall;

    public SearchEventListRequestDto(long dateFrom, long dateTo, int hall) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.hall = hall;
    }

    public long getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(long dateFrom) {
        this.dateFrom = dateFrom;
    }

    public long getDateTo() {
        return dateTo;
    }

    public void setDateTo(long dateTo) {
        this.dateTo = dateTo;
    }

    public int getHall() {
        return hall;
    }

    public void setHall(int hall) {
        this.hall = hall;
    }
}
