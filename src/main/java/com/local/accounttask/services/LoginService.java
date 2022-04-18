package com.local.accounttask.services;

import com.local.accounttask.dto.CreateLoginRequest;
import com.local.accounttask.dto.TokenDto;


public interface LoginService {
    TokenDto login(CreateLoginRequest loginRequest);
}
