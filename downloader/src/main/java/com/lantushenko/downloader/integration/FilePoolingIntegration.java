package com.lantushenko.downloader.integration;

import com.lantushenko.downloader.gateway.FileMessageHandlerGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.DirectoryScanner;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.RecursiveDirectoryScanner;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.filters.AcceptOnceFileListFilter;
import org.springframework.integration.file.filters.CompositeFileListFilter;
import org.springframework.integration.file.filters.RegexPatternFileListFilter;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.time.Duration;
import java.util.Arrays;

//https://medium.com/@changeant/file-poller-with-spring-integration-dsl-ecb7bc996ba5
//https://github.com/iainporter/spring-file-poller
@Component
public class FilePoolingIntegration {

    @Value("${inbound.directory-path}")
    private String inboundDirectoryPath;
    @Value("${inbound.filename.regex}")
    private String filePattern;
    @Value("${inbound.file-poller-fixed-delay}")
    private long fixedDelay;
    @Value("${inbound.file-poller-max-messages-per-poll}")
    private int maxMessagesPerPoll;
    @Value("${inbound.file-poller-thread-pool-size}")
    private int poolSize;

    public static final String INBOUND_CHANNEL = "inbound-channel";

    @Resource
    private FileMessageHandlerGateway fileMessageHandlerGateway;


    @Bean(name = INBOUND_CHANNEL)
    public MessageChannel inboundFilePollingChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public FileReadingMessageSource fileReadingMessageSource(DirectoryScanner directoryScanner) {
        FileReadingMessageSource source = new FileReadingMessageSource();
        source.setDirectory(new File(inboundDirectoryPath));
        source.setScanner(directoryScanner);
        source.setAutoCreateDirectory(true);
        return source;
    }

    @Bean
    public DirectoryScanner directoryScanner() {
        DirectoryScanner scanner = new RecursiveDirectoryScanner();
        CompositeFileListFilter<File> filter = new CompositeFileListFilter<>(
                Arrays.asList(new AcceptOnceFileListFilter<>(),
                        new RegexPatternFileListFilter(filePattern))
        );
        scanner.setFilter(filter);
        return scanner;
    }

    @Bean
    public IntegrationFlow inboundFileIntegration(TaskExecutor taskExecutor,
                                                  MessageSource<File> fileReadingMessageSource) {
        return IntegrationFlows.from(fileReadingMessageSource,
                        c -> c.poller(Pollers.fixedDelay(Duration.ofSeconds(fixedDelay))
                                .taskExecutor(taskExecutor)
                                .maxMessagesPerPoll(maxMessagesPerPoll)))
                .transform(Files.toStringTransformer())
                .channel(INBOUND_CHANNEL)
                .get();
    }

    @Bean
    public IntegrationFlow sendFile() {
        return IntegrationFlows.from(INBOUND_CHANNEL)
                .transform(m -> new StringBuilder((String)m).reverse().toString())
                .log(LoggingHandler.Level.INFO)
                .handle(fileMessageHandlerGateway)
                .get();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(poolSize);
        return taskExecutor;
    }

}
