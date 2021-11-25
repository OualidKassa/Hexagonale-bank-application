package com.bank;

import com.bank.api.ClientBankDomain;
import com.bank.domain.ClientBankDomainImpl;
import com.bank.spi.ClientBankProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@org.springframework.context.annotation.Configuration
@Component
public class Configuration {

    @Bean
    public ClientBankProvider clientBankProvider(){
        return new ClientBankDomainImpl().getClientBankProvider();
    }

    @Bean
    public ClientBankDomain clientBankDomain(){
        return new ClientBankDomainImpl(clientBankProvider());
    }
}
