package com.nashtech.ecommerceapi.controller;

import com.nashtech.ecommerceapi.constant.ErrorCode;
import com.nashtech.ecommerceapi.constant.SuccessCode;
import com.nashtech.ecommerceapi.converter.ImageConverter;
import com.nashtech.ecommerceapi.dto.ImageDTO;
import com.nashtech.ecommerceapi.dto.ResponseDTO;
import com.nashtech.ecommerceapi.entity.Image;
import com.nashtech.ecommerceapi.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @Autowired
    private ImageConverter imageConverter;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO> findAllImages() {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            List<ImageDTO> imageDTOs = new ArrayList<>();
            List<Image> images = imageService.getAllImages();
            for (Image image : images) {
                imageDTOs.add(imageConverter.convertToDto(image));
            }
            if (imageDTOs != null) {
                responseDTO.setData(imageDTOs);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_IMAGE_FOUND);
            }
        } catch (Exception exception) {
            responseDTO.setErrorCode(ErrorCode.ERROR_IMAGE_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseDTO> uploadImage(@RequestParam("file") MultipartFile file) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            imageService.saveImage(file);

            responseDTO.setData(true);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_IMAGE_SAVED);
        } catch (Exception e) {
            responseDTO.setData(false);
            responseDTO.setErrorCode(ErrorCode.ERROR_IMAGE_NOT_SAVED);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("{image_id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<byte[]> getFile(@PathVariable String image_id) {
        try {
            Image image = imageService.getImageById(image_id);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; imageId=\"" + image.getImageId() + "\"")
                    .contentType(MediaType.valueOf(image.getContentType()))
                    .body(image.getData());
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }
}
