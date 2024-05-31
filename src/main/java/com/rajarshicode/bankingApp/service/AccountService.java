package com.rajarshicode.bankingApp.service;

import com.rajarshicode.bankingApp.dto.AccountDto;


public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long Id);

    AccountDto deposit(Long Id, double amount);

}
