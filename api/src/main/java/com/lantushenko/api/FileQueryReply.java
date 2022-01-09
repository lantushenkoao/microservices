package com.lantushenko.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileQueryReply {
    public static final String QUEUE_NAME = "microservice.file.reply";
    private String file;
}
