package br.com.adrianorodrigues.stocksportfolio.external.repository;

import br.com.adrianorodrigues.stocksportfolio.external.repository.dto.StockDto;
import br.com.adrianorodrigues.stocksportfolio.external.repository.dto.StocksPortfolioDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class StocksPortfolioRepositoryTest {

    @Autowired
    StocksPortfolioRepository stocksPortfolioRepository;

    @BeforeEach
    void beforeEach() {
        stocksPortfolioRepository.deleteAll();
    }

    @Test
    void testPortfolioInsertion() {
        StockDto stock = StockDto
                .builder()
                .ticker("B3SA3")
                .build();

        StocksPortfolioDto stocksPortfolio = stocksPortfolioRepository.save(StocksPortfolioDto
                .builder()
                .stocks(Collections.singletonList(stock))
                        .userId(1L)
                .build());
        stocksPortfolio.getStocks().forEach(stockDto -> stockDto.setPortfolio(stocksPortfolio));
        stocksPortfolioRepository.save(stocksPortfolio);

        assertEquals(1, stocksPortfolioRepository.findByUserId(1L).get().getStocks().size());
    }
}