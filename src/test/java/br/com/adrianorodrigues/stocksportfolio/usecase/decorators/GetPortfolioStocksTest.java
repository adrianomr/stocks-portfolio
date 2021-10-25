package br.com.adrianorodrigues.stocksportfolio.usecase.decorators;

import br.com.adrianorodrigues.stocksportfolio.domain.Portfolio;
import br.com.adrianorodrigues.stocksportfolio.domain.Stock;
import br.com.adrianorodrigues.stocksportfolio.external.gateway.StocksGateway;
import br.com.adrianorodrigues.stocksportfolio.external.gateway.StocksGatewayImpl;
import br.com.adrianorodrigues.stocksportfolio.external.gateway.dto.StockDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GetPortfolioStocksTest {

    @Mock
    StocksGatewayImpl stocksGateway;
    @InjectMocks
    GetPortfolioStocks getPortfolioStocks;

    @BeforeEach
    void setUp() {
        Mockito
                .when(stocksGateway.getStock("1"))
                .thenReturn(buildMonoStock("B3SA3", 1));
        Mockito
                .when(stocksGateway.getStock("2"))
                .thenReturn(buildMonoStock("ITUB4", 2));
        Mockito
                .when(stocksGateway.getStock("3"))
                .thenReturn(buildMonoStock("ITSA4", 3));
        Mockito
                .when(stocksGateway.getStock("4"))
                .thenReturn(buildMonoStock("BCFF11", 4));
    }

    private Mono<StockDto> buildMonoStock(String ticker, double price) {
        return Mono
                .just(StockDto
                .builder()
                        .id(1L)
                        .price(BigDecimal.valueOf(price))
                        .ticker(ticker)
                .build());
    }

    @Test
    void execute() {
        Portfolio portfolio = buildPortfolio();

        getPortfolioStocks.execute(portfolio);

        assertThatStocksPriceIsFilled(portfolio);
    }

    private void assertThatStocksPriceIsFilled(Portfolio portfolio) {
        assertEquals(BigDecimal.valueOf(1.0), portfolio.getStocks().get(0).getPrice());
        assertEquals(BigDecimal.valueOf(2.0), portfolio.getStocks().get(1).getPrice());
        assertEquals(BigDecimal.valueOf(3.0), portfolio.getStocks().get(2).getPrice());
        assertEquals(BigDecimal.valueOf(4.0), portfolio.getStocks().get(3).getPrice());
    }

    private Portfolio buildPortfolio() {
        return Portfolio
                .builder()
                .stocks(Arrays.
                        asList(
                                buildStock(1L, "B3SA3"),
                                buildStock(2L, "ITUB4"),
                                buildStock(3L, "ITSA4"),
                                buildStock(4L, "BCFF11")
                        ))
                .build();
    }

    private Stock buildStock(Long id, String ticker) {
        return Stock
                .builder()
                .id(id)
                .ticker(ticker)
                .build();
    }
}