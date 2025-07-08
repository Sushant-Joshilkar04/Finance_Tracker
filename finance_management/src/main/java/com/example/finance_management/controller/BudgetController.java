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
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class BudgetController {

    @Autowired
    private BudgetService service;

    // GET budgets (optionally filtered by month)
    @GetMapping
    public List<Budget> getBudgets(@RequestParam(required = false) String month) {
        return service.getAll(Optional.ofNullable(month));
    }

    // POST: Create new budget
    @PostMapping
    public ResponseEntity<Budget> createBudget(@Valid @RequestBody Budget b) {
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

    // PUT: Update budget by ID
    @PutMapping("/{id}")
    public ResponseEntity<Budget> updateBudget(@PathVariable String id,
                                               @Valid @RequestBody Budget b) {
        try {
            Budget updated = service.update(id, b);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(
                org.springframework.http.HttpStatus.NOT_FOUND,
                "Budget not found with ID: " + id, ex
            );
        }
    }

    // DELETE: Delete budget by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable String id) {
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
