package com.accountstatement.controller;

import com.accountstatement.service.AccountStatementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/accountStatement")
public class AccountStatementController {
private final AccountStatementService accountStatementService;

    @GetMapping("/download")
    public void getAccountStatement(@RequestParam("email") String email,
                                      @RequestParam("startDate") String startDate,
                                      @RequestParam("endDate") String endDate) {
        byte[] accountStatement = accountStatementService.getAccountStatement(email, startDate, endDate);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "account_statement.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(accountStatement);
    }
}
