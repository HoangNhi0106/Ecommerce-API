package com.nashtech.ecommerceapi.service.impl;

import com.nashtech.ecommerceapi.entity.Image;
import com.nashtech.ecommerceapi.repository.ImageRepository;
import com.nashtech.ecommerceapi.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public Image getImageById(long image_id) {
        return imageRepository.findById(image_id).get();
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public void saveImage(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setData(file.getBytes());

        imageRepository.save(image);
    }
}
