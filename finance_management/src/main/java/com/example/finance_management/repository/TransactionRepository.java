package com.example.finance_management.repository;

import com.example.finance_management.entities.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
}
