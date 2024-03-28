package com.banking_application.banking_app.Service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking_application.banking_app.Entity.Account;
import com.banking_application.banking_app.Repository.AccountRepo;

@Service
public class AccountService {
    @Autowired
    private AccountRepo accountRepo;

    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public Account createAccount(Account account){
     return accountRepo.save(account);
    }
    public Account getAccountDetails(Long id){
        Optional<Account> account = accountRepo.findById(id);
        if (account.isEmpty()) {
            throw new RuntimeException("Account doesn't Exist");
            
        }else{
            return account.get();
        }
       }

       public List<Account> getAllAccounts(){
       List< Account> accounts = accountRepo.findAll();
       return accounts;
       }

       public Account depositAmount(Long id , Double amount){
        Optional<Account> isAccount = accountRepo.findById(id);
        if (isAccount.isEmpty()) {
            throw new RuntimeException("Account not found");
        }
        
           Account current= isAccount.get();
         Double depositedAmt= current.getAccount_balance()+amount;
          current.setAccount_balance(depositedAmt);
          accountRepo.save(current);
          return current;
        
       }
       public Account withdrawAmount(Long id , Double amount){
        Optional<Account> isAccount = accountRepo.findById(id);
        if (isAccount.isEmpty()) {
            throw new RuntimeException("Account not found");
        }
        
           Account current= isAccount.get();
         Double depositedAmt= current.getAccount_balance()-amount;
          current.setAccount_balance(depositedAmt);
          accountRepo.save(current);
          return current;
        
       }
       public void closeAccount(Long id){
        Optional<Account> account = accountRepo.findById(id);
        if (account.isPresent()) {
            accountRepo.deleteById(id);
        }else{
            throw new RuntimeException("No Account Found");
        }
        
       }
    
}
