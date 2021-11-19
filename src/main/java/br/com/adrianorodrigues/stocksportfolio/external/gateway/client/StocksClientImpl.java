package br.com.adrianorodrigues.stocksportfolio.external.gateway.client;

import br.com.adrianorodrigues.stocksportfolio.external.gateway.dto.StockDto;
import br.com.adrianorodrigues.stocksportfolio.external.gateway.reactive.ReactiveWebClient;
import br.com.adrianorodrigues.stocksportfolio.external.gateway.reactive.ReactiveWebClientImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class StocksClientImpl implements StocksClient {

    private final ReactiveWebClient reactiveWebClient;

    public StocksClientImpl(@Value("${gateway.url.stocks}") String url) {
        this.reactiveWebClient = new ReactiveWebClientImpl(url);
    }

    @Override
    public Mono<StockDto> getStock(String id) {
        return reactiveWebClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/"+id).build())
                .retrieve()
                .bodyToMono(StockDto.class)
                .onErrorMap(throwable -> throwable);
    }
}
