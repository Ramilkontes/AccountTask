package com.local.accounttask.mappers;

import com.local.accounttask.dto.CreateAccountRequest;
import com.local.accounttask.dto.ResponseAccount;
import com.local.accounttask.models.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public Account toEntity(CreateAccountRequest request) {
        Account entity = new Account();
        entity.setBalance(request.getBalance());
        return entity;
    }

    public ResponseAccount toDto(Account temp) {
        return new ResponseAccount(temp.getId(), temp.getBalance());
    }

    /*public Account update (UpdateAccount updateAccount, Account account){
        account.setBalance(updateAccount.getBalance());
        return account;
    }*/
}
