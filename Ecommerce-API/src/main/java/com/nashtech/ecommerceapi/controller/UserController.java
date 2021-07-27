package com.nashtech.ecommerceapi.controller;

import com.nashtech.ecommerceapi.constant.ErrorCode;
import com.nashtech.ecommerceapi.constant.SuccessCode;
import com.nashtech.ecommerceapi.converter.AccountConverter;
import com.nashtech.ecommerceapi.converter.RatingConverter;
import com.nashtech.ecommerceapi.dto.AccountDTO;
import com.nashtech.ecommerceapi.dto.RatingDTOPost;
import com.nashtech.ecommerceapi.dto.ResponseDTO;
import com.nashtech.ecommerceapi.entity.Account;
import com.nashtech.ecommerceapi.entity.Image;
import com.nashtech.ecommerceapi.entity.Rating;
import com.nashtech.ecommerceapi.exception.CreateDataFailException;
import com.nashtech.ecommerceapi.exception.DataNotFoundException;
import com.nashtech.ecommerceapi.exception.UpdateDataFailException;
import com.nashtech.ecommerceapi.service.AccountService;
import com.nashtech.ecommerceapi.service.ImageService;
import com.nashtech.ecommerceapi.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.text.ParseException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/user")
public class UserController {
    //Service
    @Autowired
    private RatingService ratingService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private AccountService accountService;

    //Converter
    @Autowired
    private RatingConverter ratingConverter;

    @Autowired
    private AccountConverter accountConverter;

    //RatingController
    @PostMapping(value = "/rating/save")
    public ResponseEntity<ResponseDTO> saveRating(@Valid @RequestBody RatingDTOPost ratingDTOPost) throws DataNotFoundException, CreateDataFailException, UpdateDataFailException, ParseException {
        ResponseDTO responseDTO = new ResponseDTO();
        Rating rating = ratingConverter.convertToEntity(ratingDTOPost);
        ratingService.addRating(rating);
        responseDTO.setData(ratingConverter.convertToDto(rating));
        responseDTO.setSuccessCode(SuccessCode.SUCCESS_RATING_SAVED);
        return ResponseEntity.ok().body(responseDTO);
    }

    //AccountController
    @PutMapping("/account/update")
    public ResponseEntity<ResponseDTO> updateAccount(@Valid @RequestBody AccountDTO accountDTO) throws DataNotFoundException, UpdateDataFailException, ParseException {
        ResponseDTO responseDTO = new ResponseDTO();
        Account account = accountConverter.convertToEntity(accountDTO);
        accountService.updateAccount(account);
        responseDTO.setData(accountConverter.convertToDto(account));
        responseDTO.setSuccessCode(SuccessCode.SUCCESS_USER_UPDATED);
        return ResponseEntity.ok().body(responseDTO);
    }
}
