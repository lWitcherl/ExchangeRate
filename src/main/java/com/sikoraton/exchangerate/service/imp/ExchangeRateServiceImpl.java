package com.sikoraton.exchangerate.service.imp;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.sikoraton.exchangerate.dto.ExchangeRateDto;
import com.sikoraton.exchangerate.dto.ExchangeRateRequestDto;
import com.sikoraton.exchangerate.mapper.ExchangeRateMapper;
import com.sikoraton.exchangerate.repository.ExchangeRateRepository;
import com.sikoraton.exchangerate.service.CurrencyService;
import com.sikoraton.exchangerate.service.ExchangeRateService;
import com.sikoraton.exchangerate.utils.ProcessingSpecification;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

@Service
@Getter @Setter
@Transactional
public class ExchangeRateServiceImpl implements ExchangeRateService {
    private ExchangeRateRepository exchangeRateRepository;
    private CurrencyService currencyService;

    @Autowired
    public ExchangeRateServiceImpl(ExchangeRateRepository exchangeRateRepository,
                                   CurrencyService currencyService) {
        this.exchangeRateRepository = exchangeRateRepository;
        this.currencyService = currencyService;
    }

    @Override
    public List<ExchangeRateDto> saveAll(Collection<ExchangeRateDto> exchangeRateDtos) {
        return new LinkedList<>(ExchangeRateMapper.INSTANCE.entityToDto(
                exchangeRateRepository.saveAll(ExchangeRateMapper.INSTANCE.dtoToEntity(exchangeRateDtos))));
    }

    @Override
    public List<ExchangeRateRequestDto> getCurrentRate(String from, Collection<String> to) {
        currencyService.validateCode(from, to);
        return ExchangeRateMapper.INSTANCE.entityToRequestDto(
                exchangeRateRepository.findAllByFrom_CodeAndTo_CodeInAndDateAndLowerTrue(from, to,
                                                                                    LocalDate.now(ZoneId.of("UTC"))));
    }

    @Override
    public Boolean dailyLowerUpdate() {
        HashMap<String, ExchangeRateDto> actualRates = new HashMap<>();
        List<ExchangeRateDto> listFromDb = ExchangeRateMapper.INSTANCE.entityToDto(
                                                exchangeRateRepository.findAllByDate(
                                                        LocalDate.now(ZoneId.of("UTC"))));
        for (ExchangeRateDto x : listFromDb) {
            String key = x.getFrom().getCode().concat(x.getTo().getCode());
            if (!actualRates.containsKey(key) || actualRates.get(key).getRate().compareTo(x.getRate()) > 0) {
                actualRates.put(key, x);
            }
        }
        Integer saveRows = exchangeRateRepository.setLowerRate(actualRates.values().stream()
                                                                                    .map(ExchangeRateDto::getId)
                                                                                    .collect(Collectors.toList()));
        return actualRates.values().size() == saveRows;
    }

    @Override
    public List<ExchangeRateRequestDto> getByParams(MultiValueMap<String, String> params) {
        if (params.isEmpty()) return ExchangeRateMapper.INSTANCE.entityToRequestDto(exchangeRateRepository.findAll());

        return ExchangeRateMapper.INSTANCE.entityToRequestDto(
                exchangeRateRepository.findAll(ProcessingSpecification.processing(params)));
    }
}
