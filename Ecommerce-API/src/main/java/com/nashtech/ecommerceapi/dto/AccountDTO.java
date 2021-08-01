package com.nashtech.ecommerceapi.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AccountDTO {
    @NotNull
    private long accountId;

    private String firstname;

    private String lastname;

    @Size(min = 3, max = 20)
    @NotNull
    private String username;

    @Email
    @NotNull
    private String email;

    private String phone;
}
