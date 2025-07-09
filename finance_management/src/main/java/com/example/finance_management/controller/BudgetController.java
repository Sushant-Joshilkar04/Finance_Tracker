package com.example.finance_management.controller;

import com.example.finance_management.entities.Budget;
import com.example.finance_management.services.BudgetService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/budgets")
@Validated
public class BudgetController {

    @Autowired
    private BudgetService service;

    // Test endpoint
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Budget Controller is working!");
    }

    @GetMapping
    public ResponseEntity<List<Budget>> getBudgets(@RequestParam(required = false) String month) {
        System.out.println("GET /api/budgets called with month: " + month);
        List<Budget> budgets = service.getAll(Optional.ofNullable(month));
        return ResponseEntity.ok(budgets);
    }

    @PostMapping
    public ResponseEntity<Budget> createBudget(@Valid @RequestBody Budget b) {
        System.out.println("POST /api/budgets called with: " + b);
        try {
            Budget saved = service.create(b);
            return ResponseEntity.status(201).body(saved);
        } catch (DuplicateKeyException ex) {
            throw new ResponseStatusException(
                org.springframework.http.HttpStatus.CONFLICT,
                "Budget for given category and month already exists", ex
            );
        }
    }

    @PutMapping
    public ResponseEntity<Budget> updateBudget(@Valid @RequestBody Budget b) {
        System.out.println("PUT /api/budgets called with: " + b);
        try {
            Budget updated = service.update(b.getId(), b);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(
                org.springframework.http.HttpStatus.NOT_FOUND,
                "Budget not found with ID: " + b.getId(), ex
            );
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBudget(@RequestParam String id) {
        System.out.println("DELETE /api/budgets called with id: " + id);
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(
                org.springframework.http.HttpStatus.NOT_FOUND,
                "Budget not found with ID: " + id, ex
            );
        }
    }
}
