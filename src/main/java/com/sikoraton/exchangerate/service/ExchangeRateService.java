package com.sikoraton.exchangerate.service;

import java.util.Collection;
import java.util.List;

import com.sikoraton.exchangerate.dto.ExchangeRateDto;
import com.sikoraton.exchangerate.dto.ExchangeRateRequestDto;
import org.springframework.util.MultiValueMap;

public interface ExchangeRateService {
    List<ExchangeRateDto> saveAll(Collection<ExchangeRateDto> exchangeRateDtos);

    List<ExchangeRateRequestDto> getCurrentRate(String from, Collection<String> to);

    Boolean dailyLowerUpdate();

    List<ExchangeRateRequestDto> getByParams(MultiValueMap<String, String> params);
}
