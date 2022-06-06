package com.example.mike.exchangechecker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateDto {
    private String disclaimer;
    private String license;
    private Long timestamp;
    private String base;
    private Map<String, BigDecimal> rates = new HashMap<>();
}
