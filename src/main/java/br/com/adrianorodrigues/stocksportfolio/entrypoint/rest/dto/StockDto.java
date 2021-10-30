package br.com.adrianorodrigues.stocksportfolio.entrypoint.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StockDto {

    private Long id;
    private String ticker;
    private BigDecimal price;

}
