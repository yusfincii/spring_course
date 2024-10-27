package com.yusuf.springcoredemo.config;

import com.yusuf.springcoredemo.common.Back;
import com.yusuf.springcoredemo.common.Player;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    @Bean
    public Player backPos(){
        return new Back();
    }
}
