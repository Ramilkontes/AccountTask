package com.local.accounttask.security.details;

import com.local.accounttask.exeptions.ClientNotFoundException;
import com.local.accounttask.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClientDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClientsRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return new UserDetailsImpl(repository.findOneByClientLogin(login).orElseThrow(ClientNotFoundException::new));
    }
}
