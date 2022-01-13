package com.lantushenko.webapp.bus;

import com.lantushenko.api.FileQueryReply;
import com.lantushenko.api.FileQueryRequest;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(defaultRequestChannel = "filesExchange")
public interface FileExchangeGateway {
    FileQueryReply sendAndReceive(FileQueryRequest request);
}
