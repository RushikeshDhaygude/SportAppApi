package com.sportapi.services;

import com.sportapi.model.DTO.GalleryDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface GalleryService {
    List<GalleryDTO> getAllGalleries();
    GalleryDTO getGalleryById(Long id);
    GalleryDTO createGallery(GalleryDTO galleryDTO);
    GalleryDTO updateGallery(Long id, MultipartFile file) throws IOException;
    boolean deleteGallery(Long id) throws IOException;
    GalleryDTO uploadImage(Long organizationId, MultipartFile file) throws IOException;
    List<GalleryDTO> getGalleriesByOrganizationId(Long organizationId);
}
