package com.git.amc.rules.service;

import com.git.amc.rules.model.Account;
import com.git.amc.rules.model.Cashflow;
import lombok.AllArgsConstructor;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CashflowService {
    private final KieSession kieSession;

    public List<Account> getOverdraftAccounts(List<Cashflow> cashFlows) {
        cashFlows.forEach(kieSession::insert);
        kieSession.fireAllRules();

        Set<Account> accounts = cashFlows.stream().map(Cashflow::getAccount).collect(Collectors.toSet());
        accounts.forEach(kieSession::insert);
        kieSession.fireAllRules();

        return accounts.stream().filter(Account::isOverdraft).collect(Collectors.toList());
    }
}
