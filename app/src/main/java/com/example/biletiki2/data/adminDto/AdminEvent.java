package com.example.biletiki2.data.adminDto;

public class AdminEvent {
    private String artist, description, eventName, eventStatus, eventType;
    private int eventDurationHours, eventId, hall;
    private long eventStart;
    private String[] images;
    private PriceRange[] priceRanges;

    public AdminEvent(String artist, String description, String eventName,
                             String eventStatus, String eventType, int eventDurationHours,
                             int eventId, int hall, long eventStart, String[] images,
                             PriceRange[] priceRanges) {
        this.artist = artist;
        this.description = description;
        this.eventName = eventName;
        this.eventStatus = eventStatus;
        this.eventType = eventType;
        this.eventDurationHours = eventDurationHours;
        this.eventId = eventId;
        this.hall = hall;
        this.eventStart = eventStart;
        this.images = images;
        this.priceRanges = priceRanges;
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

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
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

    public int getHall() {
        return hall;
    }

    public void setHall(int hall) {
        this.hall = hall;
    }

    public long getEventStart() {
        return eventStart;
    }

    public void setEventStart(long eventStart) {
        this.eventStart = eventStart;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public PriceRange[] getPriceRanges() {
        return priceRanges;
    }

    public void setPriceRanges(PriceRange[] priceRanges) {
        this.priceRanges = priceRanges;
    }

    //===========================================================================================
    public class PriceRange{
        private String color;
        private double price;
        private int[] rows;

        public PriceRange(String color, double price, int[] rows) {
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
}
