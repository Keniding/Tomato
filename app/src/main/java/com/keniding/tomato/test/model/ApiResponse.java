package com.keniding.tomato.test.model;

public class ApiResponse {
    String data;
    String message;

    public ApiResponse(String data, String message) {
        this.data = data;
        this.message = message;
    }

    public ApiResponse() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
