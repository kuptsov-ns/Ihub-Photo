package ru.sberbank.sm.ihub.photo.rest.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadPhotoRequest {
    String userId;
    String photoUrl;
    double lat;
    double lon;
}
