package com.bank.api;

/*
 *
 * Here is the interface of the hexagon. It's contract
 *
 */

import com.bank.exception.ClientOrAccountNotFound;
import com.bank.model.Account;

public interface ClientBankDomain {
    Account readAccount(String clientName, String accountCode) throws ClientOrAccountNotFound;
    void deposit(String clientName, String accountCode, double amount) throws ClientOrAccountNotFound;
}
