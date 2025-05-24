package com.git.amc.rules.model.transfers;

import com.git.amc.rules.model.transfers.TransferFlowType;
import com.git.amc.rules.model.transfers.TransferType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
@ToString
public class Transfer {
    private static final String ENABLE = "Enable";

    private TransferType transferType;
    private Map<TransferFlowType, Boolean> flowDirections = new LinkedHashMap<>();

    public Transfer(TransferType transferType) {
        this.transferType = transferType;
    }

    public void addFlowDirection(TransferFlowType flowType, String decision) {
        this.flowDirections.put(flowType, ENABLE.equalsIgnoreCase(decision));
    }
}
