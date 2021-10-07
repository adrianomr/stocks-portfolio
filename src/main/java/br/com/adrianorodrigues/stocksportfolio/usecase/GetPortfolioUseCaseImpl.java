package br.com.adrianorodrigues.stocksportfolio.usecase;

import br.com.adrianorodrigues.stocksportfolio.domain.Portfolio;
import org.springframework.stereotype.Service;

@Service
public class GetPortfolioUseCaseImpl implements GetPortfolioUseCase{
    @Override
    public Portfolio getPortfolio() {
        return Portfolio
                .builder()
                .build();
    }
}
