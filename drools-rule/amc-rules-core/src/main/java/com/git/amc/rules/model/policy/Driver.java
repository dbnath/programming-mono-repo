package com.git.amc.rules.model.policy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Driver {
    private String name = "Mr Joe Blogs";
    private Integer age = 30;
    private Integer priorClaims = 0;
    private String locationRiskProfile = "LOW";
}
