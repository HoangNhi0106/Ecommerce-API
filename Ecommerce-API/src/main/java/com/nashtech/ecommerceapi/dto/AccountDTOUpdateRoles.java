package com.nashtech.ecommerceapi.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class AccountDTOUpdateRoles {
    @NotNull
    private long accountId;

    @NotNull
    private Set<String> roles;
}
