package com.bank.spi;

/*
 *
 * Here is the standard provider interfaces which are used by hexagon
 *
 */

import com.bank.exception.ClientOrAccountNotFound;
import com.bank.model.Account;
import com.bank.model.Client;

import java.util.Optional;

public interface ClientBankProvider {
    Optional<Client> findClient(String clientName) throws ClientOrAccountNotFound;
    void saveAccount(Account account);
}
