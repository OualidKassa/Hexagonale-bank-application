package com.bank.service;

import com.bank.api.ClientBankDomain;
import com.bank.configuration.Configuration;
import com.bank.domain.ClientBankDomainImpl;
import com.bank.exception.ClientOrAccountNotFound;
import com.bank.model.Account;
import com.bank.model.Client;
import com.bank.repository.AccountJpaRepository;
import com.bank.spi.ClientBankProvider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Arrays;

@ContextConfiguration(classes=Configuration.class, loader= AnnotationConfigContextLoader.class)
public class ClientBankDomainImplTest {


    ClientBankProvider clientBankProvider;


    ClientBankDomain clientBankDomain;

    Client client;
    Account account1;
    Account account2;
    Account account3;


    @BeforeEach
    public void init(){
        clientBankDomain = new ClientBankDomainImpl(clientBankProvider);
        client = new Client("clientTest");
         account1 = new Account("1234");
        account1.setBalance(100);

         account2 = new Account("12345");
        account2.setBalance(200);

         account3 = new Account("123456");
        account3.setBalance(300);

        client.getAccounts().addAll(Arrays.asList(account1, account2, account3));
    }

    @Test
    public void testGetBalanceClientAccount() throws ClientOrAccountNotFound {

        clientBankDomain.readAccount(client.getName(), "1234");

        Account result = clientBankDomain.readAccount(client.getName(), "1234");
        Assertions.assertThat(result.getBalance()).isEqualTo(100);

        Account resultNull = clientBankDomain.readAccount(client.getName(), null);

        Assertions.assertThat(resultNull).isNull();
    }

    @Test
    public void testPutDepositClientAccount() throws ClientOrAccountNotFound {
        Account result;
        clientBankDomain.deposit(client.getName(), "12345", 6.6);
        result = clientBankDomain.readAccount(client.getName(), "12345");
        Assertions.assertThat(result.getBalance()).isEqualTo(206.6);

        Account resultNull;
        clientBankDomain.deposit(client.getName(), null, 6.6);
        resultNull = clientBankDomain.readAccount(client.getName(), "Null");
        Assertions.assertThat(resultNull).isNull();
    }

}
