package com.example.biletiki2.data.dto;

import java.util.ArrayList;
import java.util.List;

public class EventListDto {
    private List<Event> events;

    public EventListDto() {

    }

    public EventListDto(List<Event> events) {
        this.events = new ArrayList<>();
        this.events = events;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
