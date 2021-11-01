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
public class FillDataBaseConfig {

    @Bean
    @Profile("dev")
    public CommandLineRunner run(@Autowired StocksPortfolioRepository stocksPortfolioRepository,
                                 @Autowired StockRepository stockRepository) {
        return args -> {
            StockDto b3sa3 = stockRepository.save(buildB3SA3());
            StockDto bcff11 = stockRepository.save(buildBCFF11());
            StockDto hgre11 = stockRepository.save(buildHGRE11());

            stocksPortfolioRepository
                    .save(StocksPortfolioDto
                            .builder()
                            .userId(1l)
                            .stocks(Arrays.asList(b3sa3, bcff11, hgre11))
                            .build());
        };

    }

    private StockDto buildB3SA3() {
        return StockDto.builder().id(1l).ticker("B3SA3").build();
    }

    private StockDto buildBCFF11() {
        return StockDto.builder().id(2l).ticker("BCFF11").build();
    }

    private StockDto buildHGRE11() {
        return StockDto.builder().id(3l).ticker("HGRE11").build();
    }

}