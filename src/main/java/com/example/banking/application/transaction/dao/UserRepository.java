package com.example.banking.application.transaction.dao;

import com.example.banking.application.transaction.entity.User;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Id> {
     User findById(Integer userId);
}
