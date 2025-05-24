package com.git.amc.rules.model.transfers;

import com.git.amc.rules.model.Account;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccountTransfers {
    private Account account;
    private Transfer internalTransferOnce;
    private Transfer internalTransferRecurring;
    private Transfer externalTransfer;
    private Transfer achProfile;
    private Transfer achMoneyTransfer;
    private Transfer checkDeposit;
    private Transfer cardTransactions;

    public AccountTransfers(Account account) {
        this.account = account;
        this.internalTransferOnce = new Transfer(TransferType.INTERNAL_ONETIME);
        this.internalTransferRecurring = new Transfer(TransferType.INTERNAL_RECURRING);
        this.externalTransfer = new Transfer(TransferType.EXTERNAL);
        this.achProfile = new Transfer(TransferType.ACH_PROFILE);
        this.achMoneyTransfer = new Transfer(TransferType.ACH_MONEY);
        this.checkDeposit = new Transfer(TransferType.CHECK_DEPOSIT);
        this.cardTransactions = new Transfer(TransferType.CARD);
    }
}
