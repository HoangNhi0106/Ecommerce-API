package com.nashtech.ecommerceapi.dto;

import com.sun.istack.NotNull;
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

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @Size(min = 3, max = 20)
    @NotNull
    private String username;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String phone;

    private LocalDateTime createdIn;

    private LocalDateTime updatedIn;
}
