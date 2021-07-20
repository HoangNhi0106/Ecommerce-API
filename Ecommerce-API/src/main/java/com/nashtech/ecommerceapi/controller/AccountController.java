package com.nashtech.ecommerceapi.controller;

import com.nashtech.ecommerceapi.constant.ErrorCode;
import com.nashtech.ecommerceapi.constant.SuccessCode;
import com.nashtech.ecommerceapi.converter.AccountConverter;
import com.nashtech.ecommerceapi.dto.AccountDTO;
import com.nashtech.ecommerceapi.dto.ResponseDTO;
import com.nashtech.ecommerceapi.entity.Account;
import com.nashtech.ecommerceapi.entity.Rating;
import com.nashtech.ecommerceapi.exception.ProductException;
import com.nashtech.ecommerceapi.service.AccountService;
import com.nashtech.ecommerceapi.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountConverter accountConverter;

    @Autowired
    private RatingService ratingService;

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<ResponseDTO> findAccountById(@PathVariable Long id) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Account account = accountService.getAccountById(id);
            if (account != null) {
                responseDTO.setData(accountConverter.convertToDto(account));
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_LOAD_USER);
            }
        } catch (Exception exception) {
            responseDTO.setErrorCode(ErrorCode.ERROR_LOAD_USER);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO> updateAccount(@Valid @RequestBody AccountDTO accountDTO) throws AccountException {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Account account = accountConverter.convertToEntity(accountDTO);
            boolean success = accountService.updateAccount(account);
            if (success) {
                responseDTO.setData(accountConverter.convertToDto(account));
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_UPDATE_USER);
            } else {
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERROR_UPDATE_USER);
            }
        } catch (Exception exception) {
            responseDTO.setErrorCode(ErrorCode.ERROR_UPDATE_USER);
            throw new AccountException(ErrorCode.ERROR_UPDATE_USER);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping(value = "delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseDTO> deleteProduct(@PathVariable Long id) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Account account = accountService.getAccountById(id);
            if (account != null) {
                List<Rating> ratings = ratingService.getRatingByAccount(account);
                for (Rating rating : ratings)
                    ratingService.deleteRating(rating.getRatingId());

                accountService.deleteAccount(id);
                responseDTO.setData(true);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_LOAD_USER);
            }
        } catch (Exception exception) {
            responseDTO.setData(false);
            responseDTO.setErrorCode(ErrorCode.ERROR_LOAD_USER);
            throw new ProductException(ErrorCode.ERROR_LOAD_USER);
        }
        return ResponseEntity.ok().body(responseDTO);
    }
}
