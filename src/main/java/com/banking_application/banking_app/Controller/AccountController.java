package com.banking_application.banking_app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking_application.banking_app.Entity.Account;
import com.banking_application.banking_app.Service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService service;    
    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
      Account createAccount =  service.createAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(createAccount);
    }
    @GetMapping("{id}")
    public ResponseEntity<Account> getAccountDetails(@PathVariable Long id){
      Account accountFound = service.getAccountDetails(id);
      return ResponseEntity.status(HttpStatus.FOUND).body(accountFound);
    }
    @GetMapping("/view")
    public List <Account> getAllAccountDetails(){
      List<Account> accounts = service.getAllAccounts();
      return accounts;
    }
    @PutMapping("/deposit/{id}/{amount}")
    public ResponseEntity<Account> deposit( @PathVariable Long id, @PathVariable Double amount){
        Account account = service.depositAmount(id, amount);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(account);
    }
    @PutMapping("/withdraw/{id}/{amount}")
    public ResponseEntity<Account> withdraw( @PathVariable Long id, @PathVariable Double amount){
        Account account = service.withdrawAmount(id, amount);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(account);
    }
    @DeleteMapping("close/{id}")
    public ResponseEntity closeAccount(@PathVariable Long id){
        service.closeAccount(id);
        return ResponseEntity.ok("Account Closed");
    }
    
}
