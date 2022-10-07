package com.sikoraton.exchangerate.dto.imports;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
public class ApiLayerImportDto {
    private boolean success;
    private int timestamp;
    private String base;
    private LocalDate date;
    private Map<String, BigDecimal> rates;

    public ApiLayerImportDto(String base, LocalDate date, Map<String, BigDecimal> rates) {
        this.base = base;
        this.date = date;
        this.rates = rates;
    }
}
