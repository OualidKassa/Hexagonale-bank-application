package com.bank.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 *
 * Here is the model which is used/processed by the hexagon
 *
 */
public class Client {
    private String name;
    private Set<Account> accounts;

    public Client(String name, Set<Account> accounts) {
        this.name = name;
        this.accounts = accounts;
    }

    public Client(String name) {
        this.name = name;
        this.accounts = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }
}
