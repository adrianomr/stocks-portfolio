package br.com.adrianorodrigues.stocksportfolio.external.gateway;

import br.com.adrianorodrigues.stocksportfolio.external.gateway.client.StocksClient;
import br.com.adrianorodrigues.stocksportfolio.external.gateway.dto.StockDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StocksGatewayImpl implements StocksGateway{

    private final StocksClient stocksClient;

    @Override
    public Mono<StockDto> getStock(String id) {
        return stocksClient
                .getStock(id)
                .onErrorReturn(StockDto.builder().ticker(id).price(BigDecimal.ZERO).build());
    }

    @Override
    public Mono<List<StockDto>> getStocks(List<String> ids) {
        List<Mono<StockDto>> stocks = ids
                .stream()
                .map(this::getStock)
                .collect(Collectors.toList());

        return Mono
                .zip(stocks, objects -> Arrays.stream(objects)
                        .map(StockDto.class::cast)
                        .collect(Collectors.toList()));
    }

}
