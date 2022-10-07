package com.sikoraton.exchangerate.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class ExchangeRateRequestDto {
    private String from;
    private String to;
    private BigDecimal rate;
    private String serviceName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
}
