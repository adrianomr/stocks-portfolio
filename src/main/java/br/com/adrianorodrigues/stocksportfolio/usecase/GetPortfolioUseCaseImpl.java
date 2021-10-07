package br.com.adrianorodrigues.stocksportfolio.usecase;

import br.com.adrianorodrigues.stocksportfolio.domain.Portfolio;
import br.com.adrianorodrigues.stocksportfolio.usecase.decorators.GetPortfolioDecoratorUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetPortfolioUseCaseImpl implements GetPortfolioUseCase {


    private final List<GetPortfolioDecoratorUseCase> getPortfolioDecoratorUseCases;

    @Override
    public Portfolio getPortfolio() {
        List<Mono<Portfolio>> portfolios = getPortfolioDecoratorUseCases
                .stream()
                .map(GetPortfolioDecoratorUseCase::execute)
                .collect(Collectors.toList());
        return Mono
                .zip(portfolios, objects -> Arrays
                        .stream(objects)
                        .map(Portfolio.class::cast)
                        .reduce(Portfolio::aggregate)
                )
                .block()
                .orElseThrow();
    }
}
