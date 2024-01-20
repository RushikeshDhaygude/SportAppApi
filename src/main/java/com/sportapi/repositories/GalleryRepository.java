package com.sportapi.repositories;

import com.sportapi.model.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {
}
