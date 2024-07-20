//package com.sportapi.controllers;
//
//import com.cloudinary.Cloudinary;
//import com.cloudinary.utils.ObjectUtils;
//import com.sportapi.model.Gallery;
//import com.sportapi.model.Organization;
//import com.sportapi.repositories.GalleryRepository;
//import com.sportapi.repositories.OrganizationRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.Map;
//
//@RestController
//@RequestMapping("api/galleries")
//public class GalleryController {
//
//    @Autowired
//    private GalleryRepository galleryRepository;
//
//    @Autowired
//    private OrganizationRepository organizationRepository;
//
//    @Autowired
//    private Cloudinary cloudinary;
//
//    @PostMapping
//    public Gallery uploadImage(@RequestParam("organizationId") Long organizationId,
//                               @RequestParam("file") MultipartFile file) throws IOException {
//        // Find the organization
//        Organization organization = organizationRepository.findById(organizationId)
//                .orElseThrow(() -> new RuntimeException("Organization not found"));
//
//        // Upload the image to Cloudinary
//        Map<String, String> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
//
//        // Get image details
//        String imagePath = uploadResult.get("secure_url");
//        String imageName = uploadResult.get("public_id"); // Cloudinary's public ID
//
//        // Create Gallery object
//        Gallery gallery = new Gallery();
//        gallery.setOrganization(organization);
//        gallery.setImagePath(imagePath);
//        gallery.setImageName(imageName);
//
//        return galleryRepository.save(gallery);
//    }
//
//    @PutMapping("/{id}")
//    public Gallery updateImage(@PathVariable Long id,
//                               @RequestParam("file") MultipartFile file) throws IOException {
//        // Find the gallery entry
//        Gallery gallery = galleryRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Gallery not found"));
//
//        // Upload the new image to Cloudinary
//        Map<String, String> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
//
//        // Update image details
//        gallery.setImagePath(uploadResult.get("secure_url")); // New image URL
//        gallery.setImageName(uploadResult.get("public_id")); // New image name
//
//        return galleryRepository.save(gallery);
//    }
//
//    @GetMapping("/{id}")
//    public Gallery getImage(@PathVariable Long id) {
//        return galleryRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Gallery not found"));
//    }
//
//    @GetMapping
//    public Iterable<Gallery> getAllImages() {
//        return galleryRepository.findAll();
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteImage(@PathVariable Long id) throws IOException {
//        // Find the gallery entry
//        Gallery gallery = galleryRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Gallery not found"));
//
//        // Extract public ID from the image name
//        String publicId = gallery.getImageName();
//
//        // Delete the image from Cloudinary
//        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
//
//        // Delete the gallery entry from the database
//        galleryRepository.delete(gallery);
//    }
//}
package com.sportapi.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.sportapi.model.Gallery;
import com.sportapi.model.Organization;
import com.sportapi.repositories.GalleryRepository;
import com.sportapi.repositories.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("api/galleries")
public class GalleryController {

    @Autowired
    private GalleryRepository galleryRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private Cloudinary cloudinary;

    @PostMapping
    public Gallery uploadImage(@RequestParam("organizationId") Long organizationId,
                               @RequestParam("file") MultipartFile file) throws IOException {
        // Find the organization
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new RuntimeException("Organization not found"));

        // Upload the image to Cloudinary
        Map<String, String> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

        // Get image details
        String imagePath = uploadResult.get("secure_url");
        String imageName = uploadResult.get("public_id"); // Cloudinary's public ID

        // Create Gallery object
        Gallery gallery = new Gallery();
        gallery.setOrganization(organization);
        gallery.setImagePath(imagePath);
        gallery.setImageName(imageName);

        return galleryRepository.save(gallery);
    }

    @PutMapping("/{id}")
    public Gallery updateImage(@PathVariable Long id,
                               @RequestParam("file") MultipartFile file) throws IOException {
        // Find the gallery entry
        Gallery gallery = galleryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gallery not found"));

        // Upload the new image to Cloudinary
        Map<String, String> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

        // Update image details
        gallery.setImagePath(uploadResult.get("secure_url")); // New image URL
        gallery.setImageName(uploadResult.get("public_id")); // New image name

        return galleryRepository.save(gallery);
    }

    @GetMapping("/{id}")
    public Gallery getImage(@PathVariable Long id) {
        return galleryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gallery not found"));
    }

    @GetMapping
    public Iterable<Gallery> getAllImages() {
        return galleryRepository.findAll();
    }

    @GetMapping("/organization/{organizationId}")
    public Iterable<Gallery> getImagesByOrganizationId(@PathVariable Long organizationId) {
        return galleryRepository.findByOrganizationId(organizationId);
    }

    @DeleteMapping("/{id}")
    public void deleteImage(@PathVariable Long id) throws IOException {
        // Find the gallery entry
        Gallery gallery = galleryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gallery not found"));

        // Extract public ID from the image name
        String publicId = gallery.getImageName();

        // Delete the image from Cloudinary
        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());

        // Delete the gallery entry from the database
        galleryRepository.delete(gallery);
    }
}
