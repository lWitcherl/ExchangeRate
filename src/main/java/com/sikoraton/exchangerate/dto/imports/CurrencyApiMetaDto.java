package com.sikoraton.exchangerate.dto.imports;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CurrencyApiMetaDto {
    @JsonProperty("last_updated_at")
    private LocalDateTime date;
}
