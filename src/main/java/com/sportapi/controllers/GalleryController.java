package com.sportapi.controllers;// ... (imports)

import com.sportapi.model.Gallery;
import com.sportapi.model.Organization;
import com.sportapi.services.GalleryService;
import com.sportapi.services.Impl.FileUploadService;
import com.sportapi.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
// ... (imports)

@RestController
@RequestMapping("/api/galleries")
public class GalleryController {

    private final GalleryService galleryService;
    private final FileUploadService fileUploadService;
    private final OrganizationService organizationService;

    @Autowired
    public GalleryController(
            GalleryService galleryService,
            FileUploadService fileUploadService,
            OrganizationService organizationService) {
        this.galleryService = galleryService;
        this.fileUploadService = fileUploadService;
        this.organizationService = organizationService;
    }

    @GetMapping
    public ResponseEntity<List<Gallery>> getAllGalleries() {
        try {
            List<Gallery> galleries = galleryService.getAllGalleries();
            return new ResponseEntity<>(galleries, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gallery> getGalleryById(@PathVariable Long id) {
        try {
            Gallery gallery = galleryService.getGalleryById(id);
            if (gallery != null) {
                return new ResponseEntity<>(gallery, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Gallery> createGallery(
            @RequestParam("organizationId") Long organizationId,
            @RequestPart("file") MultipartFile file) {

        try {
            Organization organization = organizationService.getOrganizationById(organizationId);

            if (organization == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Gallery gallery = new Gallery();
            gallery.setOrganization(organization);

            String imagePath = fileUploadService.uploadFile(file, "images/galleries");
            gallery.setImagePath(imagePath);

            Gallery createdGallery = galleryService.createGallery(gallery);

            return new ResponseEntity<>(createdGallery, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gallery> updateGallery(
            @PathVariable Long id,
            @RequestParam("organizationId") Long organizationId,
            @RequestPart(value = "file", required = false) MultipartFile file) {

        try {
            Organization organization = organizationService.getOrganizationById(organizationId);

            if (organization == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Gallery existingGallery = galleryService.getGalleryById(id);

            if (existingGallery != null) {
                // Update the existing gallery properties
                existingGallery.setOrganization(organization);

                if (file != null && !file.isEmpty()) {
                    String imagePath = fileUploadService.uploadFile(file, "images/galleries");
                    existingGallery.setImagePath(imagePath);
                }

                // Save the updated gallery
                Gallery updatedGalleryResult = galleryService.updateGallery(existingGallery);

                return new ResponseEntity<>(updatedGalleryResult, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGallery(@PathVariable Long id) {
        try {
            if (galleryService.deleteGallery(id)) {
                return new ResponseEntity<>("Gallery deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Gallery not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return new ResponseEntity<>("Error deleting gallery", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
