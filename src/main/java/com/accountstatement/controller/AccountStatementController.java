package com.accountstatement.controller;

import com.accountstatement.service.AccountStatementService;
import lombok.RequiredArgsConstructor;
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
        accountStatementService.getAccountStatement(email,startDate,endDate);
    }
}
