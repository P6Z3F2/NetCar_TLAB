package com.example.accessingdatamysql;

public class StringResponse {

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    private String response;

    public StringResponse(String s) {
        this.response = s;
    }

    // get/set omitted...
}