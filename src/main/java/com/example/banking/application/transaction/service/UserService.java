package com.example.banking.application.transaction.service;

import com.example.banking.application.transaction.dao.AccountRepository;
import com.example.banking.application.transaction.dao.UserRepository;
import com.example.banking.application.transaction.entity.Account;
import com.example.banking.application.transaction.entity.User;
import jakarta.persistence.Access;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Transactional
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;
    public float debitAccount(Integer user_id, float amount){
        User user = userRepository.findById(user_id);
        List<Account> accountList= accountRepository.findAllByAccountNumber(user.getAccount().getAccountNumber());
        float balance= user.getAccount().getBalance();
        balance= balance - amount;

        if(balance<0){
            return -1;
        }
        user.getAccount().setBalance(balance);
        for(Account account: accountList){
            account.setBalance(balance);
            accountRepository.save(account);
        }
        userRepository.save(user);
        return balance;
    }

    public float creditAccount(Integer user_id, float amount){
        User user = userRepository.findById(user_id);
        List<Account> accountList= accountRepository.findAllByAccountNumber(user.getAccount().getAccountNumber());
        float balance= user.getAccount().getBalance();
        balance= balance + amount;

        if(balance>10000000){
            return -1;
        }
        user.getAccount().setBalance(balance);
        for(Account account: accountList){
            account.setBalance(balance);
            accountRepository.save(account);
        }
        userRepository.save(user);
        return balance;
    }
    public void createUser(User user){
        userRepository.save(user);
    }
}
