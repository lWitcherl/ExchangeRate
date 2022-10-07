package com.sikoraton.exchangerate.repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.sikoraton.exchangerate.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long>,
                                                    JpaSpecificationExecutor<ExchangeRate> {

    Set<ExchangeRate> findAllByDate(LocalDate date);
    Set<ExchangeRate> findAllByFrom_CodeAndTo_CodeInAndDateAndLowerTrue(String from, Collection<String> to, LocalDate date);

    @Modifying
    @Query(value = "update ExchangeRate set lower = true where id in :ids")
    Integer setLowerRate(@Param("ids") List<Long> ids);
}
