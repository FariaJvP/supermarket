package com.sm.supermarket.productandinventory.web.validation;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class ResponseToWrongDataSentInForm {

    @JsonProperty private final Instant timestamp;
    @JsonProperty private final Integer status;
    @JsonProperty private final String error;
    @JsonProperty private final String message;

    public ResponseToWrongDataSentInForm(Instant instant, Integer status, String error, String message) {
        this.timestamp = instant;
        this.status = status;
        this.error = error;
        this.message = message;
    }

}
