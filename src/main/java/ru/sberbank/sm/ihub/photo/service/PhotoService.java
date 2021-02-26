package ru.sberbank.sm.ihub.photo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.sberbank.sm.ihub.photo.entity.PhotoEntity;
import ru.sberbank.sm.ihub.photo.repository.PhotoRepository;
import ru.sberbank.sm.ihub.photo.rest.dto.PhotoDto;
import ru.sberbank.sm.ihub.photo.rest.dto.SwipeRequest;
import ru.sberbank.sm.ihub.photo.rest.dto.UploadPhotoRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final PhotoMapper photoMapper;

    private Map<Long, Boolean> photoSeen = new HashMap<>();
    private Map<String, Long> photoDistance = new HashMap<>();

    @Autowired
    public PhotoService(PhotoRepository photoRepository,
                        PhotoMapper photoMapper) {
        this.photoRepository = photoRepository;
        this.photoMapper = photoMapper;
    }

    public List<PhotoDto> getSwipePhotos(SwipeRequest swipeRequest) {
        List<PhotoDto> photos;
        if (!photoDistance.containsKey(swipeRequest.getUserId())) {
            photos = getAllPhotos(swipeRequest);
            fillMaps(swipeRequest, photos);
            return photos;
        }
        if (swipeRequest.getDistance() <= photoDistance.get(swipeRequest.getUserId())) {
            photos = getNewPhotos(swipeRequest);
        } else {
            photos = getAllPhotos(swipeRequest);
        }
        fillMaps(swipeRequest, photos);
        return photos;
    }

    private void fillMaps(SwipeRequest swipeRequest, List<PhotoDto> photos) {
        photos.forEach(k -> photoSeen.put(k.getId(), true));
        photoDistance.put(swipeRequest.getUserId(), swipeRequest.getDistance());
    }

    public PhotoEntity uploadPhoto(UploadPhotoRequest uploadPhotoRequest) {
        return photoRepository.save(photoMapper.uploadPhotoRequestToPhoto(uploadPhotoRequest));
    }

    private List<PhotoDto> getAllPhotos(SwipeRequest swipeRequest) {
        return getPhotosFromDb(swipeRequest.getUserId(),
                swipeRequest.getLat(), swipeRequest.getLon()).stream()
                .map(photoMapper::photoEntityToPhotoDto)
                .collect(Collectors.toList());
    }

    private List<PhotoDto> getNewPhotos(SwipeRequest swipeRequest) {
        return getPhotosFromDb(swipeRequest.getUserId(),
                swipeRequest.getLat(), swipeRequest.getLon()).stream()
                .filter(photoEntity -> !photoSeen.containsKey(photoEntity.getId()))
                .map(photoMapper::photoEntityToPhotoDto)
                .collect(Collectors.toList());
    }

    @Cacheable("photos")
    public List<PhotoEntity> getPhotosFromDb(String userId, double lat, double lon) {
        return photoRepository.getByUserIdAndLatAndLon(userId, lat, lon);
    }
}