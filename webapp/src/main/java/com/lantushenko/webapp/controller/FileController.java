package com.lantushenko.webapp.controller;


import com.lantushenko.api.FileQueryReply;
import com.lantushenko.api.FileQueryRequest;
import com.lantushenko.webapp.bus.FileDownloader;
import com.lantushenko.webapp.bus.FileExchangeGateway;
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
    private FileExchangeGateway fileExchangeGateway;

    @PostMapping("query")
    public void post() throws Exception{
        log.info("Sending file request...");
        FileQueryReply reply = fileExchangeGateway.sendAndReceive(new FileQueryRequest("testFileName.txt"));
        log.info("Received reply: " + reply.getFile());
    }
}
