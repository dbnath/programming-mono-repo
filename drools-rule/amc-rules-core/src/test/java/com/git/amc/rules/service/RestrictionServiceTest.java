package com.git.amc.rules.service;

import com.git.amc.rules.config.DroolsConfig;
import com.git.amc.rules.model.Account;
import com.git.amc.rules.model.transfers.AccountTransfers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DroolsConfig.class, RestrictionService.class})
public class RestrictionServiceTest {

    @Autowired
    private RestrictionService restrictionService;

    private Account ACCOUNT_1;
    private Account ACCOUNT_2;

    @Before
    public void setup() {
        ACCOUNT_1 = new Account(1);
        ACCOUNT_2 = new Account(2);
    }

    @Test
    public void cipr() {
        ACCOUNT_1.setRestrictionCodes(singletonList("CIPR"));
        List<AccountTransfers> accountTransfers = restrictionService.applyTransferRules(asList(ACCOUNT_1, ACCOUNT_2));
        System.out.println("Transfer Rules="+ accountTransfers);

        assertNotNull(accountTransfers);
    }
}
