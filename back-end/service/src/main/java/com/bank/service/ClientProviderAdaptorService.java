package com.bank.service;

import com.bank.api.ClientBankDomain;
import com.bank.domain.ClientBankDomainImpl;
import com.bank.model.Account;
import com.bank.model.AccountEntity;
import com.bank.model.Client;
import com.bank.model.ClientEntity;
import com.bank.repository.AccountJpaRepository;
import com.bank.repository.ClientJpaRepository;
import com.bank.spi.ClientBankProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientProviderAdaptorService implements ClientBankProvider {


    @Autowired
    private ClientJpaRepository clientJpaRepository;

    @Autowired
    private AccountJpaRepository accountJpaRepository;



    @Override
    public Optional<Client> findClient(String clientName) {
        ClientEntity clientEntity = clientJpaRepository.findByName(clientName);
        return Optional.of(convertClientEntityToClient(clientEntity));
    }

    @Override
    @Transactional
    public void saveAccount(Account account) {
        AccountEntity accountEntity = accountJpaRepository.findAccountEntityByCode(account.getName());
        ClientEntity clientEntity = clientJpaRepository.findOwnerInAccount(accountEntity.getOwner().getId());
        accountEntity.setBalance(account.getBalance());
        clientEntity.getAccounts().add(accountEntity);
        accountJpaRepository.makeDepotInAccount(accountEntity.getCode(), accountEntity.getBalance());
        clientJpaRepository.save(clientEntity);
    }

    private Client convertClientEntityToClient(ClientEntity clientEntity) {

        Client client = new Client(clientEntity.getName());
        client.getAccounts().addAll(clientEntity.getAccounts().stream().map(this::convertAccountEntityToAccount)
                .collect(Collectors.toList()));

        return client;
    }

    private Account convertAccountEntityToAccount(AccountEntity accountEntity) {
        Account account = new Account(accountEntity.getCode());
        account.setBalance(accountEntity.getBalance());
        return account;

    }

    private AccountEntity convertAccountToAccountEntity(Account account) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setCode(account.getName());
        accountEntity.setBalance(account.getBalance());
        return accountEntity;

    }
}
