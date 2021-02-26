package ru.sberbank.sm.ihub.photo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "PHOTO")
public class PhotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    String photoUrl;

    String userId;

    double lat;

    double lon;
}