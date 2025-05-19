package com.vic.lovelytrip.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ImageServiceImplTest {

    @Autowired
    private ImageService imageService;

    @Test
    void generatePreSignedUrl_withFilename_shouldReturnUrl(){
        String filename = "test.jpg";

        URL url = imageService.generatePreSignedUrl(filename);
        assertNotNull(url);
        System.out.println(url);
    }
}
