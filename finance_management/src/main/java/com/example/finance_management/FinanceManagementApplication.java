package com.example.finance_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinanceManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(FinanceManagementApplication.class, args);
        System.out.println("Finance Management Application started on port 8080");

    }
}
