package com.sikoraton.exchangerate;

import java.util.LinkedList;
import java.util.List;

import com.sikoraton.exchangerate.entity.Currency;
import com.sikoraton.exchangerate.imports.CurrencyImport;
import com.sikoraton.exchangerate.repository.CurrencyRepository;
import com.sikoraton.exchangerate.service.ExchangeRateService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
/**
 * class for the initial filling of the database
 * **/
@Component
@Getter @Setter
public class InitialDataLoader implements ApplicationRunner {
    private CurrencyRepository currencyRepository;
    private List<CurrencyImport> currencyImport;
    private ExchangeRateService exchangeRateService;

    @Autowired
    public InitialDataLoader(CurrencyRepository currencyRepository,
                             List<CurrencyImport> currencyImport,
                             ExchangeRateService exchangeRateService) {
        this.currencyRepository = currencyRepository;
        this.currencyImport = currencyImport;
        this.exchangeRateService = exchangeRateService;
    }

    @Override
    public void run(ApplicationArguments args) {
        List<Currency> initialCurrencyList = new LinkedList<>();
        initialCurrencyList.add(new Currency("USD","United States dollar"));
        initialCurrencyList.add(new Currency("EUR","Euro"));
        initialCurrencyList.add(new Currency("UAH","Ukrainian hryvnia"));
        currencyRepository.saveAll(initialCurrencyList);
        currencyImport.forEach(CurrencyImport::daily);
        exchangeRateService.dailyLowerUpdate();
    }
}
