package br.com.adrianorodrigues.stocksportfolio.external.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class StockDto {

    private Long id;
    private String ticker;
    private BigDecimal price;

}
