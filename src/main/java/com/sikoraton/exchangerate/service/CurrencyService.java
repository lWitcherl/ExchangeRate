package com.sikoraton.exchangerate.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.sikoraton.exchangerate.dto.CurrencyDto;

public interface CurrencyService {
    Set<String> getAllCurrencyCode();
    List<CurrencyDto> getAllCurrencies();
    Boolean validateCode(String from, Collection<String> to);
}
