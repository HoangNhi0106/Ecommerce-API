package com.nashtech.ecommerceapi.service.impl;

import com.nashtech.ecommerceapi.entity.Account;
import com.nashtech.ecommerceapi.entity.AccountRole;
import com.nashtech.ecommerceapi.repository.AccountRepository;
import com.nashtech.ecommerceapi.repository.AccountRoleRepository;
import com.nashtech.ecommerceapi.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AccountRoleRepository accountRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException(username);
        }
        List<AccountRole> accountRoleList = accountRoleRepository.findAllByAccount(account);

        List<String> roleNames = new ArrayList<>();

        for (AccountRole accountRole : accountRoleList) {
            roleNames.add(accountRole.getRole().getRname());
        }

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }

        UserDetails userDetails = (UserDetails) new User(account.getUsername(),
                account.getPassword(), grantList);

        return userDetails;
    }
}
