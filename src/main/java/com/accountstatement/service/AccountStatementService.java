package com.accountstatement.service;

public interface AccountStatementService {
    void getAccountStatement(String email, String startDate, String endDate);
}
