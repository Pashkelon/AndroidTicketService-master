package com.example.biletiki2.data.orderControl.seatReservation;
//todo change sessioId on sessionId. But back is not ready
public class ReservationRequest {
    private int place, row, sessioId;
    private String side;

    public ReservationRequest(int place, int row, int sessioId, String side) {
        this.place = place;
        this.row = row;
        this.sessioId = sessioId;
        this.side = side;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSessionId() {
        return sessioId;
    }

    public void setSessionId(int sessionId) {
        this.sessioId = sessionId;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }
}
