package com.astro.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by astro on 2018/4/4.
 */
@Component
@Data
@ConfigurationProperties(prefix = "weChat")
public class WeChatAccountConfig {

    private String myAppId;
    private String myAppSecret;
}
