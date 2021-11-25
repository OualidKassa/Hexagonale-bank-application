package com.bank.configuration;

import com.bank.api.ClientBankDomain;
import com.bank.domain.ClientBankDomainImpl;
import com.bank.service.ClientProviderAdaptorService;
import com.bank.spi.ClientBankProvider;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public ClientBankProvider clientBankProvider(){
        return new ClientProviderAdaptorService();
    }

    @Bean
    public ClientBankDomain clientBankDomain(){
        return new ClientBankDomainImpl(clientBankProvider());
    }
}
