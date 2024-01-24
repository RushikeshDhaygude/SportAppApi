package com.sportapi.services.Impl;

import com.sportapi.model.Gallery;
import com.sportapi.repositories.GalleryRepository;
import com.sportapi.services.Impl.FileUploadService;
import com.sportapi.services.GalleryService;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.service.spi.ServiceException;
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
        try {
            return galleryRepository.findAll();
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw new ServiceException("Error getting all galleries", e);
        }
    }

    @Override
    public Gallery getGalleryById(Long id) {
        try {
            return galleryRepository.findById(id).orElse(null);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw new ServiceException("Error getting gallery by ID: " + id, e);
        }
    }

    @Override
    public Gallery createGallery(Gallery gallery) {
        try {
            return galleryRepository.save(gallery);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw new ServiceException("Error creating gallery", e);
        }
    }

    @Override
    public Gallery updateGallery(Gallery gallery) {
        try {
            return galleryRepository.save(gallery);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw new ServiceException("Error updating gallery", e);
        }
    }

    @Override
    public boolean deleteGallery(Long id) {
        try {
            if (galleryRepository.existsById(id)) {
                galleryRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw new ServiceException("Error deleting gallery with ID: " + id, e);
        }
    }

    @Override
    public void uploadImage(Long galleryId, MultipartFile file) {
        try {
            Gallery gallery = galleryRepository.findById(galleryId)
                    .orElseThrow(() -> new EntityNotFoundException("Gallery not found with ID: " + galleryId));

            String imagePath = fileUploadService.uploadFile(file, "images/galleries");
            gallery.setImagePath(imagePath);
            galleryRepository.save(gallery);

        } catch (EntityNotFoundException e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw e;

        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw new ServiceException("Unexpected error in uploadImage for Gallery ID: " + galleryId, e);
        }
    }
}
