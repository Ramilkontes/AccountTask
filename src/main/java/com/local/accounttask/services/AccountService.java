package com.local.accounttask.services;

import com.local.accounttask.dto.ResponseAccount;
import com.local.accounttask.exeptions.AccountNotFoundException;
import com.local.accounttask.mappers.AccountMapper;
import com.local.accounttask.models.Account;
import com.local.accounttask.models.Client;
import com.local.accounttask.repositories.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService {

    @Autowired
    private AccountsRepository repository;

    @Autowired
    private AccountMapper accountMapper;

    public ResponseAccount addToAccount(BigDecimal balanceAdd, Long accountId) {
        Client foundClient = repository.getById(accountId).getClient();
        Account account = foundClient.getAccounts().stream()
                .filter(detectedAccount -> detectedAccount.getId().equals(accountId))
                .findAny().orElseThrow(() -> new AccountNotFoundException("Счет не найден"));
        BigDecimal addFacilities = account.getBalance().add(balanceAdd);
        account.setBalance(addFacilities);
        return accountMapper.toDto(repository.save(account));
    }

    public ResponseAccount addBalance(BigDecimal balance, Long accountId) {
        if (balance.compareTo(BigDecimal.ZERO) > 0) {
            return addToAccount(balance, accountId);
        } else throw new IllegalArgumentException("bad request");
    }

    public ResponseAccount findAccountById(Long id) {
        return accountMapper.toDto(repository.findById(id).orElseThrow(() ->
                new AccountNotFoundException("Счет не найден")));
    }
}
