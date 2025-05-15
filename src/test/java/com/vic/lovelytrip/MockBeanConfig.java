package com.vic.lovelytrip;

import com.vic.lovelytrip.mapper.TripMapper;
import com.vic.lovelytrip.repository.ImageCrudRepository;
import com.vic.lovelytrip.repository.TourGroupCrudRepository;
import com.vic.lovelytrip.repository.TripCrudRepository;
import com.vic.lovelytrip.service.TripService;
import com.vic.lovelytrip.service.TripServiceImpl;
import com.vic.lovelytrip.validator.TripValidator;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class MockBeanConfig {

    @Bean
    public TripCrudRepository tripCrudRepository(){
        return Mockito.mock(TripCrudRepository.class);
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
    public ImageCrudRepository imageCrudRepository(){
        return Mockito.mock(ImageCrudRepository.class);
    }

    @Bean
    public TripService tripService() {
        return new TripServiceImpl(
                tripCrudRepository(),
                tripValidator(),
                tripMapper(),
                imageCrudRepository(),
                Mockito.mock(TourGroupCrudRepository.class)
        );
    }

}
