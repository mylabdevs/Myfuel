package com.mylabs.myfuel.core;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    public ModelMapperConfig() {
        System.out.println("MODEL MAPPER");
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}