package com.local.accounttask.repositories;

import com.local.accounttask.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientsRepository extends JpaRepository<Client, Long> {
    Optional<Client> findOneByClientLogin(String login);
}
