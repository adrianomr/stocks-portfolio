package br.com.adrianorodrigues.stocksportfolio.usecase.decorators;

import br.com.adrianorodrigues.stocksportfolio.domain.Portfolio;

public interface GetPortfolioDecoratorUseCase {

    Portfolio execute(Portfolio portfolio);

}
