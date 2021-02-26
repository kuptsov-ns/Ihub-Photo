package ru.sberbank.sm.ihub.photo.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.sm.ihub.photo.entity.PhotoEntity;
import ru.sberbank.sm.ihub.photo.rest.dto.*;
import ru.sberbank.sm.ihub.photo.service.PhotoService;

import java.util.List;

@Service
public class PhotoControllerImpl implements PhotoController {

    private final PhotoService photoService;

    @Autowired
    public PhotoControllerImpl(PhotoService photoService) {
        this.photoService = photoService;
    }

    @Override
    public UploadPhotoResponse uploadPhoto(UploadPhotoRequest uploadPhotoRequest) {
        PhotoEntity photoEntity = photoService.uploadPhoto(uploadPhotoRequest);
        return UploadPhotoResponse.builder().photoId(photoEntity.getId()).build();
    }

    @Override
    public SwipeResponse getSwipePhotos(SwipeRequest swipeRequest) {
        List<PhotoDto> photoDtoList = photoService.getSwipePhotos(swipeRequest);
        return SwipeResponse.builder().photos(photoDtoList).build();
    }
}