package br.com.adrianorodrigues.stocksportfolio.entrypoint.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PortfolioDto {

    private Long id;
    private BigDecimal investedAmount;
    private BigDecimal currentAmount;
    private BigDecimal balance;
    @Builder.Default
    private List<StockDto> stocks = new ArrayList<>();

}
