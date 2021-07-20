package com.nashtech.ecommerceapi.service;

import com.nashtech.ecommerceapi.entity.Account;
import com.nashtech.ecommerceapi.exception.DataNotFoundException;
import com.nashtech.ecommerceapi.exception.DeleteDataFailException;
import com.nashtech.ecommerceapi.exception.UpdateDataFailException;

public interface AccountService {
    public Account getAccountById(Long id);

    public String getCurrentUsername();

    public Account getAccountByUsername(String username) throws DataNotFoundException;

    public void updateAccount(Account account) throws DataNotFoundException, UpdateDataFailException;

    public void deleteAccount(Long id) throws DeleteDataFailException;
}
