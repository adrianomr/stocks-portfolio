package br.com.adrianorodrigues.stocksportfolio.external.gateway.client;

import br.com.adrianorodrigues.stocksportfolio.external.gateway.dto.StockDto;
import reactor.core.publisher.Mono;

public interface StocksClient {

    Mono<StockDto> getStock(String id);

}
