package com.apiexample.payload;

import java.util.Date;

public class ErrorDetails {
    private String message;
    private Date date;

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }

    public ErrorDetails(String message, Date date) {
        this.message = message;
        this.date = date;
    }
}
