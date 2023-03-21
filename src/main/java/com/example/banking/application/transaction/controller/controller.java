package com.example.banking.application.transaction.controller;

import com.example.banking.application.transaction.entity.Amount;
import com.example.banking.application.transaction.entity.User;
import com.example.banking.application.transaction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class controller {

    @Autowired
    UserService userService;
    @PostMapping("/createUser")
    public void createUser(@RequestBody User user){
        userService.createUser(user);
    }

    @PostMapping("debit/{userId}")
    public ResponseEntity<String> debitBalance(@RequestBody Amount amount, @PathVariable("userId") int userId){
        float val= userService.debitAccount(userId,amount.getAmount());
        if(val<0){
             return ResponseEntity.badRequest().body("Balance is getting Negative");
        }
        return ResponseEntity.ok().body("Updated Balance"+ val);
    }

    @PostMapping("credit/{userId}")
    public ResponseEntity<String> creditBalance(@RequestBody Amount amount, @PathVariable("userId") int userId){
        float val= userService.debitAccount(userId,amount.getAmount());
        if(val<0){
            return ResponseEntity.badRequest().body("Balance is exceeding 10 Million");
        }
        return ResponseEntity.ok().body("Updated Balance"+ val);
    }
}
