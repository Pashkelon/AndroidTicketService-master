package com.example.biletiki2.data.adminDto.getEvents;

import com.example.biletiki2.data.adminDto.AdminEvent;
import com.example.biletiki2.data.dto.Event;

import java.util.ArrayList;
import java.util.List;

public class SearchEventListResponseDto {
    private List<AdminEvent> events;

    public SearchEventListResponseDto(List<AdminEvent> events) {
        this.events = new ArrayList<>();
        this.events = events;
    }

    public List<AdminEvent> getEvents() {
        return events;
    }

    public void setEvents(List<AdminEvent> events) {
        this.events = events;
    }
}
