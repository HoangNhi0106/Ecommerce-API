package com.nashtech.ecommerceapi.service;

import com.nashtech.ecommerceapi.entity.Account;
import com.nashtech.ecommerceapi.entity.Category;
import com.nashtech.ecommerceapi.exception.DataNotFoundException;
import com.nashtech.ecommerceapi.exception.DeleteDataFailException;
import com.nashtech.ecommerceapi.exception.UpdateDataFailException;

import java.util.List;

public interface AccountService {
    public List<Account> getAllAccount();

    public Account getAccountById(Long id);

    public String getCurrentUsername();

    public Account getAccountByUsername(String username) throws DataNotFoundException;

    public void updateAccount(Account account) throws DataNotFoundException, UpdateDataFailException;

    public void updateAccountRoles(Account account) throws DataNotFoundException, UpdateDataFailException;

    public void deleteAccount(Long id) throws DeleteDataFailException;

    public List<Account> getByNameContainting(String name);
}
