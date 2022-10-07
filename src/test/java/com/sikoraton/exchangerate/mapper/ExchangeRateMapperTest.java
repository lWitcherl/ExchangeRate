package com.sikoraton.exchangerate.mapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import com.sikoraton.exchangerate.dto.CurrencyDto;
import com.sikoraton.exchangerate.dto.ExchangeRateDto;
import com.sikoraton.exchangerate.dto.ExchangeRateRequestDto;
import com.sikoraton.exchangerate.dto.imports.ApiLayerImportDto;
import com.sikoraton.exchangerate.dto.imports.CurrencyApiImportDto;
import com.sikoraton.exchangerate.dto.imports.CurrencyApiMetaDto;
import com.sikoraton.exchangerate.dto.imports.CurrencyApiRateDto;
import com.sikoraton.exchangerate.entity.Currency;
import com.sikoraton.exchangerate.entity.ExchangeRate;
import org.assertj.core.util.Maps;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ExchangeRateMapperTest {
    private ExchangeRateMapper mapper = ExchangeRateMapper.INSTANCE;
    private static final String CODE = "USD";
    private static final String NAME = "test";
    private static final Currency CURRENCY = new Currency(CODE, NAME);
    private static final CurrencyDto CURRENCY_DTO = new CurrencyDto(CODE, NAME);

    private static final LocalDate DATE = LocalDate.now();
    private static final LocalDateTime DATE_FROM_IMPORT = LocalDateTime.now();
    private static final String SERVICE = "testservice";
    private static final BigDecimal RATE = BigDecimal.ONE;
    private static final ExchangeRateDto EXCHANGE_RATE_DTO =
                                new ExchangeRateDto(CURRENCY_DTO, CURRENCY_DTO, RATE, SERVICE, DATE, DATE_FROM_IMPORT);
    private static final ExchangeRate EXCHANGE_RATE = new ExchangeRate(CURRENCY, CURRENCY, RATE, SERVICE, DATE);

    @Test
    void entityToDto() {
        ExchangeRateDto result = mapper.entityToDto(EXCHANGE_RATE);
        assertNotNull(result);
        assertNotNull(result.getFrom());
        assertNotNull(result.getTo());
        assertEquals(DATE, result.getDate());
        assertEquals(SERVICE, result.getServiceName());
        assertEquals(RATE, result.getRate());
    }

    @Test
    void testEntityToDto() {
        List<ExchangeRateDto> result = mapper.entityToDto(Collections.singleton(EXCHANGE_RATE));
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertNotNull(result.get(0).getFrom());
        assertNotNull(result.get(0).getTo());
        assertEquals(DATE, result.get(0).getDate());
        assertEquals(SERVICE, result.get(0).getServiceName());
        assertEquals(RATE,result.get(0).getRate());
    }

    @Test
    void entityToRequestDto() {
        ExchangeRateRequestDto result = mapper.entityToRequestDto(EXCHANGE_RATE);
        assertNotNull(result);
        assertNotNull(result.getFrom());
        assertNotNull(result.getTo());
        assertEquals(DATE, result.getDate());
        assertEquals(SERVICE, result.getServiceName());
        assertEquals(RATE, result.getRate());
    }

    @Test
    void testEntityToRequestDto() {
        List<ExchangeRateRequestDto> result = mapper.entityToRequestDto(Collections.singleton(EXCHANGE_RATE));
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertNotNull(result.get(0).getFrom());
        assertNotNull(result.get(0).getTo());
        assertEquals(DATE, result.get(0).getDate());
        assertEquals(SERVICE, result.get(0).getServiceName());
        assertEquals(RATE, result.get(0).getRate());
    }

    @Test
    void dtoToEntity() {
        ExchangeRate result = mapper.dtoToEntity(EXCHANGE_RATE_DTO);
        assertNotNull(result);
        assertNotNull(result.getFrom());
        assertNotNull(result.getTo());
        assertEquals(DATE, result.getDate());
        assertEquals(SERVICE, result.getServiceName());
        assertEquals(RATE, result.getRate());
    }

    @Test
    void testDtoToEntity() {
        List<ExchangeRate> result = mapper.dtoToEntity(Collections.singleton(EXCHANGE_RATE_DTO));
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertNotNull(result.get(0).getFrom());
        assertNotNull(result.get(0).getTo());
        assertEquals(DATE, result.get(0).getDate());
        assertEquals(SERVICE, result.get(0).getServiceName());
        assertEquals(RATE, result.get(0).getRate());
    }

    @Test
    void importsToDto() {
        CurrencyApiImportDto source = new CurrencyApiImportDto();
        CurrencyApiMetaDto metaDto = new CurrencyApiMetaDto();
        metaDto.setDate(DATE.atStartOfDay());
        CurrencyApiRateDto currencyApiRateDto = new CurrencyApiRateDto();
        currencyApiRateDto.setValue(RATE);
        currencyApiRateDto.setCode(CODE);
        source.setDate(metaDto);
        source.setData(Maps.newHashMap(CODE, currencyApiRateDto));
        List<ExchangeRateDto> result = mapper.importsToDto(CODE, SERVICE, source);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertNotNull(result.get(0).getFrom());
        assertNotNull(result.get(0).getTo());
        assertEquals(DATE, result.get(0).getDate());
        assertEquals(SERVICE, result.get(0).getServiceName());
        assertEquals(RATE, result.get(0).getRate());
    }

    @Test
    void testImportsToDto() {
        ApiLayerImportDto source = new ApiLayerImportDto(CODE, DATE, Maps.newHashMap(CODE, RATE));
        List<ExchangeRateDto> result = mapper.importsToDto(SERVICE, source);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertNotNull(result.get(0).getFrom());
        assertNotNull(result.get(0).getTo());
        assertEquals(DATE, result.get(0).getDate());
        assertEquals(SERVICE, result.get(0).getServiceName());
        assertEquals(RATE, result.get(0).getRate());
    }
}