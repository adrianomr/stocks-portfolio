package br.com.adrianorodrigues.stocksportfolio.configuration;

import br.com.adrianorodrigues.stocksportfolio.external.repository.StocksPortfolioRepository;
import br.com.adrianorodrigues.stocksportfolio.external.repository.dto.StockDto;
import br.com.adrianorodrigues.stocksportfolio.external.repository.dto.StocksPortfolioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class FillDataBaseConfig {

    @Bean
    @Profile("dev")
    public CommandLineRunner run(@Autowired StocksPortfolioRepository stocksPortfolioRepository) {
        return args -> {
            StockDto b3sa3 = buildB3SA3();
            StockDto bcff11 = buildBCFF11();
            StockDto hgre11 = buildHGRE11();
            List<StockDto> stocks = Arrays.asList(b3sa3, bcff11, hgre11);

            StocksPortfolioDto portfolioDto = stocksPortfolioRepository
                    .save(StocksPortfolioDto
                            .builder()
                            .userId(1l)
                            .stocks(new ArrayList<>())
                            .build());
            stocks
                    .forEach(stockDto -> stockDto.setPortfolio(portfolioDto));

            portfolioDto.getStocks().addAll(stocks);

            stocksPortfolioRepository.save(portfolioDto);
        };

    }

    private StockDto buildB3SA3() {
        return StockDto.builder().ticker("B3SA3").build();
    }

    private StockDto buildBCFF11() {
        return StockDto.builder().ticker("BCFF11").build();
    }

    private StockDto buildHGRE11() {
        return StockDto.builder().ticker("HGRE11").build();
    }

}