package com.nashtech.ecommerceapi.controller;

import com.nashtech.ecommerceapi.constant.ErrorCode;
import com.nashtech.ecommerceapi.constant.SuccessCode;
import com.nashtech.ecommerceapi.converter.RatingConverter;
import com.nashtech.ecommerceapi.dto.RatingDTO;
import com.nashtech.ecommerceapi.dto.ResponseDTO;
import com.nashtech.ecommerceapi.entity.Product;
import com.nashtech.ecommerceapi.entity.Rating;
import com.nashtech.ecommerceapi.exception.RatingException;
import com.nashtech.ecommerceapi.repository.ProductRepository;
import com.nashtech.ecommerceapi.service.ProductService;
import com.nashtech.ecommerceapi.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value = "/rating")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @Autowired
    private RatingConverter ratingConverter;

    @Autowired
    private ProductService productService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO> findAllRating() {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            List<Rating> ratings = ratingService.getAllRating();
            if (ratings != null) {
                for (Rating rating : ratings) {
                    responseDTO.setData(ratingConverter.convertToDto(rating));
                }
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_RATING_FOUND);
            }
        } catch (Exception exception) {
            responseDTO.setErrorCode(ErrorCode.ERROR_RATING_NOT_FOUND);
            throw new RatingException(ErrorCode.ERROR_RATING_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping(value = "/save")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO> saveProduct(@Valid @RequestBody RatingDTO ratingDTO) throws ParseException {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Rating rating = ratingConverter.convertToEntity(ratingDTO);
            Rating saveRating = ratingService.addRating(rating);
            if (saveRating != null) {
                responseDTO.setData(ratingConverter.convertToDto(rating));
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_RATING_SAVED);

                Product product = productService.getProductById(ratingDTO.getProduct_id());
                product.setRating(productService.calculateRatingStar(product));
                productService.updateProduct(product);
            }
        } catch (Exception exception) {
            responseDTO.setErrorCode(ErrorCode.ERROR_RATING_NOT_SAVED);
            throw new RatingException(ErrorCode.ERROR_RATING_NOT_SAVED);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping(value = "/delete/{rating_id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseDTO> deleteRating(@PathVariable Long rating_id) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Rating rating = ratingService.getRatingById(rating_id);
            if (rating != null) {
                responseDTO.setData(true);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_RATING_FOUND);

                Product product = productService.getProductById(rating.getProduct().getProduct_id());
                ratingService.deleteRating(rating_id);
                product.setRating(productService.calculateRatingStar(product));
                productService.updateProduct(product);
            }
        } catch (Exception exception) {
            responseDTO.setData(false);
            responseDTO.setErrorCode(ErrorCode.ERROR_RATING_NOT_FOUND);
            throw new RatingException(ErrorCode.ERROR_RATING_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }
}
