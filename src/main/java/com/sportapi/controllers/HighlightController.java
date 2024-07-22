package com.sportapi.controllers;

import com.sportapi.model.Highlight;
import com.sportapi.config.CloudinaryService;
import com.sportapi.repositories.HighlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/highlights")
public class HighlightController {


    private static final Logger logger = LoggerFactory.getLogger(HighlightController.class);

    private final CloudinaryService cloudinaryService;
    private final HighlightRepository highlightRepository;

    @Autowired
    public HighlightController(CloudinaryService cloudinaryService, HighlightRepository highlightRepository) {
        this.cloudinaryService = cloudinaryService;
        this.highlightRepository = highlightRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<Highlight> uploadHighlight(@RequestParam("file") MultipartFile file,
                                                     @RequestParam("organizationId") Long organizationId,
                                                     @RequestParam("details") String details) {
        logger.info("Received file: {}, organizationId: {}, details: {}", file.getOriginalFilename(), organizationId, details);

        try {
            // Log file size and content type
            logger.info("File size: {} bytes", file.getSize());
            logger.info("Content type: {}", file.getContentType());

            // Upload video to Cloudinary
            Map<String, Object> uploadResult = cloudinaryService.uploadVideo(file);
            String videoFilePath = uploadResult.get("url").toString();

            logger.info("Video uploaded successfully. URL: {}", videoFilePath);

            // Save highlight to the database
            Highlight highlight = new Highlight();
            highlight.setOrganizationId(organizationId);
            highlight.setVideoFilePath(videoFilePath);
            highlight.setDetails(details);
            highlight.setLikes(0);

            highlight = highlightRepository.save(highlight);
            return ResponseEntity.ok(highlight);
        } catch (IOException e) {
            logger.error("Error uploading video to Cloudinary", e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/organization/{organizationId}")
    public ResponseEntity<List<Highlight>> getHighlightsByOrganizationId(@PathVariable Long organizationId) {
        List<Highlight> highlights = highlightRepository.findByOrganizationId(organizationId);
        return ResponseEntity.ok(highlights);
    }

    @GetMapping
    public ResponseEntity<List<Highlight>> getAllHighlights() {
        List<Highlight> highlights = highlightRepository.findAll();
        return ResponseEntity.ok(highlights);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Highlight> updateHighlight(@PathVariable Long id,
                                                     @RequestParam("details") String details) {
        Optional<Highlight> highlightOptional = highlightRepository.findById(id);
        if (highlightOptional.isPresent()) {
            Highlight highlight = highlightOptional.get();
            highlight.setDetails(details);
            highlight = highlightRepository.save(highlight);
            return ResponseEntity.ok(highlight);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHighlight(@PathVariable Long id) {
        Optional<Highlight> highlightOptional = highlightRepository.findById(id);
        if (highlightOptional.isPresent()) {
            highlightRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<Highlight> likeHighlight(@PathVariable Long id) {
        Optional<Highlight> highlightOptional = highlightRepository.findById(id);
        if (highlightOptional.isPresent()) {
            Highlight highlight = highlightOptional.get();
            highlight.setLikes(highlight.getLikes() + 1);
            highlight = highlightRepository.save(highlight);
            return ResponseEntity.ok(highlight);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
