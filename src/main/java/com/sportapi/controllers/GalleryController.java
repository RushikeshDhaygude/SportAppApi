package com.sportapi.controllers;

import com.sportapi.model.DTO.GalleryDTO;
import com.sportapi.services.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/galleries")
public class GalleryController {

    @Autowired
    private GalleryService galleryService;

    @PostMapping
    public GalleryDTO uploadImage(@RequestParam("organizationId") Long organizationId,
                                  @RequestParam("file") MultipartFile file) throws IOException {
        return galleryService.uploadImage(organizationId, file);
    }

    @PutMapping("/{id}")
    public GalleryDTO updateImage(@PathVariable Long id,
                                  @RequestParam("file") MultipartFile file) throws IOException {
        return galleryService.updateGallery(id, file);
    }

    @GetMapping("/{id}")
    public GalleryDTO getImage(@PathVariable Long id) {
        return galleryService.getGalleryById(id);
    }

    @GetMapping
    public List<GalleryDTO> getAllImages() {
        return galleryService.getAllGalleries();
    }

    @GetMapping("/organization/{organizationId}")
    public List<GalleryDTO> getImagesByOrganizationId(@PathVariable Long organizationId) {
        return galleryService.getGalleriesByOrganizationId(organizationId);
    }

    @DeleteMapping("/{id}")
    public void deleteImage(@PathVariable Long id) throws IOException {
        galleryService.deleteGallery(id);
    }
}
