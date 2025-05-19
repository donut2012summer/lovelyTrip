package com.vic.lovelytrip.service;

import com.vic.lovelytrip.common.enums.ImageReferenceTableEnum;
import com.vic.lovelytrip.dto.ImageCreateRequest;
import com.vic.lovelytrip.entity.ImageEntity;

import java.net.URL;
import java.util.List;

public interface ImageService {

    public URL generatePreSignedUrl(String filename);

    public boolean relocateImage(String sourceFolder, String targetFolder, String filename);

    public List<ImageEntity> prepareImageEntityListForSaving(List<ImageCreateRequest> imageCreateRequestList, ImageReferenceTableEnum imageReferenceTableEnum, Long referenceId) ;
}
