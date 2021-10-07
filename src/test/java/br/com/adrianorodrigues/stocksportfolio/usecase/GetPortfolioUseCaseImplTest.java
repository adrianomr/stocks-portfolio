package br.com.adrianorodrigues.stocksportfolio.usecase;

import br.com.adrianorodrigues.stocksportfolio.domain.Portfolio;
import br.com.adrianorodrigues.stocksportfolio.usecase.decorators.GetPortfolioDecoratorUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {GetPortfolioUseCaseImpl.class})
class GetPortfolioUseCaseImplTest {

    @MockBean
    GetPortfolioDecoratorUseCase getPortfolioDecoratorUseCase;

    @Autowired
    GetPortfolioUseCase getPortfolioUseCase;

    @Test
    void getPortfolio() {
        Mockito
                .when(getPortfolioDecoratorUseCase.execute())
                .thenReturn(Mono.just(Portfolio.builder().build()));

        Portfolio portfolio = getPortfolioUseCase.getPortfolio();

        assertEquals(Portfolio.builder().build(), portfolio);
    }
}