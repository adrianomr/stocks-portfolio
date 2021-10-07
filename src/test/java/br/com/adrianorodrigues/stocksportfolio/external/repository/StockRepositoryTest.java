package br.com.adrianorodrigues.stocksportfolio.external.repository;

import br.com.adrianorodrigues.stocksportfolio.external.repository.dto.StockDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StockRepositoryTest {

    @Autowired
    StockRepository stockRepository;

    @BeforeEach
    void setUp() {
        stockRepository.deleteAll();
    }

    @Test
    void insertStockTest() {
        StockDto stockDto = stockRepository.save(StockDto
                .builder()
                .ticker("B3SA3")
                .build());

        assertEquals("B3SA3", stockDto.getTicker());
    }
}