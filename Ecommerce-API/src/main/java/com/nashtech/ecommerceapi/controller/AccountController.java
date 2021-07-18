package com.nashtech.ecommerceapi.controller;

import com.nashtech.ecommerceapi.constant.ErrorCode;
import com.nashtech.ecommerceapi.constant.SuccessCode;
import com.nashtech.ecommerceapi.converter.AccountConverter;
import com.nashtech.ecommerceapi.dto.ResponseDTO;
import com.nashtech.ecommerceapi.entity.Account;
import com.nashtech.ecommerceapi.entity.Category;
import com.nashtech.ecommerceapi.exception.CategoryException;
import com.nashtech.ecommerceapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountConverter accountConverter;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
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
}
