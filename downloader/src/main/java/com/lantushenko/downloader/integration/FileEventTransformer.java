package com.lantushenko.downloader.integration;

import com.lantushenko.api.FileEventMessage;
import com.lantushenko.api.FileSource;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileEventTransformer implements GenericTransformer<File, FileEventMessage> {
    @Override
    public FileEventMessage transform(File source) {
        return FileEventMessage.builder()
                .fileSource(FileSource.LOCAL_FOLDER)
                .fileName(source.getName())
                .size(source.getTotalSpace())
                .build();
    }
}