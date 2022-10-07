package com.sikoraton.exchangerate.mapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.sikoraton.exchangerate.dto.CurrencyDto;
import com.sikoraton.exchangerate.dto.ExchangeRateDto;
import com.sikoraton.exchangerate.dto.ExchangeRateRequestDto;
import com.sikoraton.exchangerate.dto.imports.ApiLayerImportDto;
import com.sikoraton.exchangerate.dto.imports.CurrencyApiImportDto;
import com.sikoraton.exchangerate.dto.imports.CurrencyApiRateDto;
import com.sikoraton.exchangerate.entity.ExchangeRate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExchangeRateMapper {
    ExchangeRateMapper INSTANCE = Mappers.getMapper(ExchangeRateMapper.class);
    ExchangeRateDto entityToDto (ExchangeRate exchangeRate);
    List<ExchangeRateDto> entityToDto (Collection<ExchangeRate> exchangeRates);
    @Mapping(expression = "java(exchangeRates.getFrom().getCode())", target = "from")
    @Mapping(expression = "java(exchangeRates.getTo().getCode())", target = "to")
    ExchangeRateRequestDto entityToRequestDto (ExchangeRate exchangeRates);
    List<ExchangeRateRequestDto> entityToRequestDto (Collection<ExchangeRate> exchangeRates);
    ExchangeRate dtoToEntity (ExchangeRateDto exchangeRateDto);
    List<ExchangeRate> dtoToEntity (Collection<ExchangeRateDto> exchangeRateDtos);

    default List<ExchangeRateDto> importsToDto(String base, String service, CurrencyApiImportDto importDto){
        LocalDate date = LocalDate.now(ZoneId.of("UTC"));
        List<ExchangeRateDto> dtoList = new LinkedList<>();
        CurrencyDto baseCurrency = new CurrencyDto(base);
        for(Map.Entry<String, CurrencyApiRateDto> e : importDto.getData().entrySet())
            dtoList.add(new ExchangeRateDto(baseCurrency,
                                            CurrencyMapper.INSTANCE.importToDto(e.getValue()),
                                            e.getValue().getValue(),
                                            service,
                                            date,
                                            importDto.getDate().getDate()));
        return dtoList;
    }
    default List<ExchangeRateDto> importsToDto(String service, ApiLayerImportDto importDto){
        LocalDate date = LocalDate.now(ZoneId.of("UTC"));
        List<ExchangeRateDto> dtoList = new LinkedList<>();
        CurrencyDto baseCurrency = new CurrencyDto(importDto.getBase());
        for(Map.Entry<String, BigDecimal> e : importDto.getRates().entrySet())
            dtoList.add(new ExchangeRateDto(baseCurrency,
                                            new CurrencyDto(e.getKey()),
                                            e.getValue(),
                                            service,
                                            date,
                                            importDto.getDate().atStartOfDay()));
        return dtoList;
    }
}
