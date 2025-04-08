package org.example.perceprton.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {
    @Bean
    List<Object> arrayList(){
        return new ArrayList<>();
    }
}
