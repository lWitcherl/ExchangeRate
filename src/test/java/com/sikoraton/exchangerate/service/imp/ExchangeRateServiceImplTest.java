package com.sikoraton.exchangerate.service.imp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.sikoraton.exchangerate.dto.CurrencyDto;
import com.sikoraton.exchangerate.dto.ExchangeRateDto;
import com.sikoraton.exchangerate.dto.ExchangeRateRequestDto;
import com.sikoraton.exchangerate.entity.ExchangeRate;
import com.sikoraton.exchangerate.mapper.ExchangeRateMapper;
import com.sikoraton.exchangerate.service.ExchangeRateService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static com.sikoraton.exchangerate.utils.constant.RequestParamConstant.PARAM_DATE_START;
import static com.sikoraton.exchangerate.utils.constant.RequestParamConstant.PARAM_FROM;
import static com.sikoraton.exchangerate.utils.constant.RequestParamConstant.PARAM_RATE_LESS;
import static com.sikoraton.exchangerate.utils.constant.RequestParamConstant.PARAM_SERVICE;
import static com.sikoraton.exchangerate.utils.constant.RequestParamConstant.PARAM_TO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ExchangeRateServiceImplTest {
    private static final String SERVICE = "unictest";
    private static final String RATE = "0.021640";
    private static final String FROM = "UAH";
    private static final String TO = "UAH";
    private static final LocalDate DATE = LocalDate.now(ZoneId.of("UTC"));
    private static final LocalDateTime DATE_ROM_IMPORT = LocalDateTime.now(ZoneId.of("UTC"));

    private static final CurrencyDto FROM_TEST = new CurrencyDto(FROM);
    private static final CurrencyDto TO_TEST = new CurrencyDto(TO);
    private static final BigDecimal RATE_TEST = new BigDecimal(RATE);
    private static final ExchangeRateDto RATE_DTO_TEST =
                                    new ExchangeRateDto(FROM_TEST, TO_TEST, RATE_TEST, SERVICE, DATE,DATE_ROM_IMPORT);

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Test
    void saveAll() {
        List<ExchangeRateDto> rateList = new LinkedList<>();
        rateList.add(RATE_DTO_TEST);
        List<ExchangeRate> resultC = ExchangeRateMapper.INSTANCE.dtoToEntity(rateList);
        rateList = exchangeRateService.saveAll(rateList);
        assertEquals(1, rateList.size());
        ExchangeRateDto exchangeRateDtoFromBD = rateList.get(0);
        assertNotNull(exchangeRateDtoFromBD.getId());
        assertEquals(FROM_TEST, exchangeRateDtoFromBD.getFrom());
        assertEquals(TO_TEST, exchangeRateDtoFromBD.getTo());
        assertEquals(RATE_TEST, exchangeRateDtoFromBD.getRate());
        assertEquals(SERVICE, exchangeRateDtoFromBD.getServiceName());
        assertEquals(DATE, exchangeRateDtoFromBD.getDate());
    }

    @Test
    void dailyLowerUpdate() {
        List<Long> ids = Lists.newArrayList(1L);
        assertTrue(exchangeRateService.dailyLowerUpdate());
    }

    @Test
    void getCurrentRate() {
        exchangeRateService.saveAll(Collections.singleton(RATE_DTO_TEST));
        exchangeRateService.dailyLowerUpdate();
        List<ExchangeRateRequestDto> rateList = exchangeRateService.getCurrentRate(FROM, Collections.singleton(TO));
        assertFalse(rateList.isEmpty());
        ExchangeRateRequestDto exchangeRateDtoFromBD = rateList.get(0);
        assertEquals(FROM, exchangeRateDtoFromBD.getFrom());
        assertEquals(TO, exchangeRateDtoFromBD.getTo());
        assertEquals(RATE_TEST, exchangeRateDtoFromBD.getRate());
        assertEquals(SERVICE, exchangeRateDtoFromBD.getServiceName());
        assertEquals(DATE, exchangeRateDtoFromBD.getDate());
    }

    @Test
    void getByParams() {
        exchangeRateService.saveAll(Collections.singleton(RATE_DTO_TEST));
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(PARAM_SERVICE, SERVICE);
        params.add(PARAM_FROM, FROM);
        params.add(PARAM_TO, TO);
        params.add(PARAM_DATE_START, DATE.toString());
        params.add(PARAM_RATE_LESS, RATE);
        List<ExchangeRateRequestDto> result = exchangeRateService.getByParams(params);
        assertFalse(result.isEmpty());
        assertEquals(SERVICE, result.get(0).getServiceName());
    }
}