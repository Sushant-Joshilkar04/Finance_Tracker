package com.example.finance_management.services;

import com.example.finance_management.entities.Budget;
import com.example.finance_management.repository.BudgetRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository repo;

    public List<Budget> getAll(Optional<String> month) {
        if (month.isPresent()) {
            return repo.findAll()
                      .stream()
                      .filter(b -> month.get().equals(b.getMonth()))
                      .sorted((a, b) -> a.getCategory().compareToIgnoreCase(b.getCategory()))
                      .toList();
        }
        return repo.findAll()
                   .stream()
                   .sorted((a, b) -> a.getCategory().compareToIgnoreCase(b.getCategory()))
                   .toList();
    }

    public Budget create(Budget budget) {
        // enforce uniqueness per category+month
        if (repo.findByCategoryAndMonth(budget.getCategory(), budget.getMonth()).isPresent()) {
            throw new DuplicateKeyException("Budget already exists for this category and month");
        }
        return repo.save(budget);
    }

    public Budget update(String id, Budget budget) {
        Budget existing = repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Budget not found with id: " + id));
        
        // log to console
        System.out.println("Updating budget with ID: " + id);
        
        existing.setAmount(budget.getAmount());
        existing.setCategory(budget.getCategory());
        existing.setMonth(budget.getMonth());
        return repo.save(existing);
    }

    public void delete(String id) {
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("Budget not found");
        }
        repo.deleteById(id);
    }
}
