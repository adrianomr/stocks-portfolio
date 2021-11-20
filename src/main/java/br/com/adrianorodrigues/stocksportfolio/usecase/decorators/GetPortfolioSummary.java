package br.com.adrianorodrigues.stocksportfolio.usecase.decorators;

import br.com.adrianorodrigues.stocksportfolio.domain.Portfolio;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Order(3)
@Slf4j
public class GetPortfolioSummary implements GetPortfolioDecoratorUseCase{

    @Override
    public Portfolio execute(Portfolio portfolio) {
        portfolio
                .getStocks()
                .forEach(portfolio::summaryze);
        return portfolio;
    }


}
