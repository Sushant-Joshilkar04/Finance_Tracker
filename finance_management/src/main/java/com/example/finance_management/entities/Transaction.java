package com.example.finance_management.entities;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotNull;


import java.util.Date;

@Document(collection = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    @Id
    private String id;

    @Min(value = 1, message = "Amount must be greater than 0")
    private double amount;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Date is required")
    private Date date;

    @NotBlank(message = "Category is required")
    @Pattern(regexp = "Food|Transport|Shopping|Health|Utilities|Other",
             message = "Invalid category")
    private String category;

    private Date createdAt = new Date();
    private Date updatedAt = new Date();
}

