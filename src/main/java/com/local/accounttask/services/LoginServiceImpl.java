package com.local.accounttask.services;

import com.local.accounttask.dto.CreateLoginRequest;
import com.local.accounttask.dto.TokenDto;
import com.local.accounttask.exeptions.ClientNotFoundException;
import com.local.accounttask.models.Client;
import com.local.accounttask.models.Token;
import com.local.accounttask.repositories.ClientsRepository;
import com.local.accounttask.repositories.TokensRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.local.accounttask.dto.TokenDto.from;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private TokensRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClientsRepository clientRepository;

    @Override
    public TokenDto login(CreateLoginRequest loginRequest) {
        Optional<Client> clientCandidate = clientRepository.findOneByClientLogin(loginRequest.getLogin());
        if (clientCandidate.isPresent()) {
            Client client = clientCandidate.get();

            if (passwordEncoder.matches(loginRequest.getPassword(), client.getHashPassword())) {
                Token token = Token.builder()
                        .client(client)
                        .value(RandomStringUtils.random(10, true, true))
                        .build();
                tokenRepository.save(token);
                return from(token);
            }
        }
        throw new ClientNotFoundException("Клиент не найден");
    }
}
