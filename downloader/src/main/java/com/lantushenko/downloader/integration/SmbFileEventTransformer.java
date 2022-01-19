package com.lantushenko.downloader.integration;

import com.lantushenko.api.FileEventMessage;
import com.lantushenko.api.FileSource;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class SmbFileEventTransformer  implements GenericTransformer<File, FileEventMessage> {
    @Override
    public FileEventMessage transform(File source) {
        return FileEventMessage.builder()
                .fileName(source.getName())
                .fileSource(FileSource.SMB)
                .size(source.getTotalSpace())
                .build();
    }
}
