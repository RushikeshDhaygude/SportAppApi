package com.sportapi.services.Impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class FileUploadService {

    @Value("${upload.base-dir}")
    private String baseDir;

    public String uploadFile(MultipartFile file, String subDirectory) {
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path uploadPath = Path.of(baseDir, subDirectory);
            Path filePath = Path.of(uploadPath.toString(), fileName);

            Files.createDirectories(uploadPath);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return filePath.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }
}
