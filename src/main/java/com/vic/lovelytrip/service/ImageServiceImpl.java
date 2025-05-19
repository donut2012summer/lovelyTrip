package com.vic.lovelytrip.service;

import com.vic.lovelytrip.common.enums.ImageReferenceTableEnum;
import com.vic.lovelytrip.common.enums.ImageStatusEnum;
import com.vic.lovelytrip.dto.ImageCreateRequest;
import com.vic.lovelytrip.entity.ImageEntity;
import com.vic.lovelytrip.mapper.ImageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CopyObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    private final Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

    private final S3Presigner s3Presigner;

    private final String awsBucket = "lovelytrip";
    private final S3Client s3Client;

    private final ImageMapper imageMapper;



    public ImageServiceImpl(S3Presigner s3Presigner, S3Client s3Client, ImageMapper imageMapper) {
        this.s3Presigner = s3Presigner;
        this.s3Client = s3Client;
        this.imageMapper = imageMapper;
    }

     @Override
     public URL generatePreSignedUrl(String filename) {

         final String tmpKey = "tmp/" + UUID.randomUUID() + "_" + filename;

         PutObjectRequest request = PutObjectRequest.builder()
                 .bucket(awsBucket)
                 .key(tmpKey)
                 .contentType("image/jpeg")
                 .build();

         PutObjectPresignRequest preSignedRequest = PutObjectPresignRequest.builder()
                 .putObjectRequest(request)
                 .signatureDuration(Duration.ofMinutes(10))
                 .build();

         return s3Presigner.presignPutObject(preSignedRequest).url();
     }


     @Override
     public boolean relocateImage(String sourceFolder, String targetFolder, String filename) {
         final String sourceKey = sourceFolder + "/" + filename;
         final String targetKey = targetFolder + "/" + filename;

         try{

             // copy obj
             CopyObjectRequest copyObjectRequest = CopyObjectRequest.builder()
                     .sourceBucket(awsBucket)
                     .sourceKey(sourceKey)
                     .destinationBucket(awsBucket)
                     .destinationKey(targetKey)
                     .build();

             s3Client.copyObject(copyObjectRequest);

             // delete obj
             DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                     .bucket(awsBucket)
                     .key(sourceKey)
                     .build();

             s3Client.deleteObject(deleteObjectRequest);

             return true;

         }catch (S3Exception exception){
             logger.error("Failed to relocate S3 object from {} to {}: {}", sourceKey, targetKey, exception.getMessage());
             return false;
         }
     }

     @Override
     public List<ImageEntity> prepareImageEntityListForSaving(List<ImageCreateRequest> imageCreateRequestList, ImageReferenceTableEnum imageReferenceTableEnum, Long referenceId) {
            List<ImageEntity> imageEntityList = imageMapper.batchMapToEntity(imageCreateRequestList);

            for (ImageEntity imageEntity : imageEntityList) {
                processImageEntity(imageEntity, imageReferenceTableEnum, referenceId);
            }
            return imageEntityList;
     }

     private void processImageEntity(ImageEntity imageEntity, ImageReferenceTableEnum imageReferenceTableEnum, Long referenceId) {
            String storedFilename = extractFileNameFromUrl(imageEntity.getImageUrl());

            imageEntity.setImageUrl(generateS3PermanentUrl(storedFilename));
            imageEntity.setStoredFilename(storedFilename);
            imageEntity.setReferenceId(imageReferenceTableEnum.getCode());
            imageEntity.setReferenceId(referenceId);
            imageEntity.setStatus(ImageStatusEnum.TEMPORARY.getCode());

     }

     private String extractFileNameFromUrl(String url) {

        if (url == null || url.isEmpty()) {
            return "";
        }

        try{
            String path = new URL(url).getPath(); // get /tmp/uuid_test.jpg
            return path.substring(path.lastIndexOf("/") + 1); // uuid_test.jpg

        }catch (MalformedURLException malformedURLException){
            return "";
        }
     }

     private String generateS3PermanentUrl(String filename){

         final String awsRegion = "ap-northeast-1";
         final String storedKey = "trip/" + filename;

         return String.format("https://%s.s3.%s.amazonaws.com/%s", awsBucket, awsRegion, storedKey);
     }





}
