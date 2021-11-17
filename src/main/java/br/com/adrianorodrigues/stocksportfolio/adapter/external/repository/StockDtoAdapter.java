package br.com.adrianorodrigues.stocksportfolio.adapter.external.repository;

import br.com.adrianorodrigues.stocksportfolio.domain.Stock;
import br.com.adrianorodrigues.stocksportfolio.external.repository.dto.StockDto;
import br.com.adrianorodrigues.stocksportfolio.external.repository.dto.StocksPortfolioDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StockDtoAdapter {

    static StockDto convert(StocksPortfolioDto portfolio, Stock stock){
        return StockDto
                .builder()
                .id(stock.getId())
                .ticker(stock.getTicker())
                .portfolio(portfolio)
                .build();
    }

}
