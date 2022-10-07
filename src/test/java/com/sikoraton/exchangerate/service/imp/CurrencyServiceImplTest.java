package com.sikoraton.exchangerate.service.imp;

import java.util.Set;

import com.sikoraton.exchangerate.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CurrencyServiceImplTest {

    @Autowired
    private CurrencyService currencyService;

    @Test
    void validateCode() {
        Set<String> codes = currencyService.getAllCurrencyCode();
        assertTrue(currencyService.validateCode(codes.stream().iterator().next(), codes));
    }
}