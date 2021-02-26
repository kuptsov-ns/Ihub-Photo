package ru.sberbank.sm.ihub.photo.rest.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SwipeRequest {
    String userId;
    double lat;
    double lon;
    long distance;
}
