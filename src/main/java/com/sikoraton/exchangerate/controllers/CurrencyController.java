package com.sikoraton.exchangerate.controllers;

import java.util.List;
import java.util.Set;

import com.sikoraton.exchangerate.dto.CurrencyDto;
import com.sikoraton.exchangerate.service.CurrencyService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Setter @Getter
@RestController
@RequestMapping("/currency")
public class CurrencyController {
    private CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/codes")
    public Set<String> getAllCurrenciesCode(){
        return currencyService.getAllCurrencyCode();
    }

    @GetMapping("/all")
    public List<CurrencyDto> getAllCurrencies(){
        return currencyService.getAllCurrencies();
    }
}
