package com.banking_application.banking_app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking_application.banking_app.Entity.Account;

public interface AccountRepo extends JpaRepository<Account,Long>{

    

    
}
