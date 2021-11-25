package com.bank.domain;

/*
 *
 * Here is the implementation of the hexagon
 *
 */

import com.bank.api.ClientBankDomain;
import com.bank.exception.ClientOrAccountNotFound;
import com.bank.model.Account;
import com.bank.spi.ClientBankProvider;

public class ClientBankDomainImpl implements ClientBankDomain {



    private ClientBankProvider clientBankProvider;


    public ClientBankDomainImpl( ClientBankProvider clientBankProvider) {
        this.clientBankProvider = clientBankProvider;
    }

    public ClientBankDomainImpl() {

    }

    public ClientBankProvider getClientBankProvider(){return  this.clientBankProvider;}

    @Override
    public Account readAccount(String clientName, String accountCode) throws ClientOrAccountNotFound {
        return clientBankProvider.findClient(clientName).orElseThrow(() -> new ClientOrAccountNotFound("Client not found!"))
                .getAccounts().stream().filter(account -> accountCode.equals(account.getName()))
                .findAny()
                .orElseThrow(() -> new ClientOrAccountNotFound("Account not found!"));
    }

    @Override
    public void deposit(String clientName, String accountCode, double amount) throws ClientOrAccountNotFound {
        Account account = readAccount(clientName, accountCode);
        account.setBalance(account.getBalance() + amount);
        clientBankProvider.findClient(clientName).orElseThrow(() -> new ClientOrAccountNotFound("Client not found!"))
                .getAccounts().add(account);
        clientBankProvider.saveAccount(account);
    }

}
