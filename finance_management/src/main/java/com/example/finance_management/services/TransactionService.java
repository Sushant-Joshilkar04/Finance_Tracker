package com.example.finance_management.services;


import com.example.finance_management.entities.Transaction;
import com.example.finance_management.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    public List<Transaction> getAllTransactions() {
        return repository.findAll()
                         .stream()
                         .sorted((a, b) -> b.getDate().compareTo(a.getDate()))
                         .toList();
    }

    public Transaction addTransaction(Transaction transaction) {
        transaction.setCreatedAt(new java.util.Date());
        transaction.setUpdatedAt(new java.util.Date());
        return repository.save(transaction);
    }

    public Transaction updateTransaction(String id, Transaction updatedData) {
        Transaction existing = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Transaction not found"));

        existing.setAmount(updatedData.getAmount());
        existing.setDescription(updatedData.getDescription());
        existing.setDate(updatedData.getDate());
        existing.setCategory(updatedData.getCategory());
        existing.setUpdatedAt(new java.util.Date());

        return repository.save(existing);
    }

    public void deleteTransaction(String id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Transaction not found");
        }
        repository.deleteById(id);
    }
}
