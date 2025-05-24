package com.git.amc.rules.api;

import com.git.amc.rules.model.Account;
import com.git.amc.rules.model.Cashflow;
import com.git.amc.rules.model.Customer;
import com.git.amc.rules.service.CashflowService;
import com.git.amc.rules.service.DiscountService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static java.util.Collections.singletonList;

@RequestMapping("/rules")
@RestController
@AllArgsConstructor
public class RulesApi {

    private final DiscountService discountService;
    private final CashflowService cashflowService;

    @GetMapping("/customer/{customer-type}/discount")
    public int getCustomerDiscount(@PathVariable("customer-type") Customer.CustomerType customerType, @RequestParam int years) {
        return discountService.getCustomerDiscount(customerType, years);
    }

    @PostMapping("/accounts/{account-number}/cashflows/overdraft")
    public Account findOverdraft(
            @PathVariable("account-number") int accountNumber, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date,
            @RequestParam double amount, @RequestParam Cashflow.FlowType flowType) {

        List<Account> overdraftAccounts = cashflowService.getOverdraftAccounts(singletonList(new Cashflow(new Account(accountNumber),
                date, amount, flowType)));
        return overdraftAccounts.isEmpty() ? null : overdraftAccounts.get(0);
    }
}
