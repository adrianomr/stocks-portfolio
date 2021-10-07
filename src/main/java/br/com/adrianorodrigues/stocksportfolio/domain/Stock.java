package br.com.adrianorodrigues.stocksportfolio.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Stock {

    private Long id;
    private String ticker;
    private BigDecimal price;

}
