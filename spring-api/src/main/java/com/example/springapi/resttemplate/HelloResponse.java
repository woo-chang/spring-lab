package com.example.springapi.resttemplate;

public class HelloResponse {

    private String message;
    private int state;

    public HelloResponse() {

    }

    public HelloResponse(String message, int state) {
        this.message = message;
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public int getState() {
        return state;
    }
}
