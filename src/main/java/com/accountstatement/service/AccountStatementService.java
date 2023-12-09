package com.accountstatement.service;

public interface AccountStatementService {
    byte[] getAccountStatement(String email, String startDate, String endDate);
}
