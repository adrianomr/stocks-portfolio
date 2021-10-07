package br.com.adrianorodrigues.stocksportfolio.usecase.decorators;

import br.com.adrianorodrigues.stocksportfolio.adapter.domain.PortfolioAdapter;
import br.com.adrianorodrigues.stocksportfolio.context.TokenContext;
import br.com.adrianorodrigues.stocksportfolio.domain.Portfolio;
import br.com.adrianorodrigues.stocksportfolio.entrypoint.rest.dto.PortfolioDto;
import br.com.adrianorodrigues.stocksportfolio.external.repository.StocksPortfolioRepository;
import br.com.adrianorodrigues.stocksportfolio.external.repository.dto.StocksPortfolioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

@Service
@RequiredArgsConstructor
public class GetPortfolioFromDatabase implements GetPortfolioDecoratorUseCase{


    private final StocksPortfolioRepository repository;
    private final TokenContext tokenContext;

    @Override
    public Mono<Portfolio> execute() {
        return Mono.create(this::findPortfolio);
    }

    private void findPortfolio(MonoSink<Portfolio> sink) {
        StocksPortfolioDto portfolioDto = repository
                .findByUserId(tokenContext.getTokenDto().getUserId());

        sink.success(PortfolioAdapter.INSTACE.convert(portfolioDto));
    }

}
