package com.sportapi.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.sportapi.model.DTO.GalleryDTO;
import com.sportapi.model.DTO.OrganizationWithoutEventsDTO;
import com.sportapi.model.Gallery;
import com.sportapi.model.Organization;
import com.sportapi.repositories.GalleryRepository;
import com.sportapi.repositories.OrganizationRepository;
import com.sportapi.services.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GalleryServiceImpl implements GalleryService {

    private final GalleryRepository galleryRepository;
    private final OrganizationRepository organizationRepository;
    private final Cloudinary cloudinary;

    @Autowired
    public GalleryServiceImpl(GalleryRepository galleryRepository, OrganizationRepository organizationRepository, Cloudinary cloudinary) {
        this.galleryRepository = galleryRepository;
        this.organizationRepository = organizationRepository;
        this.cloudinary = cloudinary;
    }

    @Override
    public List<GalleryDTO> getAllGalleries() {
        return StreamSupport.stream(galleryRepository.findAll().spliterator(), false)
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public GalleryDTO getGalleryById(Long id) {
        return galleryRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    @Override
    public GalleryDTO createGallery(GalleryDTO galleryDTO) {
        Gallery gallery = new Gallery();
        gallery.setOrganization(organizationRepository.findById(galleryDTO.getOrganization().getId()).orElse(null));
        gallery.setImagePath(galleryDTO.getImagePath());
        gallery.setImageName(galleryDTO.getImageName());

        return convertToDto(galleryRepository.save(gallery));
    }

    @Override
    public GalleryDTO updateGallery(Long id, MultipartFile file) throws IOException {
        Gallery gallery = galleryRepository.findById(id).orElse(null);

        if (gallery != null) {
            Map<String, String> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            gallery.setImagePath(uploadResult.get("secure_url"));
            gallery.setImageName(uploadResult.get("public_id"));

            return convertToDto(galleryRepository.save(gallery));
        }
        return null;
    }

    @Override
    public boolean deleteGallery(Long id) throws IOException {
        if (galleryRepository.existsById(id)) {
            Gallery gallery = galleryRepository.findById(id).orElse(null);
            if (gallery != null) {
                String publicId = gallery.getImageName();
                cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
                galleryRepository.delete(gallery);
                return true;
            }
        }
        return false;
    }

    @Override
    public GalleryDTO uploadImage(Long organizationId, MultipartFile file) throws IOException {
        Organization organization = organizationRepository.findById(organizationId).orElse(null);

        if (organization != null) {
            Map<String, String> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String imagePath = uploadResult.get("secure_url");
            String imageName = uploadResult.get("public_id");

            Gallery gallery = new Gallery();
            gallery.setOrganization(organization);
            gallery.setImagePath(imagePath);
            gallery.setImageName(imageName);

            return convertToDto(galleryRepository.save(gallery));
        }
        return null;
    }

    @Override
    public List<GalleryDTO> getGalleriesByOrganizationId(Long organizationId) {
        return StreamSupport.stream(galleryRepository.findByOrganizationId(organizationId).spliterator(), false)
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private GalleryDTO convertToDto(Gallery gallery) {
        Organization organization = gallery.getOrganization();
        OrganizationWithoutEventsDTO organizationDTO = new OrganizationWithoutEventsDTO(
                organization.getId(), organization.getName(), organization.getContact(), organization.getEmail()
        );
        return new GalleryDTO(gallery.getId(), organizationDTO, gallery.getImagePath(), gallery.getImageName());
    }
}
