package com.accountstatement.service.impl;

import com.accountstatement.notification_service.NotificationService;
import com.accountstatement.pojo.AccountStatement;
import com.accountstatement.service.AccountStatementService;
import com.accountstatement.utils.DateConverter;
import com.accountstatement.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AccountStatementServiceImpl implements AccountStatementService {
    private final NotificationService notificationService;
    private final FileUtils fileUtils;
    private final DateConverter dateConverter;
    private

    public AccountStatementServiceImpl(@Qualifier("email") NotificationService notificationService, FileUtils fileUtils, DateConverter dateConverter) {
        this.notificationService = notificationService;
        this.fileUtils = fileUtils;
        this.dateConverter = dateConverter;
    }

    @Override
    public byte[] getAccountStatement(String email, String startDate, String endDate) {
        List<AccountStatement> accountStatements = fileUtils.readCsvFile();
        List<AccountStatement> response = accountStatements.stream()
                .filter((accountStatement) -> accountStatement.getTransactionDate().isEqual(dateConverter.convertToDate(startDate)) ||
                        accountStatement.getTransactionDate().isAfter(dateConverter.convertToDate(startDate)) &&
                                accountStatement.getTransactionDate().isEqual(dateConverter.convertToDate(endDate)) ||
                        accountStatement.getTransactionDate().isBefore(dateConverter.convertToDate(endDate))).toList();



    }
}
