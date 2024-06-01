package com.rajarshicode.bankingApp.controller;


import com.rajarshicode.bankingApp.dto.AccountDto;
import com.rajarshicode.bankingApp.entity.Account;
import com.rajarshicode.bankingApp.service.AccountService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/accounts")

public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // add ACCOUNT REST API
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){

        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }


    // get account rest api
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }


    // deposit rest api
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id,@RequestBody Map<String, Double> request){
        Double amount = request.get("amount");
        AccountDto accountDto =  accountService.deposit(id,amount);
        return ResponseEntity.ok(accountDto);
    }


    //  withdraw rest api
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,@RequestBody Map<String, Double> request){
        Double amount = request.get("amount");
        AccountDto accountDto =  accountService.withdraw(id,amount);
        return ResponseEntity.ok(accountDto);
    }

    // get all accounts rest api
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
      List<AccountDto> accounts = accountService.getAllAccounts();
      return ResponseEntity.ok(accounts);
    }

    // delete accounts rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount (@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted Successfully!");
    }

}
