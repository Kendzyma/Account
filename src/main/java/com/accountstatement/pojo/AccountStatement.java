package com.accountstatement.pojo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountStatement {
    private String userEmail;
    private LocalDate transactionDate;
    private LocalDate amount;
}