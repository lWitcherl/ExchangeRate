package com.sikoraton.exchangerate.repository;

import java.util.Set;

import com.sikoraton.exchangerate.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CurrencyRepository extends JpaRepository<Currency, String> {
    @Query(value = "SELECT code from Currency")
    Set<String> getAllCode();
}
