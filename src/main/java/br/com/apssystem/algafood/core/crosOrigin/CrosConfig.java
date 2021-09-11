package br.com.apssystem.algafood.core.crosOrigin;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class CrosConfig implements WebMvcConfigurer {

    private String originPermitida = "*";

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(originPermitida)
                .allowedMethods("*")
                .maxAge(3600);
    }
}
