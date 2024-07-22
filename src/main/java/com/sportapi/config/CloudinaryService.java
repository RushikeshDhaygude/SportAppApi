package com.sportapi.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;
    private static final Logger logger = LoggerFactory.getLogger(CloudinaryService.class);


    @Autowired
    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        return uploadResult.get("url").toString();
    }

    public Map<String, Object> uploadVideo(MultipartFile file) throws IOException {
        logger.info("Uploading video: {}", file.getOriginalFilename());

        // Check file size
        if (file.getSize() > 100000000) { // 100 MB limit, adjust as needed
            logger.error("File size exceeds limit");
            throw new IOException("File size exceeds limit");
        }

        // Upload video
        Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap("resource_type", "video","secure", true));
        logger.info("Upload result: {}", uploadResult);
        return uploadResult;
    }
}
