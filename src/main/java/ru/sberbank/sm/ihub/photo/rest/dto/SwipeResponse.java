package ru.sberbank.sm.ihub.photo.rest.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SwipeResponse {
    List<PhotoDto> photos;
}