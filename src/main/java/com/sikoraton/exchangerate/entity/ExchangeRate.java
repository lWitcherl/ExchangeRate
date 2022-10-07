package com.sikoraton.exchangerate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "currency_from",
            referencedColumnName = "code",
            nullable = false,
            foreignKey = @ForeignKey(name = "currency_from_exchange_foreignkey"))
    private Currency from;
    @OneToOne
    @JoinColumn(name = "currency_to",
            referencedColumnName = "code",
            nullable = false,
            foreignKey = @ForeignKey(name = "currency_to_exchange_foreignkey"))
    private Currency to;
    @Column(columnDefinition = "numeric(12,6)", nullable = false)
    private BigDecimal rate;
    @Column(nullable = false)
    private String serviceName;
    @Column(nullable = false)
    private LocalDate date;
    private LocalDateTime dateFromImport;
    @Column(columnDefinition = "boolean default false")
    private Boolean lower = Boolean.FALSE;

    public ExchangeRate(Currency from, Currency to, BigDecimal rate, String serviceName, LocalDate date) {
        this.from = from;
        this.to = to;
        this.rate = rate;
        this.serviceName = serviceName;
        this.date = date;
    }
}
