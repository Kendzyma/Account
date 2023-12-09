package com.accountstatement.utils;

import com.accountstatement.pojo.AccountStatement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileUtils {
    @Value("${csv.file.path}")
    private String csvFilePath;
    private final DateConverter dateConverter;

    public List<AccountStatement> readCsvFile() {
        try (InputStream inputStream = new ClassPathResource(csvFilePath).getInputStream();
             Reader reader = new InputStreamReader(inputStream);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {
            List<AccountStatement> csvRecords = new ArrayList<>();
            // Accessing the records from the CSV file
            for (CSVRecord record : csvParser) {
                AccountStatement accountStatement = new AccountStatement();
                accountStatement.setUserEmail(record.get("user_email"));
                accountStatement.setTransactionDate(dateConverter.convertToDate(record.get("date_of_transaction")));
                accountStatement.setAmount(dateConverter.convertToDate(record.get("amount")));

                csvRecords.add(accountStatement);
            }

            return csvRecords;
        } catch (Exception e) {
            log.error("Error while reading csv file", e);
            throw new RuntimeException("Error while reading csv file", e);
        }
    }
}

