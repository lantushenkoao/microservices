package com.lantushenko.downloader.integration;

import com.lantushenko.api.FileEventDocument;
import com.lantushenko.api.FileSource;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileEventTransformer implements GenericTransformer<File, FileEventDocument> {
    @Override
    public FileEventDocument transform(File source) {
        return FileEventDocument.builder()
                .fileSource(FileSource.LOCAL_FOLDER)
                .fileName(source.getName())
                .size(source.getTotalSpace())
                .build();
    }
}