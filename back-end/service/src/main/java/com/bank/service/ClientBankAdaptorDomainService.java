package com.bank.service;

import com.bank.api.ClientBankDomain;
import com.bank.domain.ClientBankDomainImpl;
import com.bank.exception.ClientOrAccountNotFound;
import com.bank.model.Account;
import com.bank.model.AccountEntity;
import com.bank.model.Client;
import com.bank.repository.AccountJpaRepository;
import com.bank.repository.ClientJpaRepository;
import com.bank.spi.ClientBankProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientBankAdaptorDomainService implements ClientBankDomain {

    @Autowired
    private ClientBankDomain clientBankDomain;


    @Override
    public Account readAccount(String clientName, String accountCode) throws ClientOrAccountNotFound {
        return clientBankDomain.readAccount(clientName, accountCode);
    }

    @Override
    @Transactional
    public void deposit(String clientName, String accountCode, double amount) throws ClientOrAccountNotFound {
        clientBankDomain.deposit(clientName, accountCode, amount);
    }
}
