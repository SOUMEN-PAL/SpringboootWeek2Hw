package com.example.week2hw.configs;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    @Qualifier
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

}
