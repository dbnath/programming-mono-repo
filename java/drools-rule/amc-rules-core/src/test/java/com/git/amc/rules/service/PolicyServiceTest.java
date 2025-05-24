package com.git.amc.rules.service;

import com.git.amc.rules.config.DroolsConfig;
import com.git.amc.rules.model.policy.Driver;
import com.git.amc.rules.model.policy.Policy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static com.git.amc.rules.model.policy.PolicyType.COMPREHENSIVE;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DroolsConfig.class, PolicyService.class})
public class PolicyServiceTest {

    @Autowired
    private PolicyService policyService;

    @Test
    public void youngSafeDriverPolicy() {
        Driver driver = new Driver();
        Policy policy = policyService.getPolicy(driver, COMPREHENSIVE);

        assertThat(policy.getBasePrice()).isEqualTo(120);
        assertThat(policy.getDiscountPercent()).isEqualTo(20);
    }

    @Test
    public void youngRiskyDriverPolicy() {
        Driver driver = new Driver("DriverName", 20, 0, "HIGH");
        Policy policy = policyService.getPolicy(driver, COMPREHENSIVE);

        assertThat(policy.getBasePrice()).isEqualTo(700);
        assertThat(policy.getDiscountPercent()).isEqualTo(1);
    }

    @Test
    public void matureDriverPolicy() {
        Driver driver = new Driver("DriverName", 30, 1, "HIGH");
        Policy policy = policyService.getPolicy(driver, COMPREHENSIVE);

        assertThat(policy.getBasePrice()).isEqualTo(300);
        assertThat(policy.getDiscountPercent()).isEqualTo(5);
    }

}
