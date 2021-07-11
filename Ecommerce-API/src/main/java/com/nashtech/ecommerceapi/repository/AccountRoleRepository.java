package com.nashtech.ecommerceapi.repository;

import com.nashtech.ecommerceapi.entity.Account;
import com.nashtech.ecommerceapi.entity.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRoleRepository extends JpaRepository<AccountRole, Long> {
    List<AccountRole> findAllByAccount(Account account);
}
