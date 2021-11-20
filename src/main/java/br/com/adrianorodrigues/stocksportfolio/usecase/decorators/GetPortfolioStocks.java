package br.com.adrianorodrigues.stocksportfolio.usecase.decorators;

import br.com.adrianorodrigues.stocksportfolio.domain.Portfolio;
import br.com.adrianorodrigues.stocksportfolio.domain.Stock;
import br.com.adrianorodrigues.stocksportfolio.external.gateway.StocksGateway;
import br.com.adrianorodrigues.stocksportfolio.external.gateway.dto.StockDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Order(2)
@Slf4j
public class GetPortfolioStocks implements GetPortfolioDecoratorUseCase{


    private final StocksGateway gateway;

    @Override
    public Portfolio execute(Portfolio portfolio) {
        List<Mono<Stock>> monos = portfolio
                .getStocks()
                .stream()
                .map(this::updateStock)
                .collect(Collectors.toList());

        Mono
                .zip(monos, objects -> objects)
                .block();

        return portfolio;
    }

    private Mono<Stock> updateStock(Stock stock) {
        Mono<StockDto> stockDto = gateway.getStock(stock.getTicker());
        return stockDto
                .map(stockDto1 -> fillPrice(stock, stockDto1))
                .onErrorMap(throwable -> throwable);
    }

    private Stock fillPrice(Stock stock, StockDto stockDto){
        stock.updateWithPrice(stockDto.getPrice());
        return stock;
    }

}
