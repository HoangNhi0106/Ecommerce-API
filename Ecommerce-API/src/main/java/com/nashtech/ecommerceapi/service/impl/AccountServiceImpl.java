package com.nashtech.ecommerceapi.service.impl;

import com.nashtech.ecommerceapi.constant.ErrorCode;
import com.nashtech.ecommerceapi.entity.Account;
import com.nashtech.ecommerceapi.facade.IAuthenticationFacade;
import com.nashtech.ecommerceapi.repository.AccountRepository;
import com.nashtech.ecommerceapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).get();
    }

    public String getCurrentUsername() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getName();
    }

    public Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username).get();
    }

    public boolean updateAccount(Account account) {
        Account currentAccount = getAccountByUsername(getCurrentUsername());
        if (!account.getUsername().equals(currentAccount.getUsername()))
            return false;

        account.setAccountId(currentAccount.getAccountId());
        account.setCreatedIn(currentAccount.getCreatedIn());
        account.setPassword(currentAccount.getPassword());
        account.setRoles(currentAccount.getRoles());

        if (account.getFirstname() == null) account.setFirstname(currentAccount.getFirstname());
        if (account.getLastname() == null) account.setLastname(currentAccount.getLastname());
        if (account.getEmail() == null) account.setEmail(currentAccount.getEmail());
        if (account.getPhone() == null) account.setPhone(currentAccount.getPhone());

        account.setUpdatedIn(LocalDateTime.now());
        accountRepository.save(account);
        return true;
    }


    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}
