package com.vic.lovelytrip.service;

import com.vic.lovelytrip.common.annotation.Traceable;
import com.vic.lovelytrip.common.enums.HttpStatusEnum;
import com.vic.lovelytrip.common.enums.ImageReferenceTableEnum;
import com.vic.lovelytrip.common.enums.ImageStatusEnum;
import com.vic.lovelytrip.common.enums.TripStatusEnum;
import com.vic.lovelytrip.common.message.MessageCodeEnum;
import com.vic.lovelytrip.common.message.MessageInfoContainer;
import com.vic.lovelytrip.dto.*;
import com.vic.lovelytrip.entity.ImageEntity;
import com.vic.lovelytrip.entity.TourGroupEntity;
import com.vic.lovelytrip.entity.TripEntity;
import com.vic.lovelytrip.mapper.ImageMapper;
import com.vic.lovelytrip.mapper.TourGroupMapper;
import com.vic.lovelytrip.mapper.TripMapper;
import com.vic.lovelytrip.repository.ImageRepository;
import com.vic.lovelytrip.repository.TourGroupCrudRepository;
import com.vic.lovelytrip.repository.TripRepository;
import com.vic.lovelytrip.validator.TripValidator;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TripServiceImpl implements TripService {

    private final ImageRepository imageRepository;

    private final TourGroupCrudRepository tourGroupCrudRepository;

    private final TripRepository tripRepository;

    private final TripValidator tripValidator;

    private final TripMapper tripMapper;

    private final ImageMapper imageMapper = new ImageMapper();

    private final TourGroupMapper tourGroupMapper = new TourGroupMapper();
    private final ImageService imageService;

    public TripServiceImpl (TripRepository tripRepository, TripValidator tripValidator, TripMapper tripMapper, ImageRepository imageRepository, TourGroupCrudRepository tourGroupCrudRepository, ImageService imageService) {
        this.tripRepository = tripRepository;
        this.tripValidator = tripValidator;
        this.tripMapper = tripMapper;
        this.imageRepository = imageRepository;
        this.tourGroupCrudRepository = tourGroupCrudRepository;
        this.imageService = imageService;
    }

    @Override
    public TripCreateResponse createTrip(TripCreateRequest tripCreateRequest) {

        tripValidator.validateCreateTripRequest(tripCreateRequest)
                     .throwIfContainsErrors(HttpStatusEnum.BAD_REQUEST);

        return tripMapper.mapToCreateResponse(saveTripDetail(tripCreateRequest));
    }

    @Override
    @Traceable
    public TripDetailResponse getTripDetailById(Long id, boolean includeTourGroups) {

        MessageInfoContainer messageInfoContainer = new MessageInfoContainer();

        tripValidator.checkTripIdFormat(id, messageInfoContainer);

        messageInfoContainer.throwIfContainsErrors(HttpStatusEnum.BAD_REQUEST);

        TripEntity tripEntity = findExistedTrip(id, messageInfoContainer);

        messageInfoContainer.throwIfContainsErrors(HttpStatusEnum.BAD_REQUEST);

        Long tripId = tripEntity.getId();

        return tripMapper.mapToTripDetailResponse(tripEntity, prepareImageDetailList(tripId), prepareTourGroupDetailList(tripId, includeTourGroups));

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

        Long tripId = tripEntity.getId();

        List<ImageEntity> imageEntityList = imageService.prepareImageEntityListForSaving(request.getImageCreateRequestList(), ImageReferenceTableEnum.TRIP, tripId);
        imageRepository.saveAll(imageEntityList);

        for (ImageEntity imageEntity : imageEntityList) {
            storeImagePermanently(imageEntity);
        }

        return tripEntity;
    }

    private void storeImagePermanently(ImageEntity imageEntity) {

        boolean isImageSaved = imageService.relocateImage("tmp", "trip", imageEntity.getStoredFilename());

        if (isImageSaved){
            imageEntity.setStatus(ImageStatusEnum.STORED.getCode());
        }
    }





    private TripEntity prepareTripEntity(TripCreateRequest request) {

        TripEntity tripEntity = tripMapper.mapToEntity(request);

        // active trip
        tripEntity.setStatus(TripStatusEnum.PUBLISHED.getStatusCode());

        return tripEntity;
    }

    private List<ImageEntity> prepareImageEntityList(Long tripId, TripCreateRequest request) {

        List<ImageEntity> imageEntityList = imageMapper.batchMapToEntity(request.getImageCreateRequestList());

        // generate saved url

        imageEntityList.forEach(imageEntity -> {
            imageEntity.setReferenceTable(ImageReferenceTableEnum.TRIP.getCode());
            imageEntity.setReferenceId(tripId);
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

        List<ImageEntity> imageEntityList = imageRepository.findByReferenceIdAndReferenceTable(tripId, ImageReferenceTableEnum.TRIP.getCode());

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
