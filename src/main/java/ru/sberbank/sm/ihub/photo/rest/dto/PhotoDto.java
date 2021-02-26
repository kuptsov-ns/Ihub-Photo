package ru.sberbank.sm.ihub.photo.rest.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhotoDto {
    Long id;
    String photoUrl;
    String userId;
    double lat;
    double lon;
}
