package com.git.amc.rules.service;

import com.git.amc.rules.model.policy.Driver;
import com.git.amc.rules.model.policy.Policy;
import com.git.amc.rules.model.policy.PolicyType;
import lombok.AllArgsConstructor;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.stereotype.Service;

import static java.util.Arrays.asList;

@Service
@AllArgsConstructor
public class PolicyService {

    private final KieContainer kieContainer;

    public Policy getPolicy(Driver driver, PolicyType policyType) {
        Policy policy = new Policy();
        policy.setType(policyType.name());

        StatelessKieSession kieSession = kieContainer.newStatelessKieSession();
        kieSession.execute(asList(driver, policy));

        return policy;
    }
}
