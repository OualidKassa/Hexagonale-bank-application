package com.bank;

import com.bank.api.ClientBankDomain;
import com.bank.spi.ClientBankProvider;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.platform.engine.Cucumber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

@Cucumber
@CucumberOptions(features = "src/test/resources/features", publish = true, glue = "com.bank")
@RunWith(io.cucumber.junit.Cucumber.class)
@ContextConfiguration(classes = Configuration.class)
@ComponentScan("com.bank")
public class CucumberTest {


    @Test
    public void run() {
        // Without this, cucumber tests are executed but not counted by surefire
        Assertions.assertTrue(true);
    }
}
