package br.com.adrianorodrigues.stocksportfolio.external.repository;

import br.com.adrianorodrigues.stocksportfolio.external.repository.dto.StockDto;
import br.com.adrianorodrigues.stocksportfolio.external.repository.dto.StocksPortfolioDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StocksPortfolioRepositoryTest {

    @Autowired
    StockRepository stockRepository;

    @Autowired
    StocksPortfolioRepository stocksPortfolioRepository;

    @BeforeEach
    void setUp() {
        stockRepository.deleteAll();
        stocksPortfolioRepository.deleteAll();
    }

    @Test
    void testPortfolioInsertion() {
        StockDto stock = stockRepository
                .save(StockDto
                        .builder()
                        .ticker("B3SA3")
                        .build());

        StocksPortfolioDto stocksPortfolio = stocksPortfolioRepository.save(StocksPortfolioDto
                .builder()
                .stocks(Collections.singletonList(stock))
                .build());

        assertEquals(1, stocksPortfolio.getStocks().size());
    }
}