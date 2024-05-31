package com.rajarshicode.bankingApp.repository;

import com.rajarshicode.bankingApp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {




}
