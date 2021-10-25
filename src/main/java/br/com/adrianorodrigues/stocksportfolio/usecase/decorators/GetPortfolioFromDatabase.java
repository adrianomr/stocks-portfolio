package br.com.adrianorodrigues.stocksportfolio.usecase.decorators;

import br.com.adrianorodrigues.stocksportfolio.adapter.domain.PortfolioAdapter;
import br.com.adrianorodrigues.stocksportfolio.context.TokenContext;
import br.com.adrianorodrigues.stocksportfolio.domain.Portfolio;
import br.com.adrianorodrigues.stocksportfolio.external.repository.StocksPortfolioRepository;
import br.com.adrianorodrigues.stocksportfolio.external.repository.dto.StocksPortfolioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

@Service
@RequiredArgsConstructor
@Order(1)
public class GetPortfolioFromDatabase implements GetPortfolioDecoratorUseCase{


    private final StocksPortfolioRepository repository;
    private final TokenContext tokenContext;

    @Override
    public Portfolio execute(Portfolio portfolio) {
        StocksPortfolioDto portfolioDto = repository
                .findByUserId(tokenContext.getTokenDto().getUserId());

        return PortfolioAdapter.INSTACE.convert(portfolioDto);
    }

}
