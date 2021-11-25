package com.bank.controller;

public class Provision {
    private double deposit;

    public Provision(double deposit) {
        this.deposit = deposit;
    }

    public double getDeposit() {
        return deposit;
    }

    public Provision() {
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }
}
