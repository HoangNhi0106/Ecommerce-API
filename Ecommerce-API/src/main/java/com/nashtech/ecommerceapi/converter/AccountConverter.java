package com.nashtech.ecommerceapi.converter;

import com.nashtech.ecommerceapi.dto.AccountDTO;
import com.nashtech.ecommerceapi.entity.Account;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class AccountConverter {
    @Autowired
    private ModelMapper modelMapper;

    public AccountDTO convertToDto(Account account) {
        AccountDTO accountDTO = modelMapper.map(account, AccountDTO.class);
        accountDTO.setAccountId(account.getAccountId());
        return accountDTO;
    }

    public Account convertToEntity(AccountDTO accountDTO) throws ParseException {
        Account account = modelMapper.map(accountDTO, Account.class);
        return account;
    }
}
