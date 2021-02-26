package ru.sberbank.sm.ihub.photo.rest.controller;

import org.springframework.web.bind.annotation.*;
import ru.sberbank.sm.ihub.photo.rest.dto.*;

@RestController
@RequestMapping(value = "ihub-photo/", produces = "application/json")
public interface PhotoController {

    /**
     * Загрузить фото
     *
     * @param uploadPhotoRequest - запрос на загрузку фоток
     * @return {@link UploadPhotoResponse}
     */
    @PostMapping(path = "/uploadPhoto")
    UploadPhotoResponse uploadPhoto(@RequestBody UploadPhotoRequest uploadPhotoRequest);

    /**
     * Получить отсвайпленные фото
     *
     * @param swipeRequest - запрос отсвайпленных фоток
     * @return {@link SwipeResponse}
     */
    @GetMapping(path = "/getSwipePhotos")
    SwipeResponse getSwipePhotos(@RequestBody SwipeRequest swipeRequest);
}