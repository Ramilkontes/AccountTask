package com.local.accounttask.dto;

import com.local.accounttask.models.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateClient {
    private String firstName;
    private String lastName;
    private String clientLogin;
    private Account account;
}
