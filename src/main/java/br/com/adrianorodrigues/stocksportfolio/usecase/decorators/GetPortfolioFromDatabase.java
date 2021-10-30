package br.com.adrianorodrigues.stocksportfolio.usecase.decorators;

import br.com.adrianorodrigues.stocksportfolio.adapter.domain.PortfolioAdapter;
import br.com.adrianorodrigues.stocksportfolio.context.TokenContext;
import br.com.adrianorodrigues.stocksportfolio.domain.Portfolio;
import br.com.adrianorodrigues.stocksportfolio.exceptions.PortfolioNotFoundException;
import br.com.adrianorodrigues.stocksportfolio.external.repository.StocksPortfolioRepository;
import br.com.adrianorodrigues.stocksportfolio.external.repository.dto.StocksPortfolioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Order(1)
public class GetPortfolioFromDatabase implements GetPortfolioDecoratorUseCase {


    private final StocksPortfolioRepository repository;
    private final TokenContext tokenContext;

    @Override
    public Portfolio execute(Portfolio portfolio) {
        long userId = tokenContext.getTokenDto().getUserId();

        StocksPortfolioDto portfolioDto = repository
                .findByUserId(userId)
                .orElseThrow(() -> new PortfolioNotFoundException("Portfolio not found for user: " + userId));
        Portfolio databasePortfolio = PortfolioAdapter.INSTACE.convert(portfolioDto);

        portfolio.setId(databasePortfolio.getId());
        portfolio.setStocks(databasePortfolio.getStocks());

        return portfolio;
    }

}
