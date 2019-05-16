package com.example.biletiki2.data.dto;

public class EventListRequestDto {
    private long dateFrom;
    private long dateTo;
    private String type;

    public EventListRequestDto(long dateFrom, long dateTo, String type) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.type = type;
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

    public String getHallId() {
        return type;
    }

    public void setHallId(String hallId) {
        this.type = type;
    }
}
