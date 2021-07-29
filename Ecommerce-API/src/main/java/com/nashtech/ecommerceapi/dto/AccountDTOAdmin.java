package com.nashtech.ecommerceapi.dto;

import com.nashtech.ecommerceapi.entity.Role;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class AccountDTOAdmin {
    private long accountId;

    private String firstname;

    private String lastname;

    @Size(min = 3, max = 20)
    private String username;

    @Email
    private String email;

    @NotNull
    private String phone;

    private Set<Role> roles;

    private LocalDateTime createdIn;

    private LocalDateTime updatedIn;
}
