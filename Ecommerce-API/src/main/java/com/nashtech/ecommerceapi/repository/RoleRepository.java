package com.nashtech.ecommerceapi.repository;

import com.nashtech.ecommerceapi.constant.ERole;
import com.nashtech.ecommerceapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRname(ERole rname);
}
