package br.com.adrianorodrigues.stocksportfolio.usecase.decorators;

import br.com.adrianorodrigues.stocksportfolio.context.TokenContext;
import br.com.adrianorodrigues.stocksportfolio.context.dto.TokenDto;
import br.com.adrianorodrigues.stocksportfolio.domain.Portfolio;
import br.com.adrianorodrigues.stocksportfolio.domain.Stock;
import br.com.adrianorodrigues.stocksportfolio.entrypoint.rest.dto.PortfolioDto;
import br.com.adrianorodrigues.stocksportfolio.external.repository.StocksPortfolioRepository;
import br.com.adrianorodrigues.stocksportfolio.external.repository.dto.StockDto;
import br.com.adrianorodrigues.stocksportfolio.external.repository.dto.StocksPortfolioDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = GetPortfolioFromDatabase.class)
class GetPortfolioFromDatabaseTest {

    @MockBean
    StocksPortfolioRepository stocksPortfolioRepository;

    @MockBean
    TokenContext context;

    @Autowired
    GetPortfolioFromDatabase getPortfolioFromDatabase;

    @Test
    void execute() {
        Mockito
                .when(context.getTokenDto())
                .thenReturn(TokenDto.builder().userId(1L).build());
        Mockito
                .when(stocksPortfolioRepository.findByUserId(1L))
                .thenReturn(StocksPortfolioDto.builder().id(1L).stocks(Collections.singletonList(StockDto.builder().id(1L).ticker("B3SA3").build())).build());

        Portfolio portfolio = getPortfolioFromDatabase.execute(new Portfolio());

        assertEquals(Portfolio.builder().id(1L).stocks(Collections.singletonList(Stock.builder().id(1L).ticker("B3SA3").build())).build(), portfolio);
    }
}