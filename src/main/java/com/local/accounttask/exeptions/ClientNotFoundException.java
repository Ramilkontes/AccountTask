package com.local.accounttask.exeptions;

import com.local.accounttask.models.Client;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(String msg) {
        super(msg);
    }
}
