package br.com.adrianorodrigues.stocksportfolio.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Builder
@AllArgsConstructor
@Data
public class Portfolio {

    private Long id;
    private List<Stock> stocks;

    public Portfolio aggregate(Portfolio portfolio) {
        id = Objects.isNull(id) ? portfolio.getId() : id;
        stocks = Objects.isNull(stocks) ? portfolio.getStocks() : stocks;
        return this;
    }
}
