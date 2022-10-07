package com.sikoraton.exchangerate.dto.imports;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CurrencyApiImportDto {
    @JsonProperty("meta")
    private CurrencyApiMetaDto date;
    private Map<String, CurrencyApiRateDto> data;
}
