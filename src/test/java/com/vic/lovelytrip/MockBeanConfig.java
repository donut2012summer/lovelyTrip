package com.vic.lovelytrip;

import com.vic.lovelytrip.mapper.TripMapper;
import com.vic.lovelytrip.repository.ImageRepository;
import com.vic.lovelytrip.repository.TourGroupCrudRepository;
import com.vic.lovelytrip.repository.TripRepository;
import com.vic.lovelytrip.service.ImageService;
import com.vic.lovelytrip.service.TripService;
import com.vic.lovelytrip.service.TripServiceImpl;
import com.vic.lovelytrip.validator.TripValidator;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class MockBeanConfig {

    @Bean
    public TripRepository tripCrudRepository(){
        return Mockito.mock(TripRepository.class);
    }

    @Bean
    public TripValidator tripValidator(){
        return Mockito.mock(TripValidator.class);
    }

    @Bean
    public TripMapper tripMapper(){
        return Mockito.mock(TripMapper.class);
    }

    @Bean
    public ImageRepository imageCrudRepository(){
        return Mockito.mock(ImageRepository.class);
    }

    @Bean
    public ImageService imageService(){
        return Mockito.mock(ImageService.class);
    }

    @Bean
    public TripService tripService() {
        return new TripServiceImpl(
                tripCrudRepository(),
                tripValidator(),
                tripMapper(),
                imageCrudRepository(),
                Mockito.mock(TourGroupCrudRepository.class)
                , imageService()
        );
    }

}
