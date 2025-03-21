package com.vic.lovelytrip.repository;


import com.vic.lovelytrip.entity.TripEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TripRepositoryTest {

    @Autowired
    private TripRepository tripRepository;

    @Test
    void testConnection(){
        List<TripEntity> tripEntityList = tripRepository.findAll();
        assert tripEntityList.isEmpty();
        System.out.println("Database connected!");

    }

    @Test
    void testSaveAndFindTrip(){

        TripEntity tripEntity = new TripEntity();

        tripEntity.setDuration(2);
        tripEntity.setDescription("aad");
        tripEntity.setDestination("Ilan");
        tripEntity.setTitle("TestSave");

        tripRepository.save(tripEntity);
        tripRepository.findAll().forEach(System.out::println);

        // override the equals and hashcode and compare by id , then will got true
        assert tripRepository.findAll().contains(tripEntity);
    }

    @Test
    void testDeleteTrip(){

        TripEntity tripEntity = new TripEntity();

        tripEntity.setDuration(2);
        tripEntity.setDescription("aad");
        tripEntity.setDestination("Ilan");
        tripEntity.setTitle("TestDelete");

        tripRepository.delete(tripEntity);

        assert !tripRepository.findAll().contains(tripEntity);

    }

}
