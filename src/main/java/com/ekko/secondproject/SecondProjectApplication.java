package com.ekko.secondproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SecondProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondProjectApplication.class, args);
    }


    public SpringApplicationBuilder configure(SpringApplicationBuilder builder)
    {
        return builder.sources(this.getClass());
    }

}
