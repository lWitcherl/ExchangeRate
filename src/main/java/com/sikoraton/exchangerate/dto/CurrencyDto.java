package com.sikoraton.exchangerate.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
@EqualsAndHashCode
public class CurrencyDto {
    private String code;
    private String name;

    public CurrencyDto(String code) {
        this.code = code;
    }

    public CurrencyDto(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
