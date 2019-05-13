package com.sujiakeji.merchant.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@RefreshScope
@Component
public class StorageConfig {

    @Value("${storage.baseUrl}")
    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }
}
