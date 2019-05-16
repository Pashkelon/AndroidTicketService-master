package com.example.biletiki2.data.adminDto.getEvents;

public class SearchEventListDto {
    private String artist, description, eventName, eventType;
    private int eventDurationHours, eventId, hall;
    private long eventStart;
    private String[] images;
    private Progress progress;

    public SearchEventListDto(String artist, String description, String eventName,
                              String eventType, int eventDurationHours, int eventId,
                              int hall, long eventStart, String[] images, Progress progress) {
        this.artist = artist;
        this.description = description;
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventDurationHours = eventDurationHours;
        this.eventId = eventId;
        this.hall = hall;
        this.eventStart = eventStart;
        this.images = images;
        this.progress = progress;
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

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

//=========================================================================================
    class Progress{
        private int soldCount;
        private double soldPercents;

        public Progress(int soldCount, double soldPercents) {
            this.soldCount = soldCount;
            this.soldPercents = soldPercents;
        }

    public int getSoldCount() {
        return soldCount;
    }

    public void setSoldCount(int soldCount) {
        this.soldCount = soldCount;
    }

    public double getSoldPercents() {
        return soldPercents;
    }

    public void setSoldPercents(double soldPercents) {
        this.soldPercents = soldPercents;
    }
}

}
