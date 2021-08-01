package com.nashtech.ecommerceapi.service.impl;

import com.nashtech.ecommerceapi.constant.ERole;
import com.nashtech.ecommerceapi.constant.ErrorCode;
import com.nashtech.ecommerceapi.entity.Account;
import com.nashtech.ecommerceapi.entity.Rating;
import com.nashtech.ecommerceapi.entity.Role;
import com.nashtech.ecommerceapi.exception.DataNotFoundException;
import com.nashtech.ecommerceapi.exception.DeleteDataFailException;
import com.nashtech.ecommerceapi.exception.UpdateDataFailException;
import com.nashtech.ecommerceapi.exception.UserAuthenticationException;
import com.nashtech.ecommerceapi.facade.IAuthenticationFacade;
import com.nashtech.ecommerceapi.repository.AccountRepository;
import com.nashtech.ecommerceapi.repository.RoleRepository;
import com.nashtech.ecommerceapi.service.AccountService;
import com.nashtech.ecommerceapi.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    private RoleRepository roleRepository;

    public List<Account> getAllAccount() {
        List<Account> accounts = accountRepository.findAll();
        if (accounts.isEmpty())
            return null;
        else return accounts;
    }

    public Account getAccountById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent())
            return account.get();
        else return null;
    }

    public String getCurrentUsername() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getName();
    }

    public Account getAccountByUsername(String username) throws DataNotFoundException {
        Optional<Account> opAccount = accountRepository.findByUsername(username);
        if (!opAccount.isPresent()){
            throw new DataNotFoundException(ErrorCode.ERROR_USER_NOT_FOUND);
        }
        return opAccount.get();
    }

    public void updateAccount(Account account) throws UpdateDataFailException {
        try{
            Account currentAccount = getAccountByUsername(getCurrentUsername());
            if (!account.getUsername().equals(currentAccount.getUsername()))
                throw new UserAuthenticationException(ErrorCode.ERROR_USER_AUTHENTICATION);
            account.setAccountId(currentAccount.getAccountId());
            account.setUpdatedIn(LocalDateTime.now());
            account.setPassword(currentAccount.getPassword());
            account.setCreatedIn(currentAccount.getCreatedIn());
            if (account.getLastname() == null) account.setLastname(currentAccount.getLastname());
            if (account.getFirstname() == null) account.setFirstname(currentAccount.getFirstname());
            if (account.getPhone() == null) account.setPhone(currentAccount.getPhone());
            account.setRoles(currentAccount.getRoles());
            accountRepository.save(account);
        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERROR_USER_NOT_UPDATED);
        }
    }

    public void updateAccountRoles(Account account) throws UpdateDataFailException {
        try{
            Account currentAccount = getAccountById(account.getAccountId());
            account.setUpdatedIn(LocalDateTime.now());
            account.setPassword(currentAccount.getPassword());
            account.setCreatedIn(currentAccount.getCreatedIn());
            account.setLastname(currentAccount.getLastname());
            account.setFirstname(currentAccount.getFirstname());
            account.setPhone(currentAccount.getPhone());
            account.setUsername(currentAccount.getUsername());
            account.setEmail(currentAccount.getEmail());
            accountRepository.save(account);
        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERROR_USER_NOT_UPDATED);
        }
    }

    public void deleteAccount(Long id) throws DeleteDataFailException {
        try {
            Account account =getAccountById(id);
            List<Rating> ratings = ratingService.getRatingByAccount(account);
            if (ratings != null)
                for (Rating rating : ratings)
                    ratingService.deleteRating(rating.getRatingId());
            accountRepository.deleteById(id);
        } catch (Exception e) {
            throw new DeleteDataFailException(ErrorCode.ERROR_USER_NOT_DELETED);
        }
    }

    public List<Account> getByNameContainting(String name) {
        List<Account> accounts = accountRepository.findAccountByUsernameContainingIgnoreCase(name);
        if (accounts.isEmpty())
            return null;
        else return accounts;
    }
}
