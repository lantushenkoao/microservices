package com.lantushenko.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileQueryReply {
    public static final String QUEUE_NAME = "microservice.file.reply";
    private String file;
}
