package com.example.mike.checkingcurrencyexchangeratechanges.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataDto {

    Map<String, InfoDto> images = new HashMap<>();
}
