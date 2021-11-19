package br.com.adrianorodrigues.stocksportfolio.entrypoint.queue;

import br.com.adrianorodrigues.stocksportfolio.external.repository.StocksPortfolioRepository;
import br.com.adrianorodrigues.stocksportfolio.external.repository.dto.StocksPortfolioDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class TransactionConsumerTest {

    @Autowired
    UpdatePortfolioConsumer transactionConsumer;
    @Autowired
    StocksPortfolioRepository stocksPortfolioRepository;

    @BeforeEach
    void beforeEach() {
        stocksPortfolioRepository.deleteAll();
    }

    @Test
    void createTransaction() {
        String transactionJson = "{ \"id\": 1, \"transactions\": [{\"date\": \"2020-06-22\", \"action\": \"buy\", \"ticker\": \"TEST11\", \"amount\": 1, \"price\": \"149.97\"}]}\n";

        transactionConsumer.process(transactionJson);

        StocksPortfolioDto stocksPortfolioDto = stocksPortfolioRepository
                .findByUserId(1l)
                .get();

        assertThatStockWasInserted(stocksPortfolioDto);
    }

    private void assertThatStockWasInserted(StocksPortfolioDto stocksPortfolioDto) {
        assertTrue(stocksPortfolioDto
                .getStocks()
                .stream()
                .anyMatch(stockDto -> stockDto.getTicker().equalsIgnoreCase("TEST11")));
    }
}