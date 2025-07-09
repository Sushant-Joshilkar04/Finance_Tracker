package com.example.finance_management.controller;

import com.example.finance_management.entities.Transaction;
import com.example.finance_management.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Transaction Controller is working!");
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAll() {
        System.out.println("GET /api/transactions called");
        List<Transaction> transactions = service.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @PostMapping
    public ResponseEntity<Transaction> create(@Valid @RequestBody Transaction t) {
        System.out.println("POST /api/transactions called with: " + t);
        Transaction saved = service.addTransaction(t);
        return ResponseEntity.status(201).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> update(@PathVariable String id,
                                              @Valid @RequestBody Transaction t) {
        System.out.println("PUT /api/transactions/" + id + " called with: " + t);
        try {
            Transaction updated = service.updateTransaction(id, t);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        System.out.println("DELETE /api/transactions/" + id + " called");
        try {
            service.deleteTransaction(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(404).build();
        }
    }
}