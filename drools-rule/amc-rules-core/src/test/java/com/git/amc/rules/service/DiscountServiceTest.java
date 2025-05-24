package com.git.amc.rules.service;

import com.git.amc.rules.config.DroolsConfig;
import com.git.amc.rules.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DroolsConfig.class, DiscountService.class})
public class DiscountServiceTest {

    @Autowired
    private DiscountService discountService;

    @Test
    public void customerDiscount() {
        int customerDiscount = discountService.getCustomerDiscount(Customer.CustomerType.INDIVIDUAL, 5);
        assertEquals(15, customerDiscount);

        customerDiscount = discountService.getCustomerDiscount(Customer.CustomerType.INDIVIDUAL, 1);
        assertEquals(5, customerDiscount);

        customerDiscount = discountService.getCustomerDiscount(Customer.CustomerType.BUSINESS, 0);
        assertEquals(20, customerDiscount);
    }
}
