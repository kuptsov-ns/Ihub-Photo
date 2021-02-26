package ru.sberbank.sm.ihub.photo.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.sberbank.sm.ihub.photo.entity.PhotoEntity;
import ru.sberbank.sm.ihub.photo.rest.dto.PhotoDto;
import ru.sberbank.sm.ihub.photo.rest.dto.SwipeRequest;
import ru.sberbank.sm.ihub.photo.rest.dto.UploadPhotoRequest;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PhotoMapper {
    @Mapping(target = "id", ignore = true)
    PhotoEntity uploadPhotoRequestToPhoto(UploadPhotoRequest uploadPhotoRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "photoUrl", ignore = true)
    PhotoDto swipeRequestToPhoto(SwipeRequest swipeRequest);

    PhotoDto photoEntityToPhotoDto(PhotoEntity photoEntity);
}