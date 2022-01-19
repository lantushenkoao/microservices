package com.lantushenko.api;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class FileEventMessage implements Message{
    private String fileName;
    private long size;
    private FileSource fileSource;
}
