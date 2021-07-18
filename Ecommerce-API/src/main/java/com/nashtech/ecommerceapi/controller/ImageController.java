package com.nashtech.ecommerceapi.controller;

import com.nashtech.ecommerceapi.constant.ErrorCode;
import com.nashtech.ecommerceapi.constant.SuccessCode;
import com.nashtech.ecommerceapi.converter.ImageConverter;
import com.nashtech.ecommerceapi.dto.ImageDTO;
import com.nashtech.ecommerceapi.dto.ResponseDTO;
import com.nashtech.ecommerceapi.entity.Image;
import com.nashtech.ecommerceapi.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
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
                responseDTO.setSuccessCode("yes");
            }
        } catch (Exception exception) {
            responseDTO.setErrorCode("no");
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
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_PRODUCT_SAVED);
        } catch (Exception e) {
            responseDTO.setData(false);
            responseDTO.setErrorCode(ErrorCode.ERROR_PRODUCT_NOT_SAVED);
        }
        return ResponseEntity.ok().body(responseDTO);
    }
}
