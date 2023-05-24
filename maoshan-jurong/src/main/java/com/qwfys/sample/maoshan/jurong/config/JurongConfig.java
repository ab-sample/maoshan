package com.qwfys.sample.maoshan.jurong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author liuwenke
 * @since 0.0.1
 */
@Configuration
public class JurongConfig {

    private static final int CONNECT_TIMEOUT = 8000;

    private static final int SOCKET_TIMEOUT = 8000;

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        //factory.setConnectTimeout(CONNECT_TIMEOUT);
        //factory.setReadTimeout(SOCKET_TIMEOUT);
        return new RestTemplate(factory);
        //return new RestTemplate();
    }
}
