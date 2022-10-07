package com.sikoraton.exchangerate.dto.imports;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CurrencyApiRateDto {
    private String code;
    private BigDecimal value;
}
