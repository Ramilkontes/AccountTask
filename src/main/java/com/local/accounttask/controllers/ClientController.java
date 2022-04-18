package com.local.accounttask.controllers;


import com.local.accounttask.dto.CreateAccountRequest;
import com.local.accounttask.dto.CreateClientRequest;
import com.local.accounttask.dto.ResponseClient;
import com.local.accounttask.mappers.AccountMapper;
import com.local.accounttask.mappers.ClientMapper;
import com.local.accounttask.services.SignUpService;
import com.local.accounttask.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private ClientService service;

    @Autowired
    private ClientMapper mapper;

    @Autowired
    private AccountMapper accountMapper;

    @PostMapping
    public ResponseClient addClient (@RequestBody CreateClientRequest createClientRequest){
        return  signUpService.signUp(createClientRequest);
    }

    @PutMapping("/{id}")
    public ResponseClient addNewAccount(@RequestBody CreateAccountRequest request, @PathVariable Long id) {
        return service.createNewAccount(accountMapper.toEntity(request).getBalance(), id);
    }

    @GetMapping("/{id}")
    public ResponseClient findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public ResponseClient findByLogin(@RequestParam String login){
        return  service.findByLogin(login);
    }

    @GetMapping("all")
    public List<ResponseClient> findAll(){
        return service.findAll();
    }
}
