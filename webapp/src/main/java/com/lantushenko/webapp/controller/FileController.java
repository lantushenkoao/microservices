package com.lantushenko.webapp.controller;


import com.lantushenko.webapp.bus.FileDownloaderGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(path = "api/file")
@Slf4j
public class FileController {

    @Resource
    private FileDownloaderGateway fileDownloader;

    @PostMapping("query")
    public void post() throws Exception{
        log.info("Sending file request...");
        fileDownloader.requestFile();
    }
}
