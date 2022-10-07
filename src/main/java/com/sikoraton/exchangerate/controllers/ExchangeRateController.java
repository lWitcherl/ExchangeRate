package com.sikoraton.exchangerate.controllers;

import java.util.List;

import com.sikoraton.exchangerate.dto.ExchangeRateRequestDto;
import com.sikoraton.exchangerate.service.ExchangeRateService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.sikoraton.exchangerate.utils.constant.RequestParamConstant.DEFAULT_VALUE_FROM;
import static com.sikoraton.exchangerate.utils.constant.RequestParamConstant.DEFAULT_VALUE_TO;
import static com.sikoraton.exchangerate.utils.constant.RequestParamConstant.PARAM_FROM;
import static com.sikoraton.exchangerate.utils.constant.RequestParamConstant.PARAM_TO;

@Getter @Setter
@RestController
@RequestMapping("/exchangerate")
public class ExchangeRateController {
    private ExchangeRateService exchangeRateService;

    @Autowired
    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping("/latest")
    public List<ExchangeRateRequestDto> getLatest (@RequestParam MultiValueMap<String, String> params){
        if (!params.containsKey(PARAM_FROM))
            params.add(PARAM_FROM, DEFAULT_VALUE_FROM);
        if (!params.containsKey(PARAM_TO))
            params.add(PARAM_TO, DEFAULT_VALUE_TO);
        return exchangeRateService.getCurrentRate(params.getFirst(PARAM_FROM), params.get(PARAM_TO));
    }

    @GetMapping("/param")
    public List<ExchangeRateRequestDto> getByParams (@RequestParam MultiValueMap<String, String> params){

        return exchangeRateService.getByParams(params);
    }
}

