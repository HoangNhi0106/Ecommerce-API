package com.nashtech.ecommerceapi.service;

import com.nashtech.ecommerceapi.entity.Image;
import com.nashtech.ecommerceapi.exception.CreateDataFailException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    public Image saveImage(MultipartFile file) throws IOException, CreateDataFailException;

    public Image getImageById(String image_id);

    public List<Image> getAllImages();
}
