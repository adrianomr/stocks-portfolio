package br.com.adrianorodrigues.stocksportfolio.external.gateway;

import br.com.adrianorodrigues.stocksportfolio.external.gateway.dto.StockDto;
import reactor.core.publisher.Mono;

import java.util.List;

public interface StocksGateway {

    Mono<StockDto> getStock(String id);

    Mono<List<StockDto>> getStocks(List<String> id);

}
