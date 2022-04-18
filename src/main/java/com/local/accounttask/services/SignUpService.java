package com.local.accounttask.services;

import com.local.accounttask.dto.CreateClientRequest;
import com.local.accounttask.dto.ResponseClient;
import com.local.accounttask.mappers.ClientMapper;
import com.local.accounttask.models.Client;
import com.local.accounttask.models.Role;
import com.local.accounttask.models.State;
import com.local.accounttask.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    @Autowired
    private ClientsRepository repository;

    @Autowired
    private ClientMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public ResponseClient signUp(CreateClientRequest createClientRequest) {
        String hashPassword = passwordEncoder.encode(createClientRequest.getPassword());

        Client client = Client.builder()
                .firstName(createClientRequest.getFirstName())
                .lastName(createClientRequest.getLastName())
                .clientLogin(createClientRequest.getClientLogin())
                .numberOfAccount(0)
                .hashPassword(hashPassword)
                .role(Role.USER)
                .state(State.ACTIVE)
                .build();
        repository.save(client);
        return mapper.toDto(client);
    }
}