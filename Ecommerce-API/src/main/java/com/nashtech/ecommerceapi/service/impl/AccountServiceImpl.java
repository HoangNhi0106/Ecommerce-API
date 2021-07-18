package com.nashtech.ecommerceapi.service.impl;

import com.nashtech.ecommerceapi.entity.Account;
import com.nashtech.ecommerceapi.repository.AccountRepository;
import com.nashtech.ecommerceapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).get();
    }
}
