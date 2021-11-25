package com.bank;

import com.bank.api.ClientBankDomain;
import com.bank.domain.ClientBankDomainImpl;
import com.bank.exception.ClientOrAccountNotFound;
import com.bank.model.Account;
import com.bank.model.Client;
import com.bank.spi.ClientBankProvider;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.platform.engine.Cucumber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.bank")
public class MyStepdefs {


    Client steve;
    Account fortuneo;
    Account N26;
    Account testAccountSteve;

    Client elon;
    Account BNP;
    Account testAccountElon;

    Client jeff;
    Account bForBank;
    Account testAccountJeff;

    Client clientFind;





    @Autowired
     ClientBankDomain clientBankDomain;


    @Given("I am steve")
    public void iAmSteve() {
        steve = new Client("steve");
    }

    @And("I own {double} on my account FORTUNEO")
    public void iOwnOnMyAccountFORTUNEO(double arg0) {
        fortuneo = new Account("FORTUNEO");
        fortuneo.setBalance(arg0);

    }

    @And("I own {double} on my account N26")
    public void iOwnOnMyAccountN(double arg0) {
        N26 = new Account("N26");
        N26.setBalance(arg0);

    }

    @When("I check my account FORTUNEO")
    public void iCheckMyAccountFORTUNEO() throws ClientOrAccountNotFound {

        testAccountSteve =  clientBankDomain.readAccount(steve.getName(), fortuneo.getName());
    }

    @Then("balance of my account FORTUNEO should be {double}")
    public void balanceOfMyAccountFORTUNEOShouldBe(double arg0) {
        Assertions.assertEquals(arg0, testAccountSteve.getBalance());
    }

    @Given("I am elon")
    public void iAmElon() {
        elon = new Client("elon");
    }

    @And("I own {double} on my account BNP")
    public void iOwnOnMyAccountBNP(double balanceBnp) {
        BNP = new Account("BNP");
        BNP.setBalance(balanceBnp);
    }

    @When("I deposit {double} on my account BNP")
    public void iDepositOnMyAccountBNP(double depositBnp) throws ClientOrAccountNotFound {
        clientBankDomain.deposit(elon.getName(), BNP.getName(), depositBnp);
    }

    @And("I check my account BNP")
    public void iCheckMyAccountBNP() throws ClientOrAccountNotFound {
        testAccountElon = clientBankDomain.readAccount(elon.getName(), BNP.getName());
    }

    @Then("balance of my account BNP should be {double}")
    public void balanceOfMyAccountBNPShouldBe(double expectAmount) {
        Assertions.assertEquals(expectAmount, testAccountElon.getBalance());
    }


    @Given("I am jeff")
    public void iAmJeff() {
        jeff = new Client("jeff");
    }

    @And("I own {double} on my account BFORBANK")
    public void iOwnOnMyAccountBFORBANK(double ownBforBank) {
        bForBank = new Account("BFORBANK");
        bForBank.setBalance(ownBforBank);
    }

    @When("I withdraw {double} on my account BFORBANK")
    public void iWithdrawOnMyAccountBFORBANK(double depotBforBnak) throws ClientOrAccountNotFound {
        clientBankDomain.deposit(jeff.getName(),bForBank.getName(), -depotBforBnak);
    }

    @And("I check my account BFORBANK")
    public void iCheckMyAccountBFORBANK() throws ClientOrAccountNotFound {
        testAccountJeff = clientBankDomain.readAccount(jeff.getName(), bForBank.getName());
    }

    @Then("balance of my account BFORBANK should be {double}")
    public void balanceOfMyAccountBFORBANKShouldBe(double expectedBforBank) {
        Assertions.assertEquals(expectedBforBank, testAccountJeff.getBalance());
    }
}
