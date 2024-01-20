package com.sportapi.services;

import com.sportapi.model.Gallery;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GalleryService {

    List<Gallery> getAllGalleries();

    Gallery getGalleryById(Long id);

    Gallery createGallery(Gallery gallery);

    Gallery updateGallery(Gallery gallery);

    boolean deleteGallery(Long id);

    void uploadImage(Long galleryId, MultipartFile file);

}
