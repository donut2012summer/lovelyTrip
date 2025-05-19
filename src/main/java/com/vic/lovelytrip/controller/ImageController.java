package com.vic.lovelytrip.controller;


import com.vic.lovelytrip.common.enums.HttpStatusEnum;
import com.vic.lovelytrip.dto.restservice.RestServiceResponse;
import com.vic.lovelytrip.service.ImageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;

@RestController
@RequestMapping("/images")
public class ImageController {


    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/presigned-url")
    RestServiceResponse<URL> getPreSignedUrl(@RequestParam String filename){
        URL url = imageService.generatePreSignedUrl(filename);
        return toResponse(url);
    }


    /**
     * Wraps a DTO in a {@link RestServiceResponse}.
     *
     * @param responseData the responseData to be wrapped
     * @return a {@link RestServiceResponse} containing the given base DTO
     * @implNote This is a utility method for standardizing API response format.
     * */
    private <T> RestServiceResponse<T> toResponse(T responseData) {

        RestServiceResponse<T> response = new RestServiceResponse<>();
        response.setResponseCode(HttpStatusEnum.OK.getStatusCode());
        response.setResponseDescription(HttpStatusEnum.OK.getStatusMessage());

        response.setResponseBody(responseData);

        return response;

    }


}
