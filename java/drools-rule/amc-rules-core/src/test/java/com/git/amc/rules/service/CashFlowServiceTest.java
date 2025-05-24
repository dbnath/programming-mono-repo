package com.git.amc.rules.service;

import com.git.amc.rules.config.DroolsConfig;
import com.git.amc.rules.model.Account;
import com.git.amc.rules.model.Cashflow;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.git.amc.rules.model.Cashflow.FlowType.CREDIT;
import static com.git.amc.rules.model.Cashflow.FlowType.DEBIT;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DroolsConfig.class, CashflowService.class})
public class CashFlowServiceTest {

    @Autowired
    private CashflowService cashflowService;

    private Account ACCOUNT_1;
    private Account ACCOUNT_2;

    @Before
    public void setup() {
        ACCOUNT_1 = new Account(1);
        ACCOUNT_2 = new Account(2);
    }

    @Test
    public void overdrafts_not_present_on_no_cashflow() {
        List<Cashflow> cashflows = new ArrayList<>();
        List<Account> overdraftAccounts = cashflowService.getOverdraftAccounts(cashflows);

        assertTrue(CollectionUtils.isEmpty(overdraftAccounts));
    }

    @Test
    public void overdrafts_not_present_on_all_credits() {
        List<Cashflow> cashflows = new ArrayList<>();
        cashflows.add(new Cashflow(ACCOUNT_1, LocalDate.parse("2022-01-01"), 100, CREDIT));
        cashflows.add(new Cashflow(ACCOUNT_1, LocalDate.parse("2022-01-20"), 50, CREDIT));
        cashflows.add(new Cashflow(ACCOUNT_2, LocalDate.parse("2022-01-07"), 150, CREDIT));

        List<Account> overdraftAccounts = cashflowService.getOverdraftAccounts(cashflows);

        assertTrue(CollectionUtils.isEmpty(overdraftAccounts));
    }

    @Test
    public void overdrafts_present_on_more_debits() {
        List<Cashflow> cashflows = new ArrayList<>();
        cashflows.add(new Cashflow(ACCOUNT_1, LocalDate.parse("2022-01-21"), 100, DEBIT));
        cashflows.add(new Cashflow(ACCOUNT_1, LocalDate.parse("2022-01-07"), 50, CREDIT));

        List<Account> overdraftAccounts = cashflowService.getOverdraftAccounts(cashflows);

        assertFalse(CollectionUtils.isEmpty(overdraftAccounts));
    }

    @Test
    public void overdrafts_not_present_on_less_debits() {
        List<Cashflow> cashflows = new ArrayList<>();
        cashflows.add(new Cashflow(ACCOUNT_1, LocalDate.parse("2022-01-21"), 20, DEBIT));
        cashflows.add(new Cashflow(ACCOUNT_1, LocalDate.parse("2022-01-07"), 50, CREDIT));

        List<Account> overdraftAccounts = cashflowService.getOverdraftAccounts(cashflows);

        assertTrue(CollectionUtils.isEmpty(overdraftAccounts));
    }
}
