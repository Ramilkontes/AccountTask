package com.local.accounttask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseClient {
    private Long id;
    private String firstName;
    private String lastName;
    private String accountLogin;
    private int numberOfAccount;
    private List<ResponseAccount> accounts = new ArrayList<>();
}
