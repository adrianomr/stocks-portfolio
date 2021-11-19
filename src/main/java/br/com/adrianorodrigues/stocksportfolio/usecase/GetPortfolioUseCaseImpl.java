package br.com.adrianorodrigues.stocksportfolio.usecase;

import br.com.adrianorodrigues.stocksportfolio.domain.Portfolio;
import br.com.adrianorodrigues.stocksportfolio.usecase.decorators.GetPortfolioDecoratorUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetPortfolioUseCaseImpl implements GetPortfolioUseCase {


    private final List<GetPortfolioDecoratorUseCase> getPortfolioDecoratorUseCases;

    @Override
    public Portfolio getPortfolio() {
        Portfolio portfolio = new Portfolio();
        getPortfolioDecoratorUseCases
                .forEach(getPortfolioDecoratorUseCase -> getPortfolioDecoratorUseCase.execute(portfolio));
        return portfolio;
    }
}
