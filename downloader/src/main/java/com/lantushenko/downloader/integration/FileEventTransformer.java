package com.lantushenko.downloader.integration;

import com.lantushenko.api.FileEventMessage;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileEventTransformer implements GenericTransformer<File, FileEventMessage> {
    @Override
    public FileEventMessage transform(File source) {
        return FileEventMessage.builder()
                .fileName(source.getName())
                .size(source.getTotalSpace())
                .build();
    }
}