package ru.sberbank.sm.ihub.photo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.sm.ihub.photo.entity.PhotoEntity;

import java.util.List;

public interface PhotoRepository extends JpaRepository<PhotoEntity, Long> {
    List<PhotoEntity> getByUserIdAndLatAndLon(String userId, double lat, double lon);
}