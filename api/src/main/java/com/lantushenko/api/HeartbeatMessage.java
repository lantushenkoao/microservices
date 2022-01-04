package com.lantushenko.api;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
@JsonSerialize
public class HeartbeatMessage {
    public static final String QUEUE_NAME = "microservice.heartbeat";
    @JsonProperty
    private String message;
}
