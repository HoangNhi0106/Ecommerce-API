package com.nashtech.ecommerceapi.service.impl;

import com.nashtech.ecommerceapi.constant.ErrorCode;
import com.nashtech.ecommerceapi.entity.Account;
import com.nashtech.ecommerceapi.entity.Rating;
import com.nashtech.ecommerceapi.exception.DataNotFoundException;
import com.nashtech.ecommerceapi.exception.DeleteDataFailException;
import com.nashtech.ecommerceapi.exception.UpdateDataFailException;
import com.nashtech.ecommerceapi.exception.UserAuthenticationException;
import com.nashtech.ecommerceapi.facade.IAuthenticationFacade;
import com.nashtech.ecommerceapi.repository.AccountRepository;
import com.nashtech.ecommerceapi.service.AccountService;
import com.nashtech.ecommerceapi.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).get();
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
            account.setRoles(currentAccount.getRoles());
            account.setPassword(currentAccount.getPassword());
            account.setCreatedIn(currentAccount.getCreatedIn());
            accountRepository.save(account);
        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERROR_USER_NOT_UPDATED);
        }

    }


    public void deleteAccount(Long id) throws DeleteDataFailException {
        try {
            Account account =getAccountById(id);
            List<Rating> ratings = ratingService.getRatingByAccount(account);
            for (Rating rating : ratings)
                ratingService.deleteRating(rating.getRatingId());
            accountRepository.deleteById(id);
        } catch (Exception e) {
            throw new DeleteDataFailException(ErrorCode.ERROR_USER_NOT_DELETED);
        }
    }
}
