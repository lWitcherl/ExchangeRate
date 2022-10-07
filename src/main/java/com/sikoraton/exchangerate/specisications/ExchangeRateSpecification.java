package com.sikoraton.exchangerate.specisications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.sikoraton.exchangerate.entity.ExchangeRate;
import org.springframework.data.jpa.domain.Specification;

import static com.sikoraton.exchangerate.utils.constant.ConstantForSpecification.*;
import static com.sikoraton.exchangerate.utils.constant.RequestParamConstant.*;

public class ExchangeRateSpecification implements Specification<ExchangeRate> {
    private SearchCriteria criteria;

    public ExchangeRateSpecification(SearchCriteria searchCriteria) {
        this.criteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<ExchangeRate> root, CriteriaQuery<?> criteriaQuery,
                                 CriteriaBuilder criteriaBuilder) {
        switch (criteria.getKey()) {
            case PARAM_DATE_START:
                return criteriaBuilder.greaterThanOrEqualTo(root.get(DATE), LocalDate.parse(criteria.getValue()));
            case PARAM_DATE_END:
                return criteriaBuilder.lessThanOrEqualTo(root.get(DATE), LocalDate.parse(criteria.getValue()));
            case PARAM_FROM:
                return criteriaBuilder.equal(root.join(JOIN_CURRENCY_FROM).get(CODE), criteria.getValue());
            case PARAM_TO:
                return root.join(JOIN_CURRENCY_TO).get(CODE).in(criteria.getList());
            case PARAM_RATE_MORE:
                return criteriaBuilder.greaterThanOrEqualTo(root.get(RATE), new BigDecimal(criteria.getValue()));
            case PARAM_RATE_LESS:
                return criteriaBuilder.lessThanOrEqualTo(root.get(RATE), new BigDecimal(criteria.getValue()));
            case SERVICE:
                return criteriaBuilder.equal(root.get(SERVICE), criteria.getValue());
        }
        return null;
    }
}
