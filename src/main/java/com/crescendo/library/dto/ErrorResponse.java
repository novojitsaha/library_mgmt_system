package com.crescendo.library.dto;

import java.util.Date;

/**
 * Data model for error response.
 */
public class ErrorResponse {

    private int status;
    private String error;
    private String message;
    private Date timestamp;
    private String path;

    public ErrorResponse(int status, String error, String message, String path, Date timestamp) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getPath() {
        return path;
    }
}
