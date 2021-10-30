package br.com.adrianorodrigues.stocksportfolio.configuration;

import br.com.adrianorodrigues.stocksportfolio.external.repository.StockRepository;
import br.com.adrianorodrigues.stocksportfolio.external.repository.StocksPortfolioRepository;
import br.com.adrianorodrigues.stocksportfolio.external.repository.dto.StockDto;
import br.com.adrianorodrigues.stocksportfolio.external.repository.dto.StocksPortfolioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
public class FillDataBase {


    @Bean
    @Profile("dev")
    public CommandLineRunner run(@Autowired StocksPortfolioRepository stocksPortfolioRepository,
                                 @Autowired StockRepository stockRepository) {
        return args -> {
            StockDto stockDto = stockRepository.save(StockDto.builder().id(1l).ticker("B3SA3").build());
            stocksPortfolioRepository
                    .save(StocksPortfolioDto
                            .builder()
                            .userId(1l)
                            .stocks(Arrays.asList(stockDto))
                            .build());
        };

    }

}