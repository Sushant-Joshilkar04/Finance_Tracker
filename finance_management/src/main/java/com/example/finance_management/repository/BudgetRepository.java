package com.example.finance_management.repository;

import com.example.finance_management.entities.Budget;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BudgetRepository extends MongoRepository<Budget, String> {
    List<Budget> findByMonth(String month);
    
    Optional<Budget> findByCategoryAndMonth(String category, String month);
}