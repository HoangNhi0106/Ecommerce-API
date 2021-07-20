package com.nashtech.ecommerceapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
public class AccountDTO {
    private long accountId;

    private String firstname;

    private String lastname;

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @Email
    private String email;

    private String phone;

    private LocalDateTime createdIn;

    private LocalDateTime updatedIn;
}
