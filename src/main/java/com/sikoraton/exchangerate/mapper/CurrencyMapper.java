package com.sikoraton.exchangerate.mapper;

import java.util.Collection;
import java.util.List;

import com.sikoraton.exchangerate.dto.CurrencyDto;
import com.sikoraton.exchangerate.dto.imports.CurrencyApiRateDto;
import com.sikoraton.exchangerate.entity.Currency;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CurrencyMapper {
    CurrencyMapper INSTANCE = Mappers.getMapper(CurrencyMapper.class);
    CurrencyDto entityToDto (Currency currency);
    List<CurrencyDto> entityToDto (Collection<Currency> currency);
    Currency dtoToEntity (CurrencyDto currencyDto);
    List<Currency> dtoToEntity (Collection<CurrencyDto> currencyDto);
    @Mapping(source = "code", target = "code")
    @Mapping(target = "name", ignore = true)
    CurrencyDto importToDto(CurrencyApiRateDto currencyApiRateDto);
}
