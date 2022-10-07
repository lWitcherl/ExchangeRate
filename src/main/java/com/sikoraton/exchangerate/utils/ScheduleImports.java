package com.sikoraton.exchangerate.utils;

import java.util.List;

import com.sikoraton.exchangerate.imports.CurrencyImport;
import com.sikoraton.exchangerate.service.ExchangeRateService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
public class ScheduleImports {
    private List<CurrencyImport> imports;
    private ExchangeRateService exchangeRateService;

    @Autowired
    public ScheduleImports(List<CurrencyImport> imports,
                           ExchangeRateService exchangeRateService) {
        this.imports = imports;
        this.exchangeRateService = exchangeRateService;
    }

    @Scheduled(cron = "${import-service.import-crone}", zone = "UTC")
    void dailyImport(){
        imports.forEach(CurrencyImport::daily);
        exchangeRateService.dailyLowerUpdate();
    }
}
