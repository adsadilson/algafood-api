package br.com.apssystem.algafood.core.webClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Configuration
public class WebClient {


    @Bean
    public org.springframework.web.reactive.function.client.WebClient webClientCEP(org.springframework.web.reactive.function.client.WebClient.Builder builder) {
        return builder
                .baseUrl("https://viacep.com.br/ws/")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
