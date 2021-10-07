package br.com.adrianorodrigues.stocksportfolio.usecase.decorators;

import br.com.adrianorodrigues.stocksportfolio.domain.Portfolio;
import reactor.core.publisher.Mono;

public interface GetPortfolioDecoratorUseCase {

    Mono<Portfolio> execute();

}
