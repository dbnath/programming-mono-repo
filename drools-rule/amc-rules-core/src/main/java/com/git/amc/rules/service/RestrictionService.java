package com.git.amc.rules.service;

import com.git.amc.rules.model.Account;
import com.git.amc.rules.model.transfers.AccountTransfers;
import lombok.AllArgsConstructor;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RestrictionService {
    private final KieContainer kieContainer;

    public List<AccountTransfers> applyTransferRules(List<Account> accounts) {
        List<AccountTransfers> transfers = accounts.stream().map(AccountTransfers::new).collect(Collectors.toList());

        StatelessKieSession kieSession = kieContainer.newStatelessKieSession();
        kieSession.execute(transfers);

        return transfers;
    }
}
