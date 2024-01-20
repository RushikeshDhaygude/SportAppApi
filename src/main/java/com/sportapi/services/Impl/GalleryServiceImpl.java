package com.sportapi.services.Impl;

import com.sportapi.model.Gallery;
import com.sportapi.repositories.GalleryRepository;
import com.sportapi.services.Impl.FileUploadService;
import com.sportapi.services.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class GalleryServiceImpl implements GalleryService {

    private final GalleryRepository galleryRepository;
    private final FileUploadService fileUploadService;

    @Autowired
    public GalleryServiceImpl(GalleryRepository galleryRepository, FileUploadService fileUploadService) {
        this.galleryRepository = galleryRepository;
        this.fileUploadService = fileUploadService;
    }

    @Override
    public List<Gallery> getAllGalleries() {
        return galleryRepository.findAll();
    }

    @Override
    public Gallery getGalleryById(Long id) {
        return galleryRepository.findById(id).orElse(null);
    }

    @Override
    public Gallery createGallery(Gallery gallery) {
        return galleryRepository.save(gallery);
    }

    @Override
    public Gallery updateGallery(Gallery gallery) {
        return galleryRepository.save(gallery);
    }

    @Override
    public boolean deleteGallery(Long id) {
        if (galleryRepository.existsById(id)) {
            galleryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void uploadImage(Long galleryId, MultipartFile file) {
        Gallery gallery = galleryRepository.findById(galleryId).orElse(null);

        if (gallery != null) {
            try {
                String imagePath = fileUploadService.uploadFile(file, "images/galleries");
                gallery.setImagePath(imagePath);
                galleryRepository.save(gallery);
            } catch (Exception e) {
                e.printStackTrace(); // Handle exception as needed
            }
        }
    }
}
