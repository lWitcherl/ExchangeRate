package com.sikoraton.exchangerate.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.sikoraton.exchangerate.entity.ExchangeRate;
import com.sikoraton.exchangerate.specisications.ExchangeRateSpecification;
import com.sikoraton.exchangerate.specisications.SearchCriteria;
import com.sikoraton.exchangerate.utils.constant.RequestParamConstant;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;

public class ProcessingSpecification {
    public static Specification<ExchangeRate> processing(MultiValueMap<String, String> params){
        List<ExchangeRateSpecification> list = getFilters(params);
        Specification<ExchangeRate> specification = Specification.where(list.remove(0));
        for (ExchangeRateSpecification predicate : list)
            specification = specification.and(predicate);
        return specification;
    }

    public static List<ExchangeRateSpecification> getFilters(MultiValueMap<String, String> params){
        List<ExchangeRateSpecification> list = new LinkedList<>();
        for (Map.Entry<String, List<String>> param : params.entrySet()){
            SearchCriteria searchCriteria = new SearchCriteria(param.getKey());
            if (param.getKey().equals(RequestParamConstant.PARAM_TO))
                searchCriteria.setList(param.getValue());
            else
                searchCriteria.setValue(param.getValue().get(0));
            list.add(new ExchangeRateSpecification(searchCriteria));
        }
        return list;
    }
}
