package com.rajarshicode.bankingApp.service.impl;

import com.rajarshicode.bankingApp.dto.AccountDto;
import com.rajarshicode.bankingApp.entity.Account;
import com.rajarshicode.bankingApp.mapper.AccountMapper;
import com.rajarshicode.bankingApp.repository.AccountRepository;
import com.rajarshicode.bankingApp.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }



    @Override
    public AccountDto createAccount(AccountDto accountDto) {

        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long Id) {
        Account account =
                accountRepository.findById(Id).orElseThrow(()-> new RuntimeException
                        ("Account does not exists!"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long Id, double amount) {
        Account account =
                accountRepository.findById(Id).orElseThrow(()-> new RuntimeException
                        ("Account does not exists!"));

        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long Id, double amount) {
        Account account =
                accountRepository.findById(Id).orElseThrow(()-> new RuntimeException
                        ("Account does not exists!"));
        if(account.getBalance() < amount){
            throw new RuntimeException("Insufficient Balance");
        }
        double total = account.getBalance() - amount;

        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
       return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
        .collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long Id) {
        Account account =
                accountRepository.findById(Id).orElseThrow(()-> new RuntimeException
                        ("Account does not exists!"));

        accountRepository.deleteById(Id);
    }
}
