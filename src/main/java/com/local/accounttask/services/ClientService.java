package com.local.accounttask.services;

import com.local.accounttask.dto.ResponseClient;
import com.local.accounttask.exeptions.ClientNotFoundException;
import com.local.accounttask.mappers.ClientMapper;
import com.local.accounttask.models.Account;
import com.local.accounttask.models.Client;
import com.local.accounttask.repositories.AccountsRepository;
import com.local.accounttask.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientsRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private AccountsRepository accountRepository;

    public ResponseClient createNewAccount(BigDecimal newAccounts, Long clientId) {
        Client client = clientRepository.getById(clientId);
        Account account = new Account();
        account.setBalance(newAccounts);
        account.setClient(client);
        client.getAccounts().add(account);
        client.setNumberOfAccount(client.getNumberOfAccount());
        accountRepository.save(account);
        clientRepository.save(client);
        return clientMapper.toDto(client);
    }

    public ResponseClient findById(Long id) {
        return clientMapper.toDto(clientRepository.findById(id).orElseThrow(() ->
                new ClientNotFoundException("Аккаунт не найден")));
    }

    public ResponseClient findByLogin(String login) {
        Optional<Client> clientCandidate = clientRepository.findOneByClientLogin(login);
        Client client = clientRepository.findOneByClientLogin(login).orElseThrow(() ->
                new ClientNotFoundException("Аккаунт с таким логином не найден"));
        return clientMapper.toDto(client);
    }

    public List<ResponseClient> findAll() {
        return clientRepository.findAll().stream()
                .map(client -> clientMapper.toDto(client))
                .collect(Collectors.toList());
    }
}
