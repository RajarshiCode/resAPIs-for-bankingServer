package com.rajarshicode.bankingApp.service;

import com.rajarshicode.bankingApp.dto.AccountDto;

import java.util.List;


public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long Id);

    AccountDto deposit(Long Id, double amount);

    AccountDto withdraw(Long Id, double amount);

    List<AccountDto> getAllAccounts();

    void deleteAccount(Long Id);



}
