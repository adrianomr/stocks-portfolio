package br.com.adrianorodrigues.stocksportfolio.usecase.decorators;

import br.com.adrianorodrigues.stocksportfolio.domain.Portfolio;
import br.com.adrianorodrigues.stocksportfolio.domain.Stock;
import br.com.adrianorodrigues.stocksportfolio.external.gateway.StocksGateway;
import br.com.adrianorodrigues.stocksportfolio.external.gateway.StocksGatewayImpl;
import br.com.adrianorodrigues.stocksportfolio.external.gateway.dto.StockDto;
import org.assertj.core.api.Assertions;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GetPortfolioStocksTest {

    @Mock
    StocksGatewayImpl stocksGateway;
    @InjectMocks
    GetPortfolioStocks getPortfolioStocks;

    @Test
    void execute() {
        Mockito
                .when(stocksGateway.getStock("B3SA3"))
                .thenReturn(buildMonoStock("B3SA3", 1));
        Mockito
                .when(stocksGateway.getStock("ITUB4"))
                .thenReturn(buildMonoStock("ITUB4", 2));
        Mockito
                .when(stocksGateway.getStock("ITSA4"))
                .thenReturn(buildMonoStock("ITSA4", 3));
        Mockito
                .when(stocksGateway.getStock("BCFF11"))
                .thenReturn(buildMonoStock("BCFF11", 4));

        Portfolio portfolio = buildPortfolio();

        getPortfolioStocks.execute(portfolio);

        assertThatStocksPriceIsFilled(portfolio);
    }

    @Test
    void executeWhenStockNotFoundShouldThrowException() {
        Portfolio portfolio = buildStockNotFoundPortfolio();

        Mockito
                .when(stocksGateway.getStock("5"))
                .thenThrow(new RuntimeException("stock not found"));

        assertThrows(RuntimeException.class, () -> getPortfolioStocks.execute(portfolio));
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

    private Portfolio buildStockNotFoundPortfolio() {
        return Portfolio
                .builder()
                .stocks(Arrays.
                        asList(
                                buildStock(5L, "XPLG11")
                        ))
                .build();
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