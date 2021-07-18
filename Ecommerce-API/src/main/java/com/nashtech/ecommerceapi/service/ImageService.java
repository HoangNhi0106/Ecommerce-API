package com.nashtech.ecommerceapi.service;

import com.nashtech.ecommerceapi.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    public void saveImage(MultipartFile file) throws IOException;

    public Image getImageById(long image_id);

    public List<Image> getAllImages();
}
