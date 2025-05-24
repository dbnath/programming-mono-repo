package com.git.amc.rules.service;

import com.git.amc.rules.model.Customer;
import lombok.AllArgsConstructor;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DiscountService {
    private final KieSession kieSession;

    public int getCustomerDiscount(Customer.CustomerType customerType, int years) {
        Customer customer = new Customer(customerType, years);
        kieSession.insert(customer);
        kieSession.fireAllRules();

        return customer.getDiscount();
    }
}
