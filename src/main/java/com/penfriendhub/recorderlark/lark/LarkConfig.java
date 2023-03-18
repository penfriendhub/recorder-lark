package com.penfriendhub.recorderlark.lark;

import com.lark.oapi.Client;
import com.lark.oapi.sdk.servlet.ext.ServletAdapter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author unickcheng
 * @since 2023-03-18
 */

@Component
public class LarkConfig {
    @Bean
    public ServletAdapter getServletAdapter () {
        return new ServletAdapter();
    }

    @Bean
    @ConfigurationProperties(prefix = "lark")
    public LarkInfo larkInfo () {
        return new LarkInfo();
    }

    @Bean
    public Client client(LarkInfo larkInfo) {
        return Client.newBuilder(larkInfo.getAppId(), larkInfo.getAppSecret()).build();
    }
}
