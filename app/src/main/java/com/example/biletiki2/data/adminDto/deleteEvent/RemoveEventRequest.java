package com.example.biletiki2.data.adminDto.deleteEvent;

public class RemoveEventRequest {
    private String cause;

    public RemoveEventRequest(String cause) {
        this.cause = cause;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
