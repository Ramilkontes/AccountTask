package com.local.accounttask.mappers;

import com.local.accounttask.dto.CreateClientRequest;
import com.local.accounttask.dto.ResponseAccount;
import com.local.accounttask.dto.ResponseClient;
import com.local.accounttask.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ClientMapper {

    @Autowired
    private AccountMapper accountMapper;

    public Client toEntity(CreateClientRequest request) {
        Client entity = new Client();
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setClientLogin(request.getClientLogin());
        entity.setHashPassword(request.getPassword());
        return entity;
    }

    public ResponseClient toDto(Client temp) {
        List<ResponseAccount> responseAccounts = Optional.ofNullable(temp.getAccounts())
                .orElseGet(Collections::emptyList)
                .stream()
                .map(account -> accountMapper.toDto(account))
                .collect(Collectors.toList());
        return new ResponseClient(temp.getId(), temp.getFirstName(), temp.getLastName(),
                temp.getClientLogin(), responseAccounts.size(), responseAccounts);
    }

//    public Client update(Client updateClient, UpdateClient request) {
//        updateClient.setClientLogin(request.getClientLogin());
//        updateClient.getAccounts().add(request.getAccount());
//        return updateClient;
//    }
}
