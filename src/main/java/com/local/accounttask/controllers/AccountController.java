package com.local.accounttask.controllers;

import com.local.accounttask.dto.ResponseAccount;
import com.local.accounttask.dto.UpdateAccount;
import com.local.accounttask.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService service;

    @PutMapping("/{id}")
    public ResponseAccount paymentAccount(@PathVariable Long id, @RequestBody UpdateAccount updateAccount) {
        return service.addBalance(updateAccount.getBalance(), id);
    }

    @GetMapping("/{id}")
    public ResponseAccount findAccountById(@PathVariable Long id) {
        return service.findAccountById(id);
    }
}
