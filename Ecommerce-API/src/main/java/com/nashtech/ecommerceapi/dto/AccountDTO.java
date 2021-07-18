package com.nashtech.ecommerceapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AccountDTO {
    private long accountId;

    private String firstname;

    private String lastname;

    private String username;

    private String email;

    private String phone;

    private LocalDateTime createdIn;

    private LocalDateTime updatedIn;
}
