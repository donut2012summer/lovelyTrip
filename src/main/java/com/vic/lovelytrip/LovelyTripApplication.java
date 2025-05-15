package com.vic.lovelytrip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LovelyTripApplication {

    static Logger logger = LoggerFactory.getLogger(LovelyTripApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(LovelyTripApplication.class, args);

        logger.info(" 🩵 Application started");
    }

}
