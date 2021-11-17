package br.com.adrianorodrigues.stocksportfolio.adapter.external.repository;

import br.com.adrianorodrigues.stocksportfolio.domain.Portfolio;
import br.com.adrianorodrigues.stocksportfolio.external.repository.dto.StocksPortfolioDto;
import lombok.experimental.UtilityClass;

import java.util.stream.Collectors;

@UtilityClass
public class StocksPortfolioDtoAdapter {


    public static StocksPortfolioDto convert(Portfolio portfolio) {
        StocksPortfolioDto stocksPortfolioDto = StocksPortfolioDto
                .builder()
                .id(portfolio.getId())
                .userId(portfolio.getUserId())
                .build();

        stocksPortfolioDto.setStocks(portfolio
                .getStocks()
                .stream()
                .map(stock -> StockDtoAdapter.convert(stocksPortfolioDto, stock)).
                collect(Collectors.toList()));

        return stocksPortfolioDto;
    }

}
