package com.example.finance_management.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;


@Document(collection = "budgets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Budget {
    @Id
    private String id;

    @NotBlank(message = "Category is required")
    @Pattern(regexp = "Food|Transport|Shopping|Health|Utilities|Other",
             message = "Invalid category")
    private String category;

    @Min(value = 1, message = "Amount must be greater than 0")
    private double amount;

    @NotBlank(message = "Month is required")
    private String month;
}
