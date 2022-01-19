package com.lantushenko.downloader.integration;

import jcifs.DialectVersion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.smb.session.SmbSessionFactory;

@Configuration
public class IntegrationConfig {
    @Value("${inbound.smb.remote-directory-path}")
    private String remoteDirectoryPath;
    @Value("${inbound.smb.host}")
    private String smbHost;
    @Value("${inbound.smb.user}")
    private String smbUser;
    @Value("${inbound.smb.password}")
    private String smbPassword;

    @Bean
    public SmbSessionFactory smbSessionFactory() {
        SmbSessionFactory smbSession = new SmbSessionFactory();
        smbSession.setHost(smbHost);
//        smbSession.setPort(445);
//        smbSession.setDomain("");
        smbSession.setUsername(smbUser);
        smbSession.setPassword(smbPassword);
        smbSession.setShareAndDir("/");
        smbSession.setSmbMinVersion(DialectVersion.SMB210);
        smbSession.setSmbMaxVersion(DialectVersion.SMB311);
        return smbSession;
    }

}
