package dev.sanderk.home_media_server.dto;

import lombok.Data;

@Data
public class ErrorResponse {
    private int status;
    private String title;
    private String message;

    public ErrorResponse(int status, String title, String message) {
        this.status = status;
        this.title = title;
        this.message = message;
    }
}
