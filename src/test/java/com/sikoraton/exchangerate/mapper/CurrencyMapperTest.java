package com.sikoraton.exchangerate.mapper;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import com.sikoraton.exchangerate.dto.CurrencyDto;
import com.sikoraton.exchangerate.dto.imports.CurrencyApiRateDto;
import com.sikoraton.exchangerate.entity.Currency;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class CurrencyMapperTest {
    private CurrencyMapper mapper = CurrencyMapper.INSTANCE;
    private static final String CODE = "USD";
    private static final String NAME = "test";
    private static final Currency CURRENCY = new Currency(CODE, NAME);
    private static final CurrencyDto CURRENCY_DTO = new CurrencyDto(CODE, NAME);

    @Test
    void entityToDto() {
        CurrencyDto result = mapper.entityToDto(CURRENCY);
        assertNotNull(result);
        assertEquals(CURRENCY.getCode(), result.getCode());
        assertEquals(CURRENCY.getName(), result.getName());
    }

    @Test
    void testEntityToDto() {
        List<CurrencyDto> result = mapper.entityToDto(Collections.singletonList(CURRENCY));
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(CURRENCY.getCode(), result.get(0).getCode());
        assertEquals(CURRENCY.getName(), result.get(0).getName());
    }

    @Test
    void dtoToEntity() {
        Currency result = mapper.dtoToEntity(CURRENCY_DTO);
        assertNotNull(result);
        assertEquals(CURRENCY.getCode(), result.getCode());
        assertEquals(CURRENCY.getName(), result.getName());
    }

    @Test
    void testDtoToEntity() {
        List<Currency> result = mapper.dtoToEntity(Collections.singletonList(CURRENCY_DTO));
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(CURRENCY.getCode(), result.get(0).getCode());
        assertEquals(CURRENCY.getName(), result.get(0).getName());
    }

    @Test
    void importToDto() {
        CurrencyApiRateDto currency = new CurrencyApiRateDto();
        currency.setCode(CODE);
        currency.setValue(BigDecimal.ZERO);
        CurrencyDto result = mapper.importToDto(currency);
        assertNotNull(result);
        assertEquals(currency.getCode(), result.getCode());
        assertNull(result.getName());
    }
}