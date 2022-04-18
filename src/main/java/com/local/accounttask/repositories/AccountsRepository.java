package com.local.accounttask.repositories;

import com.local.accounttask.models.Account;
import com.local.accounttask.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Account, Long> {
}
