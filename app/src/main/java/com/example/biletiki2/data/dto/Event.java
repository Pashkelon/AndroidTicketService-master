package com.example.biletiki2.data.dto;

import java.io.Serializable;

public class Event implements Serializable {
    private int eventDurationHours, eventId, hall, ticketsAvailable;
    private long eventStart;
    private String eventName, artist, description, eventType;
    private String[] images;
    private double maxPrice, minPrice;

    public Event(String artist, String description, int eventDurationHours, int eventId,
                 String eventName, long eventStart, String eventType, int hall, String[] images,
                                        double maxPrice, double minPrice, int ticketsAvailable) {
        this.eventId = eventId;
        this.eventStart = eventStart;
        this.hall = hall;
        this.eventDurationHours = eventDurationHours;
        this.eventType = eventType;
        this.ticketsAvailable = ticketsAvailable;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.eventName = eventName;
        this.artist = artist;
        this.description = description;
        this.images = images;
    }

    public int getEventDurationHours() {
        return eventDurationHours;
    }

    public void setEventDurationHours(int eventDurationHours) {
        this.eventDurationHours = eventDurationHours;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public long getEventStart() {
        return eventStart;
    }

    public void setEventStart(int eventStart) {
        this.eventStart = eventStart;
    }

    public int getHall() {
        return hall;
    }

    public void setHall(int hall) {
        this.hall = hall;
    }

    public int getTicketsAvailable() {
        return ticketsAvailable;
    }

    public void setTicketsAvailable(int ticketsAvailable) {
        this.ticketsAvailable = ticketsAvailable;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }
}
