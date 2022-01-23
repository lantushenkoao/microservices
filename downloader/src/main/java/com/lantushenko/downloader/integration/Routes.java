package com.lantushenko.downloader.integration;

import com.lantushenko.api.FileEventDocument;
import com.lantushenkoao.common.destinations.DestinationResolver;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.builder.RouteBuilder;

import org.apache.camel.component.file.GenericFile;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.lantushenkoao.common.JacksonJsonMessageConverter.JMS_TYPE_ID_HEADER_NAME;

@Component
@Slf4j
public class Routes extends RouteBuilder{
    @Value("${inbound.directory-path}")
    private String directoryPath;
    @Value("${inbound.smb.user}")
    private String smbUser;
    @Value("${inbound.smb.password}")
    private String smbPassword;
    @Value("${inbound.smb.remote-directory-path}")
    private String smbRemotePath;
    @Value("${inbound.smb.local-directory-path}")
    private String smbLocalPath;


    @Resource
    private DestinationResolver destinationResolver;

    @Override
    public void configure() {

        from(String.format("file:/%s?noop=true&delay=10s", directoryPath))
                .routeId("inbound-local-file")
                //.removeHeaders("*")
                .transform(new Expression(){
                    @Override
                    public FileEventDocument evaluate(Exchange exchange, Class type) {
                        GenericFile f = exchange.getIn().getBody(GenericFile.class);
                        return FileEventDocument.builder()
                                .fileName(f.getFileName())
                                .size(f.getFileLength())
                                .build();
                    }
                } )
                .to("direct:fileDocuments");

        //https://svn.apache.org/repos/infra/websites/production/camel/content/jcifs.html
        from(String.format("smb://%s:%s@localhost/%s?delay=5000&localWorkDirectory=%s",
                smbUser, smbPassword, smbRemotePath, smbLocalPath))
                .routeId("inbound-smb-file")
                //.removeHeaders("*")
                .transform(new Expression(){
                    @Override
                    public FileEventDocument evaluate(Exchange exchange, Class type) {
                        GenericFile f = exchange.getIn().getBody(GenericFile.class);
                        return FileEventDocument.builder()
                                .fileName(f.getFileName())
                                .size(f.getFileLength())
                                .build();
                    }
                } )
                .to("direct:fileDocuments");
        from("direct:fileDocuments")
                .marshal(new JacksonDataFormat())
                .log("Received file event - ${body}, headers: ${headers}")
                .setHeader(JMS_TYPE_ID_HEADER_NAME, constant(FileEventDocument.class.getName()))
                .convertBodyTo(String.class)
                .to(String.format("activemq:queue:%s",
                        destinationResolver.resolveAddress(FileEventDocument.class)))
                ;
    }
}
