package com.example.mike.checkingcurrencyexchangeratechanges.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GifDto {
    List<DataDto> data;
}
