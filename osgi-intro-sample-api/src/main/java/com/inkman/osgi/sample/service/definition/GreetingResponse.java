package com.inkman.osgi.sample.service.definition;

import java.io.Serializable;

public class GreetingResponse implements Serializable {
    String message;

    public GreetingResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
