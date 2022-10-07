package com.sikoraton.exchangerate.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExchangeRateDto {
    private Long id;
    private CurrencyDto from;
    private CurrencyDto to;
    private BigDecimal rate;
    private String serviceName;
    private LocalDate date;
    private LocalDateTime dateFromImport;

    public ExchangeRateDto(CurrencyDto from,
                           CurrencyDto to,
                           BigDecimal rate,
                           String serviceName,
                           LocalDate date,
                           LocalDateTime dateFromImport) {
        this.from = from;
        this.to = to;
        this.rate = rate;
        this.serviceName = serviceName;
        this.date = date;
        this.dateFromImport = dateFromImport;
    }
}
