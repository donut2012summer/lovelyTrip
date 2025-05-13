package com.vic.lovelytrip.service;

import com.vic.lovelytrip.dto.*;
import com.vic.lovelytrip.entity.ImageEntity;
import com.vic.lovelytrip.entity.TourGroupEntity;
import com.vic.lovelytrip.entity.TripEntity;
import com.vic.lovelytrip.lib.*;
import com.vic.lovelytrip.mapper.ImageMapper;
import com.vic.lovelytrip.mapper.TourGroupMapper;
import com.vic.lovelytrip.mapper.TripMapper;
import com.vic.lovelytrip.repository.ImageCrudRepository;
import com.vic.lovelytrip.repository.TourGroupCrudRepository;
import com.vic.lovelytrip.repository.TripCrudRepository;
import com.vic.lovelytrip.validator.TripValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TripServiceImpl implements TripService {

    private final ImageCrudRepository imageCrudRepository;

    private final TourGroupCrudRepository tourGroupCrudRepository;

    private final TripCrudRepository tripRepository;

    private final TripValidator tripValidator;

    private final TripMapper tripMapper;

    private final ImageMapper imageMapper = new ImageMapper();

    private final TourGroupMapper tourGroupMapper = new TourGroupMapper();

    public TripServiceImpl (TripCrudRepository tripRepository, TripValidator tripValidator, TripMapper tripMapper, ImageCrudRepository imageCrudRepository, TourGroupCrudRepository tourGroupCrudRepository) {
        this.tripRepository = tripRepository;
        this.tripValidator = tripValidator;
        this.tripMapper = tripMapper;
        this.imageCrudRepository = imageCrudRepository;
        this.tourGroupCrudRepository = tourGroupCrudRepository;
    }

    @Override
    public TripCreateResponse createTrip(TripCreateRequest tripCreateRequest) {

        tripValidator.validateCreateTripRequest(tripCreateRequest)
                     .throwIfContainsErrors(HttpStatusEnum.BAD_REQUEST);

        return tripMapper.mapToCreateResponse(saveTripDetail(tripCreateRequest));
    }

    @Override
    public TripDetailResponse getTripDetailById(Long id, boolean includeTourGroups) {

        MessageInfoContainer messageInfoContainer = new MessageInfoContainer();

        tripValidator.checkTripIdFormat(id, messageInfoContainer);

        messageInfoContainer.throwIfContainsErrors(HttpStatusEnum.BAD_REQUEST);

        TripEntity tripEntity = findExistedTrip(id, messageInfoContainer);

        messageInfoContainer.throwIfContainsErrors(HttpStatusEnum.BAD_REQUEST);

        Long tripId = tripEntity.getId();

        return tripMapper.mapToTripDetail(tripEntity, prepareImageDetailList(tripId), prepareTourGroupDetailList(tripId, includeTourGroups));

    }

    @Override
    public List<TripSearchResponse> searchTrip(TripSearchRequest tripSearchRequest) {
        return List.of();
    }

    /**
     * Save trip details included imageList
     *
     * @param
     * @return
     * @remark
     * */
    private TripEntity saveTripDetail(TripCreateRequest request) {

        TripEntity tripEntity = tripRepository.save(prepareTripEntity(request));

        imageCrudRepository.saveAll(prepareImageEntityList(tripEntity.getId(), request));

        return tripEntity;
    }

    private TripEntity prepareTripEntity(TripCreateRequest request) {

        TripEntity tripEntity = tripMapper.mapToEntity(request);

        // active trip
        tripEntity.setStatus(StatusEnum.TRIP_ACTIVE.getStatusCode());

        return tripEntity;
    }

    private List<ImageEntity> prepareImageEntityList(Long tripId, TripCreateRequest request) {

        List<ImageEntity> imageEntityList = imageMapper.batchMapToEntity(request.getImageCreateRequestList());

        imageEntityList.forEach(imageEntity -> {
            imageEntity.setReference_table(ImageEnum.TRIP.getCode());
            imageEntity.setReference_id(tripId);
        });

        return imageEntityList;
    }

    /**
     * Retrieves and maps image entities to detail DTOs for the given trip ID.
     *
     * @param tripId the trip ID to fetch associated images
     * @return a list of {@link ImageDetail}, or an empty list if none are found
     */
    private List<ImageDetail> prepareImageDetailList(Long tripId) {

        List<ImageEntity> imageEntityList = imageCrudRepository.findByTripId(tripId);

        return imageEntityList.isEmpty()
                ? Collections.emptyList()
                : imageMapper.batchMapToImageDetail(imageEntityList);
    }

    /**
     * Retrieves and maps {@link TourGroupEntity} records associated with the given trip ID
     * to a list of {@link TourGroupDetail} DTOs, if tour group inclusion is requested.
     * <p>
     * If {@code includeTourGroups} is {@code false}, or if no associated tour groups are found,
     * this method returns an empty list instead of {@code null}, ensuring null-safety.
     *
     * @param tripId the ID of the trip whose tour groups are to be fetched
     * @param includeTourGroups whether or not to include tour group details in the response
     * @return a list of {@link TourGroupDetail} objects, or an empty list if none are found or inclusion is not requested
     */
    private List<TourGroupDetail> prepareTourGroupDetailList(Long tripId, boolean includeTourGroups) {

        if (!includeTourGroups){
            return Collections.emptyList();
        }

        List<TourGroupEntity> tourGroupEntityList = tourGroupCrudRepository.findByTripId(tripId);

        return tourGroupEntityList.isEmpty() ? Collections.emptyList() : tourGroupMapper.batchMapToTourGroupDetail(tourGroupEntityList);

    }



    /**
     * Finds an existing {@link TripEntity} by its ID.
     * <p>
     * If no matching entity is found, a new empty {@code TripEntity} instance is returned
     * and a {@code DATA_NOT_FOUND} message is added to the provided {@link MessageInfoContainer}.
     * This method never returns {@code null}, so it is safe to call without null checks.
     * However, the returned entity should be used only after checking
     * {@link MessageInfoContainer#throwIfContainsErrors(HttpStatusEnum)}.
     *
     * @param tripId the ID of the trip to look up
     * @param messageInfoContainer the container used to collect validation or error messages
     * @return the found {@code TripEntity}, or a new (empty) one if not found
     */
    private TripEntity findExistedTrip(long tripId, MessageInfoContainer messageInfoContainer) {

        return tripRepository.findById(tripId)
                .orElseGet(() -> {
                    messageInfoContainer.addMessage(MessageCodeEnum.DATA_NOT_FOUND, "trip.id");
                    return new TripEntity();
                });
    }

}
