package com.sikoraton.exchangerate.service.imp;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.sikoraton.exchangerate.dto.CurrencyDto;
import com.sikoraton.exchangerate.exception.CurrencyCodeValidationException;
import com.sikoraton.exchangerate.mapper.CurrencyMapper;
import com.sikoraton.exchangerate.repository.CurrencyRepository;
import com.sikoraton.exchangerate.service.CurrencyService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter @Setter
public class CurrencyServiceImpl implements CurrencyService {
    private CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Set<String> getAllCurrencyCode() {
        return currencyRepository.getAllCode();
    }

    @Override
    public List<CurrencyDto> getAllCurrencies() {
        return CurrencyMapper.INSTANCE.entityToDto(currencyRepository.findAll());
    }

    @Override
    public Boolean validateCode(String from, Collection<String> to) {
        Set<String> code = currencyRepository.getAllCode();
        if(!code.contains(from))
            throw new CurrencyCodeValidationException("From code");
        else if(!code.containsAll(to))
            throw new CurrencyCodeValidationException("Some TO code");
        return Boolean.TRUE;
    }
}
