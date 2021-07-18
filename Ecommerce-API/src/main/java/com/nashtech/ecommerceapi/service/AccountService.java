package com.nashtech.ecommerceapi.service;

import com.nashtech.ecommerceapi.entity.Account;

public interface AccountService {
    public Account getAccountById(Long id);

    public String getCurrentUsername();

    public Account getAccountByUsername(String username);

    public boolean updateAccount(Account account);

    public void deleteAccount(Long id);
}
