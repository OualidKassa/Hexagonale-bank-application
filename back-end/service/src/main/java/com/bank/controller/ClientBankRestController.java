package com.bank.controller;

import com.bank.api.ClientBankDomain;
import com.bank.exception.ClientOrAccountNotFound;
import com.bank.model.Account;
import com.bank.spi.ClientBankProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class ClientBankRestController {


    @Autowired
    private ClientBankDomain clientBankDomain;

    @Autowired
    private ClientBankProvider clientBankProvider;

    @GetMapping("/client/{client}/account/{account}")
    @ResponseBody
    public Account getAccountClient(@PathVariable String client, @PathVariable String account) throws ClientOrAccountNotFound {
        return clientBankDomain.readAccount(client, account);
    }

    @GetMapping("/client/{client}/accounts")
    @ResponseBody
    public Set<Account> getAllAccountsClient(@PathVariable String client) throws ClientOrAccountNotFound {
        return clientBankProvider.findClient(client).orElseThrow(() -> new ClientOrAccountNotFound("Client not found !"))
                .getAccounts();
    }

    @PutMapping("/client/{clientName}/account/{accountName}")
    @ResponseBody
    public Double makeDepositInAccountClient(@PathVariable String clientName, @PathVariable String accountName, @RequestBody Provision deposit) throws ClientOrAccountNotFound {
        clientBankDomain.deposit(clientName, accountName, deposit.getDeposit());
        Account account = clientBankDomain.readAccount(clientName, accountName);

        return account.getBalance();
    }

}
