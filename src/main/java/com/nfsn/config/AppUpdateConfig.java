package com.nfsn.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.update")
public class AppUpdateConfig {
    private String latestVersion;
    private String downloadUrl;
}