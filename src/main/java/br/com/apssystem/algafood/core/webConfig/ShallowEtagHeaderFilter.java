package br.com.apssystem.algafood.core.webConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

//@Configuration
public class ShallowEtagHeaderFilter implements WebMvcConfigurer {

    @Bean
    public Filter shallowEtagHeaderFilter() {
        return new org.springframework.web.filter.ShallowEtagHeaderFilter();
    }
}
